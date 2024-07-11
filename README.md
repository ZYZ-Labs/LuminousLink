# InterWorldLink

InterWorldLink 是一个多平台互联项目，旨在实现类似 iPhone 和 Mac 之间的无缝互联。该项目使用 Kotlin 和
Jetpack Compose 实现跨平台代码共享，支持 Android、iOS、桌面和 Web 平台。

## 特性

- **优先使用 Wi-Fi 通信**：在设备间传输数据时，首先尝试使用 Wi-Fi 进行高速传输。
- **备用蓝牙传输**：如果 Wi-Fi 不可用，将自动切换到蓝牙传输。
- **远程服务器同步**：在无法使用 Wi-Fi 和蓝牙的情况下，最终使用远程服务器进行数据同步（云服务暂未开放）。

## 项目简介

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the
  platform-specific folders here too.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack
channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them
on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle
task.

## 安装和使用

### 前提条件

- [Android Studio](https://developer.android.com/studio) (用于 Android 开发)
- [Xcode](https://developer.apple.com/xcode/) (用于 iOS 开发)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (推荐用于多平台开发)
- [Node.js](https://nodejs.org/) (用于 Web 开发)

### 构建和运行

1. **克隆项目**：

    ```sh
    git clone https://github.com/ZYZ-Labs/InterWorldLink.git
    cd InterWorldLink
    ```

2. **构建共享模块**：

   ```sh
    ./gradlew :shared:build
    ```
   
3. **运行 Android 应用**:

    ```sh
   ./gradlew :androidApp:assembleDebug
   ```
   
4. **运行 iOS 应用**:

   在 Xcode 中打开 iosApp 项目，并运行。

5. **运行桌面应用**:

    ```sh
    ./gradlew :desktopApp:run
    ```
   
6. **运行 Web 应用**:

    ```sh
    ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
    ```

## 贡献

欢迎贡献代码和提出建议！请先阅读 CONTRIBUTING.md 了解详情。

## 许可证

MIT License

```
MIT License

Copyright (c) 2024 ZYZ-Labs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 联系我们

如果你有任何问题或建议，请通过 issues 提交。