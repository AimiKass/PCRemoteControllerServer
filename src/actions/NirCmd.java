package actions;

import notifications.WindowsNotification;

import java.awt.*;
import java.io.IOException;

public class NirCmd
{


    public void exeNormalCommand(String command) throws AWTException {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", " nircmd.exe "+command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            e.printStackTrace();
            WindowsNotification windowsNotification = new WindowsNotification();
            windowsNotification.displayTray(e.getMessage());
        }
    }


    public void moveCursor(String x , String y) throws AWTException, IOException {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", " nircmd.exe movecursor "+x+" "+y);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            e.printStackTrace();
            WindowsNotification windowsNotification = new WindowsNotification();
            windowsNotification.displayTray(e.getMessage());
        }
    }

}


