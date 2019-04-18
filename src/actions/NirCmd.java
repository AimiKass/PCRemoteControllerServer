package actions;

import java.io.IOException;

public class NirCmd
{
    private String path;

    public NirCmd(String path)
    {
        this.path = path;
    }

    public void exeNormalCommand(String command)
    {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \""+path+"\" && nircmd.exe "+command);
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
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \""+path+"\" && nircmd.exe movecursor "+x+" "+y);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}


