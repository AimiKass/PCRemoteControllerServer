package actions;

import notifications.WindowsNotification;

import javax.swing.*;
import java.awt.*;

public class NirCmd
{

    private static JFrame frame = new JFrame();
    private  CmdCommands cmdCommand ;

    public void exeNormalCommand(String command)
    {
        try
        {
            cmdCommand = new CmdCommands();
            cmdCommand.execute("nircmd.exe "+command);

        }catch (AWTException e) {
            try {
                e.printStackTrace();
                WindowsNotification windowsNotification = new WindowsNotification();
                windowsNotification.displayTray(e.getMessage());
            } catch (AWTException e1) {
                JOptionPane.showMessageDialog(frame, "NirCmd class , exeNormalCommand function error");
            }
        }
    }


    public void moveCursor(String x , String y)
    {
        try
        {
            cmdCommand = new CmdCommands();
            cmdCommand.execute(" nircmd.exe movecursor "+x+" "+y);

        }catch (AWTException e)
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


