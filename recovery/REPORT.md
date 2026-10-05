# 恢复记录

目标：Minecraft 1.20.1、Forge 47.x，原版本 9.3，mod ID `naven`。JAR 的显示名称、作者、许可字段与仓库名称不一致，均按输入保留。

原始 JAR SHA-256：

`5BD0D1F7FB2E1195331816D98BEBE5FEE46E3D87A3458C20CDBA48327F84718D`。

输入 Vineflower SHA-256：`B9B208E50793B64657A6B6292067526613F549DE7405F9243624B02F4276E409`。

## 工作过程

1. 读取清单、Forge 元数据、Mixin/refmap、内嵌依赖和全部资源；未运行原始 JAR 作为反编译工具。
2. 用提供的 Vineflower 反编译，使用 ASM 对短类名、内部类名、枚举常量和 Minecraft SRG 成员进行映射。
3. 以模块注解恢复可确认的模块名称。未确认的名称保留 `Recovered...`，不声称是原作者命名。
4. 加入 Forge 47.3.0 MDK 的 Gradle Wrapper，建立 Java 17 / official mappings 工程。
5. 修复 Java 类型和反编译重载问题，加入 Mixin 发布映射；保留原 refmap。
6. 通过 JDK 编译、完整 Forge 构建、IDEA 配置生成和归档结构核对，另做开发客户端启动验证。

## 必要修复

- 原混淆枚举字段名与注解枚举名称不一致，恢复枚举常量名。
- 原始短内部类名与字段名冲突，改为 `InnerA` 等可编译名称。
- 补全丢失的集合泛型、Mixin 转换桥接和 `Function.apply`。
- 恢复 `AbstractTexture.load(ResourceManager)`；移除反编译产生的无效 `@Override`。
- ClickGUI 单项模式列表显式传入 `String[]`，避免被重载解析为文本设置。
- 以 `LinkedHashSet` 替换两处仅使用标准集合操作的 ANTLR `OrderedHashSet`，避免给发布包引入未打包的运行依赖。
- 对 Minecraft 1.20.1 使用 `ResourceLocation` 构造器，纹理路径与资源目录统一小写；资源字节内容保留。
- 使用已有 `NativeImageAccessor` 获取字体像素，避免生产环境字段反射失败。
- 按参数与调用行为恢复 Windows JNA 函数名：窗口枚举、窗口标题、进程名查询和句柄释放。

## 验证范围

归档验证结果见 `verification.json`。原始下载路径在恢复期间不再存在；最终归档以之前保留的映射后字节码快照核对，原始文件哈希为最初读取结果。快照保留了原始资源内容。

确认项目包含 343 个 Java 文件；原输入和恢复发布包均包含 410 个顶层及内部 class。54 个 Mixin 配置条目存在，49 个原 SRG Mixin 成员在发布包中恢复。除生成清单与资源目录大小写外，资源内容与快照逐字节一致。

开发客户端已在 Windows 11 / JDK 17 / Forge 47.3.0 下实际启动：Forge 和 Mixin 加载、客户端模块初始化、字体加载、声音引擎和纹理图集创建完成，随后正常退出并保存配置；`runClient` 返回 `BUILD SUCCESSFUL`。测试用开发账号为 `Dev`，未进入服务器或验证所有模块。启动检查跳过批量 Minecraft 资源下载，并补齐了标题全景资源；完整首次运行仍应让 Gradle 下载剩余资源。

最终构建与启动摘要见 `build-validation.txt`。

这不是原始源码的逐字恢复。源码注释、局部变量名和未保留的原始符号不能从此 JAR 唯一推导。启动测试不代表全部模块、世界渲染、联机协议或音乐 API 已回归验证。

`verify_jar.py` 可用原 JAR 和恢复 JAR 再次检查资源与 Mixin 成员；`--mapped-input` 支持配合 `mixin-reobf.tsrg` 核对映射快照。`tools/Remap.java` 是实际使用的字节码映射工具，需要 ASM / ASM Commons / ASM Tree，参数依次为输入 JAR、输出 JAR、类名 TSV、SRG-to-official TSRG。

新增或改写 `@Shadow` / `@Overwrite` 时需要同步维护 `mixin-reobf.tsrg`，或改用 MixinGradle Annotation Processor 生成映射；此文件是本次恢复的发布映射，不会自动分析新成员。
