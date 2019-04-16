package actions;

import java.io.IOException;

public class CmdCommands
{
    public void execute(String command)
    {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
