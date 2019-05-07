package actions;

import notifications.WindowsNotification;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NirCmd
{

    private static JFrame frame = new JFrame();


    public void exeNormalCommand(String command)
    {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", " nircmd.exe "+command);
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
                JOptionPane.showMessageDialog(frame, "NirCmd class , exeNormalCommand function error");
            }
        }
    }


    public void moveCursor(String x , String y)
    {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", " nircmd.exe movecursor "+x+" "+y);
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
                JOptionPane.showMessageDialog(frame, "NirCmd class , moveCursor function error");
            }
        }
    }

}


