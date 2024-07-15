package utils

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.io.IOException

object ClipboardUtil {
    private var lastClipboardContent: String? = null

    interface ClipboardListener {
        fun onClipboardChange(newContent: String)
    }

    fun startClipboardListener(listener: ClipboardListener) {
        Thread {
            val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard

            while (true) {
                try {
                    val currentContent = getClipboardText(clipboard)
                    if (currentContent != lastClipboardContent) {
                        lastClipboardContent = currentContent
                        listener.onClipboardChange(currentContent)
                    }
                    Thread.sleep(1000) // Check clipboard content every second
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.apply {
            isDaemon = true
        }.start()
    }

    fun getClipboardText(): String {
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        return getClipboardText(clipboard)
    }

    private fun getClipboardText(clipboard: Clipboard): String {
        try {
            val transferable: Transferable = clipboard.getContents(null)
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return transferable.getTransferData(DataFlavor.stringFlavor) as String
            }
        } catch (e: UnsupportedFlavorException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}
