package actions;

import java.io.IOException;

public class SongConfigurations
{
    // TODO: 4/16/2019 execute With AutoHotkey
    public void nextSong()
    {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \"D:\\IntellJ_Projects\\LatestServerToComWithAndroidPhone\\nircmd\" && nircmd.exe setsysvolume ");
            processBuilder.redirectErrorStream(true);
            processBuilder.start();


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void previousSong()
    {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \"D:\\IntellJ_Projects\\LatestServerToComWithAndroidPhone\\nircmd\" && nircmd.exe setsysvolume ");
            processBuilder.redirectErrorStream(true);
            processBuilder.start();


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void pauseSong()
    {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c", "cd /d \"D:\\IntellJ_Projects\\LatestServerToComWithAndroidPhone\\nircmd\" && nircmd.exe setsysvolume ");
            processBuilder.redirectErrorStream(true);
            processBuilder.start();


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
