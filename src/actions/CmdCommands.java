package actions;

import notifications.WindowsNotification;

import java.awt.*;
import java.io.IOException;

public class CmdCommands
{
    public void execute(String command) throws AWTException {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
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
