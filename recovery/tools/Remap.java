import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;

public class Remap {
    public static void main(String[] args) throws Exception {
        Map<String,String> classes=new HashMap<>(), members=new HashMap<>(), enumFields=new HashMap<>();
        for(String line:Files.readAllLines(Path.of(args[2]))) {
            String[] a=line.split("\t"); classes.put(a[0],a[1]);
        }
        for(String line:Files.readAllLines(Path.of(args[3]))) {
            if(!line.startsWith("\t")||line.startsWith("\t\t"))continue;
            String[] a=line.trim().split("\\s+");
            if(a[0].matches("[mf]_\\d+_"))members.put(a[0],a[a.length-1]);
        }
        try(JarFile jar=new JarFile(args[0])) {
            for(JarEntry e:Collections.list(jar.entries())) {
                if(!e.getName().endsWith(".class"))continue;
                ClassNode c=new ClassNode();new ClassReader(jar.getInputStream(e)).accept(c,0);
                int dollar=c.name.indexOf('$');
                if(dollar>=0&&classes.containsKey(c.name.substring(0,dollar))) {
                    String[] inner=c.name.substring(dollar+1).split("\\$");
                    String tail="";
                    for(String part:inner) tail+="$"+(part.matches("[a-z]{1,2}")?"Inner"+part.toUpperCase():part);
                    classes.put(c.name,classes.get(c.name.substring(0,dollar))+tail);
                }
                if((c.access&Opcodes.ACC_ENUM)==0)continue;
                Set<String> fields=new HashSet<>();for(FieldNode f:c.fields)if((f.access&Opcodes.ACC_ENUM)!=0)fields.add(f.name);
                for(MethodNode m:c.methods)if(m.name.equals("<clinit>")) {
                    String enumName=null;
                    for(AbstractInsnNode i:m.instructions) {
                        if(i.getOpcode()==Opcodes.NEW)enumName=null;
                        if(i instanceof LdcInsnNode&&enumName==null&&((LdcInsnNode)i).cst instanceof String)enumName=(String)((LdcInsnNode)i).cst;
                        if(i instanceof FieldInsnNode&&i.getOpcode()==Opcodes.PUTSTATIC) {
                            FieldInsnNode f=(FieldInsnNode)i;
                            if(f.owner.equals(c.name)&&fields.contains(f.name)&&enumName!=null)enumFields.put(c.name+"/"+f.name,enumName);
                        }
                    }
                }
            }
        }
        Remapper mapper=new Remapper(){
            public String map(String n){
                if(classes.containsKey(n))return classes.get(n);
                int dollar=n.indexOf('$');
                if(dollar>=0&&classes.containsKey(n.substring(0,dollar)))return classes.get(n.substring(0,dollar))+n.substring(dollar);
                return n;
            }
            public String mapMethodName(String o,String n,String d){return members.getOrDefault(n,n);}
            public String mapFieldName(String o,String n,String d){
                if(n.equals("do")||n.equals("if"))return "icon_"+n;
                return enumFields.getOrDefault(o+"/"+n,members.getOrDefault(n,n));
            }
            public Object mapValue(Object value){
                if(value instanceof String){
                    String s=(String)value;
                    if(classes.containsKey(s))return classes.get(s);
                    if(classes.containsKey(s.replace('.','/')))return classes.get(s.replace('.','/')).replace('/','.');
                    java.util.regex.Matcher m=java.util.regex.Pattern.compile("[mf]_\\d+_").matcher(s);
                    StringBuffer b=new StringBuffer();
                    while(m.find())m.appendReplacement(b,java.util.regex.Matcher.quoteReplacement(members.getOrDefault(m.group(),m.group())));
                    m.appendTail(b); return b.toString();
                }
                return super.mapValue(value);
            }
        };
        StringBuilder mixinMappings=new StringBuilder();
        try(JarFile jar=new JarFile(args[0])) {
            for(JarEntry e:Collections.list(jar.entries())) {
                if(!e.getName().startsWith("com/heypixel/heypixelmod/mixin/")||!e.getName().endsWith(".class"))continue;
                ClassNode c=new ClassNode();new ClassReader(jar.getInputStream(e)).accept(c,0);
                StringBuilder entries=new StringBuilder();
                for(FieldNode f:c.fields)if(members.containsKey(f.name))entries.append("\t").append(members.get(f.name)).append(" ").append(f.name).append("\n");
                for(MethodNode m:c.methods)if(members.containsKey(m.name))entries.append("\t").append(members.get(m.name)).append(" ").append(mapper.mapMethodDesc(m.desc)).append(" ").append(m.name).append("\n");
                if(entries.length()>0)mixinMappings.append(mapper.mapType(c.name)).append(" ").append(mapper.mapType(c.name)).append("\n").append(entries);
            }
        }
        Files.writeString(Path.of(args[2]).resolveSibling("mixin-reobf.tsrg"),mixinMappings.toString());
        try(JarFile in=new JarFile(args[0]); JarOutputStream out=new JarOutputStream(Files.newOutputStream(Path.of(args[1])))){
            for(JarEntry e:Collections.list(in.entries())){
                if(e.isDirectory())continue;
                byte[] bytes=in.getInputStream(e).readAllBytes(); String name=e.getName();
                if(name.endsWith(".class")){
                    ClassReader cr=new ClassReader(bytes); ClassWriter cw=new ClassWriter(0);
                    cr.accept(new ClassRemapper(cw,mapper),0); bytes=cw.toByteArray();
                    name=mapper.mapType(cr.getClassName())+".class";
                }
                out.putNextEntry(new JarEntry(name));out.write(bytes);out.closeEntry();
            }
        }
        System.out.println("Remapped "+classes.size()+" classes and "+members.size()+" Minecraft members");
    }
}
