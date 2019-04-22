package notifications;

import java.awt.*;

public class WindowsNotification
{


    public void displayTray(String text) throws AWTException
    {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("PCRemoteController", text, TrayIcon.MessageType.INFO);
    }
}
