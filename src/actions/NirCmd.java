package actions;

import java.io.IOException;

public class NirCmd
{


    public void exeNormalCommand(String command)
    {
        try
        {
//            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d nircmd && nircmd.exe "+command);
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", " nircmd.exe "+command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void moveCursor(String x , String y)
    {
        try
        {
//            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d nircmd && nircmd.exe movecursor "+x+" "+y);
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", " nircmd.exe movecursor "+x+" "+y);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}


