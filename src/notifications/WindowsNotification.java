package notifications;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WindowsNotification
{


    public void displayTray(String text) throws AWTException
    {
        final PopupMenu popup = new PopupMenu();
        SystemTray tray = SystemTray.getSystemTray();


        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File("photos\\appPhotos\\serverTaskBarIcon.png"));

        } catch (IOException e)
        {
            try {
                image = ImageIO.read(new File("icons\\serverTaskBarIcon.png"));
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }


        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("PC Remote Controller");
        tray.add(trayIcon);

//        MenuItem aboutItem = new MenuItem("About");
//        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
//        Menu displayMenu = new Menu("Display");
//        MenuItem errorItem = new MenuItem("Error");
//        MenuItem warningItem = new MenuItem("Warning");
//        MenuItem infoItem = new MenuItem("Info");
//        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");


        ActionListener menuItemExitActionListener = e ->
        {
            trayIcon.displayMessage("PCRemoteController", "Server Closed", TrayIcon.MessageType.INFO);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        };

        exitItem.addActionListener(menuItemExitActionListener);
//        popup.add(aboutItem);
//        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
        popup.add(exitItem);


        trayIcon.setPopupMenu(popup);


        trayIcon.displayMessage("PCRemoteController", text, TrayIcon.MessageType.INFO);

    }
}
