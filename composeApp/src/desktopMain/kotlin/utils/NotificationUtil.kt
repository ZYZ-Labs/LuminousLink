package utils

import java.awt.*
import java.awt.TrayIcon.MessageType
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object NotificationUtil {
    fun showNotification(title: String, message: String) {
        if (!SystemTray.isSupported()) {
            println("System tray is not supported!")
            return
        }

        val tray = SystemTray.getSystemTray()
        val image: BufferedImage = ImageIO.read(File("C:\\Users\\zhouwj\\Pictures\\Saved Pictures\\logo.png"))
        val trayIcon = TrayIcon(image, "Notification")
        trayIcon.isImageAutoSize = true

        try {
            tray.add(trayIcon)
            trayIcon.displayMessage(title, message, MessageType.INFO)

            // Delay for some time before removing the icon
            Thread.sleep(5000)
            tray.remove(trayIcon)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
