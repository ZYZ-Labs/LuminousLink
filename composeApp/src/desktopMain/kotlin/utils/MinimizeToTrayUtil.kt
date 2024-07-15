package utils

import androidx.compose.ui.window.ApplicationScope
import java.awt.AWTException
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JOptionPane

object MinimizeToTrayUtil {
    fun minimizeToTray(scope: ApplicationScope, onRestore: () -> Unit) {
        if (!SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(null, "System tray is not supported!", "Error", JOptionPane.ERROR_MESSAGE)
            return
        }

        val tray = SystemTray.getSystemTray()
        val image: BufferedImage = ImageIO.read(File("C:\\Users\\zhouwj\\Pictures\\Saved Pictures\\logo.png"))
        val trayIcon = TrayIcon(image, "Application")

        val restoreItem = java.awt.MenuItem("Restore")
        restoreItem.addActionListener {
            onRestore()
            tray.remove(trayIcon)
        }

        val exitItem = java.awt.MenuItem("Exit")
        exitItem.addActionListener {
            tray.remove(trayIcon)
            scope.exitApplication()
        }

        val popup = java.awt.PopupMenu()
        popup.add(restoreItem)
        popup.add(exitItem)
        trayIcon.popupMenu = popup

        try {
            tray.add(trayIcon)
            scope.exitApplication()
        } catch (e: AWTException) {
            e.printStackTrace()
        }
    }
}
