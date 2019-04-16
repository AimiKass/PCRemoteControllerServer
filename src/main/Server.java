package main;

import actions.CmdCommands;
import actions.VolumeConfigurations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    private static Socket socket;
    private static ServerSocket serverSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String message ;



    public static void main(String[] args)
    {

        try
        {
            serverSocket = new ServerSocket(7800);

            while (true)
            {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                message = bufferedReader.readLine();


                switch (Integer.parseInt(message.split("-")[0]))
                {

                    //CMD command
                    case 1:
                        CmdCommands cmdCommands = new CmdCommands();
                        cmdCommands.execute(message.split("-")[1]);
                        break;

                    //Volume configurations
                    case 2:
                        VolumeConfigurations volumeConfigurations = new VolumeConfigurations();
                        volumeConfigurations.setVolume(message.split("-")[1]);
                        break;

                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
