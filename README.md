# OpenEixClient — recovered Forge project

从 `EixClient-1.20.1-Forge-9.3-obf.jar` 恢复的 Minecraft 1.20.1 Forge 客户端工程。

## 在 IntelliJ IDEA 中打开

1. 安装 JDK 17，在 IDEA 中打开本目录或 `build.gradle`。
2. 将 Project SDK 和 Gradle JVM 都设为 JDK 17，使用项目自带的 Gradle Wrapper。
3. 等待 Gradle 导入完成，可直接运行项目共享的 `Minecraft Client` 配置；也可执行 `genIntellijRuns` 后使用生成的 `runClient` 配置。

```powershell
./gradlew.bat genIntellijRuns
./gradlew.bat runClient
./gradlew.bat clean build
```

Linux/macOS 可使用 `bash gradlew`，但原客户端包含 Windows Skija 原生库，音乐窗口识别也使用 Windows API；目前以 Windows x64 为目标环境。

从终端构建时，`JAVA_HOME` 也必须指向 JDK 17；IDEA 的 SDK 设置不会自动修改终端环境变量。

构建产物位于 `build/libs/EixClient-1.20.1-Forge-9.3-recovered.jar`，放入 Minecraft 1.20.1 Forge 的 `mods` 目录。开发环境使用 Forge 47.3.0、Mojang official 1.20.1 mappings、Gradle 8.8。首次启动会下载 Minecraft 资源。

## 恢复内容和限制

- 343 个 Java 源文件，完整字体、图片、配置、Mixin/refmap 和 9 个内嵌依赖。
- 功能模块根据保留的 `ModuleInfo` 注解恢复名称；其余无法确定用途的类保留可追溯的 `Recovered...` 名称，映射见 `recovery/class-map.tsv`。
- Minecraft SRG 字段和方法转换为开发环境可读名称；发布时由 ForgeGradle 和 `recovery/mixin-reobf.tsrg` 恢复生产映射。
- 修复反编译产生的泛型、内部类冲突、关键字字段、Mixin 类型转换和函数接口实现。
- 将不适用于 1.20.1 的 `ResourceLocation.fromNamespaceAndPath` 调用换成等价构造器；统一纹理目录大小写，修复字体像素访问和被混淆的 JNA Windows 导出名。

这份代码是可维护的反编译重建结果。被混淆删除的原始变量名、部分方法名、注释和原始构建脚本无法逐字恢复。完整游戏行为需要实际运行和逐模块回归验证；编译通过不能证明所有功能与原工程完全相同。

`recovery/REPORT.md` 记录恢复依据和验证范围。现有仓库 `LICENSE` 与原 JAR 的 `mods.toml` 许可声明均保留，未自行重新声明第三方依赖的许可。

已验证：完整 Forge 构建、IDEA 启动配置生成、开发客户端初始化及正常退出。尚未逐模块回归测试。

参考：[ForgeGradle 6 文档](https://docs.minecraftforge.net/en/fg-6.x/)、[MixinGradle 映射说明](https://github.com/SpongePowered/MixinGradle)。
