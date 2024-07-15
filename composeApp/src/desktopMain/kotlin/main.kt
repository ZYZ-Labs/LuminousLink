import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import utils.ClipboardUtil
import utils.NotificationUtil

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LuminousLink",
    ) {
        App()

    }
    ClipboardUtil.startClipboardListener(object : ClipboardUtil.ClipboardListener {
        override fun onClipboardChange(newContent: String) {
            NotificationUtil.showNotification("复制的内容",newContent)
            println("Clipboard changed: $newContent")
        }
    })
}