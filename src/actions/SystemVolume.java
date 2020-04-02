package actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemVolume
{

    public int getVolume() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("cmd.exe", "/c", "src\\adjust_get_current_system_volume_vista_plus.exe");
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));


        return Integer.parseInt(reader.readLine());
    }
}
