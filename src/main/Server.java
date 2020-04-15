package main;

import actions.CmdCommands;
import actions.NirCmd;
import actions.SendToClient;
import database.Database;
import notifications.WindowsNotification;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server
{

    private static Process process = null;
    static JFrame frame = new JFrame();

    private static final String splitCharacter = "@";
    private static final Path databasePath = Paths.get("src\\database\\portNumber.txt");
    private static final Path databasePathInCaseOfArtifact = Paths.get("newDatabase\\portNumber.txt");

    private static final int EXIT_SERVER = 0;
    private static final int MOVE_CURSOR = 1;
    private static final int VOLUME_CONF = 2;
    private static final int MUTE_UNMUTE = 3;
    private static final int PLAY_PAUSE = 4;
    private static final int PREVIOUS_SONG = 5;
    private static final int NEXT_SONG = 6;
    private static final int SPEAK = 7;
    private static final int MONITOR_ON = 8;
    private static final int MONITOR_OFF = 81;
    private static final int MIN_TILL_MON_OFF = 9;
    private static final int SHUTDOWN = 10;
    private static final int RESTART = 11;
    private static final int MIN_TILL_PC_OFF = 12;
    private static final int RIGHT_CLICK = 14;
    private static final int LEFT_CLICK = 15;
    private static final int CMD_COMMAND = 16;
    private static final int KEYBOARD = 17;
    private static final int GET_SYSTEM_VOLUME = 18;
    private static final int VERIFICATION_MESSAGE =19;
    private static final int SPEAKER_ON =20;
    private static final int SPEAKER_OFF =201;


    public static void main(String[] args)
    {
        Database database = new Database(databasePath, databasePathInCaseOfArtifact);

        if (!Files.exists(databasePath) && !Files.exists(databasePathInCaseOfArtifact))
            database.setPort("7800");


        try
        {
            int port = database.getPort();

            sendWindowsMessage("Server Is Running "+port);


            ServerSocket serverSocket = new ServerSocket(port);

            while (true)
            {
                Socket socket = serverSocket.accept();
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String message = bufferedReader.readLine();

                NirCmd nircmd = new NirCmd();
                CmdCommands cmdCommands = new CmdCommands();
                SendToClient sendToClient = new SendToClient(socket);



                switch (Integer.parseInt(message.split(splitCharacter)[0]))
                {

                    case MOVE_CURSOR:
                        nircmd.moveCursor(message.split(splitCharacter)[1].split("#")[0], message.split("#")[1]);
                        break;
                    case VOLUME_CONF:
                        System.out.println("VOLUME_CONF executed");
                        nircmd.exeNormalCommand("setsysvolume "+ message.split(splitCharacter)[1]);
                        break;
                    case GET_SYSTEM_VOLUME:
                        sendToClient.systemsVolume();
                        break;
                    case SPEAKER_ON:
                        System.out.println("SPEAKER_ON executed");
                        ProcessBuilder processBuilder = new ProcessBuilder("java","-jar","src\\PCRemContCommunicationServer.jar");
                        process = processBuilder.start();
                        break;
                    case SPEAKER_OFF:
                        System.out.println("SPEAKER_OFF executed");
                        process.destroy();
                        if (process.isAlive())
                            process.destroyForcibly();
                        break;
                    case KEYBOARD:
                        // TODO: 4/14/2020 do it with Robot Class !
                        System.out.println("KEYBOARD executed");
                        nircmd.exeNormalCommand("sendkey "+ message.split(splitCharacter)[1]+" press");
                        break;
                    case RIGHT_CLICK:
                        System.out.println("RIGHT_CLICK executed");
                        nircmd.exeNormalCommand("sendmouse right click");
                        break;
                    case LEFT_CLICK:
                        System.out.println("LEFT_CLICK executed");
                        nircmd.exeNormalCommand("sendmouse left click");
                        break;
                    case PREVIOUS_SONG:
                        System.out.println("PREVIOUS_SONG executed");
                        nircmd.exeNormalCommand("sendkey 0xB1 press");
                        break;
                    case NEXT_SONG:
                        System.out.println("NEXT_SONG executed");
                        nircmd.exeNormalCommand("sendkey 0xB0 press");
                        break;
                    case PLAY_PAUSE:
                        System.out.println("PLAY_PAUSE executed");
                        nircmd.exeNormalCommand("sendkey 0xB3 press");
                        break;
                    case MUTE_UNMUTE:
                        System.out.println("MUTE_UNMUTE executed");
                        nircmd.exeNormalCommand("mutesysvolume 2");
                        break;
                    case SPEAK:
                        System.out.println("SPEAK executed");
                        nircmd.exeNormalCommand("speak text \""+ message.split(splitCharacter)[1]+"\"");
                        break;
                    case MONITOR_ON:
                        System.out.println("MONITOR_ON executed");
                        nircmd.exeNormalCommand("monitor on");
                        break;
                    case MONITOR_OFF:
                        System.out.println("MONITOR_OFF executed");
                        nircmd.exeNormalCommand("monitor off");
                        break;
                    case MIN_TILL_MON_OFF:
                        System.out.println("MIN_TILL_MON_OFF executed");
                        System.out.println("cmdwait "+ message.split(splitCharacter)[1]+" monitor off");
                        nircmd.exeNormalCommand("cmdwait "+ message.split(splitCharacter)[1]+" monitor off");
                        break;
                    case SHUTDOWN:
                        System.out.println("SHUTDOWN executed");
                        nircmd.exeNormalCommand("exitwin poweroff");
                        break;
                    case RESTART:
                        System.out.println("RESTART executed");
                        cmdCommands.execute("shutdown -r");
                        break;
                    case MIN_TILL_PC_OFF:
                        System.out.println("MIN_TILL_PC_OFF executed");
                        cmdCommands.execute("shutdown -s -t "+ message.split(splitCharacter)[1]);
                        break;
                    case CMD_COMMAND:
                        System.out.println("CMD_COMMAND executed");
                        cmdCommands.execute(message.split(splitCharacter)[1]);
                        break;
                    case VERIFICATION_MESSAGE:
                        System.out.println("VERIFICATION_MESSAGE executed");
                        sendToClient.verificationMsg();
                        break;
                    case EXIT_SERVER:
                        System.out.println("EXIT_SERVER executed");
                        sendWindowsMessage("Server Closed");
                        System.exit(0);
                        break;
                    default:
                        sendWindowsMessage("Server Closed");
                        System.out.println("default executed");
                        System.exit(0);

                }
            }
        } catch (Exception e)
        {
            sendWindowsMessage("Server crashed");
            sendWindowsMessage(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

    }

    private static  void sendWindowsMessage(String text)
    {
        try {
            WindowsNotification windowsNotification = new WindowsNotification();
            windowsNotification.displayTray(text);
        }catch (AWTException e)
        {
            JOptionPane.showMessageDialog(frame, "PC Remote Controller Crushed");
        }
    }
}
