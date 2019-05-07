package actions;

import notifications.WindowsNotification;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CmdCommands
{
    private static JFrame frame = new JFrame();

    public void execute(String command) throws AWTException
    {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            try {
                e.printStackTrace();
                WindowsNotification windowsNotification = new WindowsNotification();
                windowsNotification.displayTray(e.getMessage());
            }catch (AWTException e1)
            {
                JOptionPane.showMessageDialog(frame, "CmdCommands class, execute function error");
            }
        }
    }
}
