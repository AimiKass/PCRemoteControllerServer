package actions;

import java.io.IOException;

public class VolumeConfigurations
{
    public void setVolume(String units)
    {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \"D:\\IntellJ_Projects\\LatestServerToComWithAndroidPhone\\nircmd\" && nircmd.exe setsysvolume "+units);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void muteUnmute()
    {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \"D:\\IntellJ_Projects\\LatestServerToComWithAndroidPhone\\nircmd\" && nircmd.exe mutesysvolume 2");
            processBuilder.redirectErrorStream(true);
            processBuilder.start();


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
