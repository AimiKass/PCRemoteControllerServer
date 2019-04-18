package main;

import actions.CmdCommands;
import actions.NirCmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket socket;
    private static ServerSocket serverSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String message;
    private static boolean serverIs;

    private static final int EXIT_SERVER = 0;
    private static final int MOVE_CURSOR = 1;
    private static final int VOLUME_CONF = 2;
    private static final int MUTE_UNMUTE = 3;
    private static final int PLAY_PAUSE = 4;
    private static final int PREVIOUS_SONG = 5;
    private static final int NEXT_SONG = 6;
    private static final int SPEAK = 7;
    private static final int MONITOR_ON_OFF = 8;
    private static final int MIN_TILL_MON_OFF = 9;
    private static final int SHUTDOWN = 10;
    private static final int RESTART = 11;
    private static final int MIN_TILL_PC_OFF = 12;
    private static final int RIGHT_CLICK = 14;
    private static final int LEFT_CLICK = 15;
    private static final int CMD_COMMAND = 16;

    private static String path = "D:\\IntellJ_Projects\\LatestServerToComWithAndroidPhone\\nircmd";


    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(7800);
            serverIs = true;

            while (serverIs) {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                message = bufferedReader.readLine();

                NirCmd nircmd = new NirCmd(path);
                CmdCommands cmdCommands = new CmdCommands();

                switch (Integer.parseInt(message.split("@")[0])) {

                    case MOVE_CURSOR:
                        System.out.println("MOVE_CURSOR executed");
                        String y = message.split("#")[1];
                        String x = message.split("@")[1].split("#")[0];
                        nircmd.moveCursor(x,y);
                        break;
                    case VOLUME_CONF:
                        System.out.println("VOLUME_CONF executed");
                        nircmd.exeNormalCommand("setsysvolume "+message.split("@")[1]);
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
                        // TODO: 4/18/2019 need autoHotKey
                        break;
                    case NEXT_SONG:
                        System.out.println("NEXT_SONG executed");
                        // TODO: 4/18/2019 need autoHotKey
                        break;
                    case PLAY_PAUSE:
                        System.out.println("PLAY_PAUSE executed");
                        // TODO: 4/18/2019 need autoHotKey
                        break;
                    case MUTE_UNMUTE:
                        System.out.println("MUTE_UNMUTE executed");
                        nircmd.exeNormalCommand("mutesysvolume 2");
                        break;
                    case SPEAK:
                        System.out.println("SPEAK executed");
                        nircmd.exeNormalCommand("speak text "+message.split("@")[1]);
                        break;
                    case MONITOR_ON_OFF:
                        System.out.println("MONITOR_ON_OFF executed");
                        nircmd.exeNormalCommand("monitor async_off");
                    case MIN_TILL_MON_OFF:
                        System.out.println("MIN_TILL_MON_OFF executed");
                        nircmd.exeNormalCommand("cmdwait "+message.split("@")[1]+" monitor off");
                        break;
                    case SHUTDOWN:
                        System.out.println("SHUTDOWN executed");
                        nircmd.exeNormalCommand("exitwin poweroff");
                        break;
                    case RESTART:
                        System.out.println("RESTART executed");
                        cmdCommands.execute("shutdown -r -t "+message.split("@")[1]);
                        break;
                    case MIN_TILL_PC_OFF:
                        System.out.println("MIN_TILL_PC_OFF executed");
                        nircmd.exeNormalCommand("exitwin "+message.split("@")[1]+" poweroff");
                        break;
                    case CMD_COMMAND:
                        System.out.println("CMD_COMMAND executed");
                        cmdCommands.execute(message.split("@")[1]);
                        break;
                    case EXIT_SERVER:
                        System.out.println("EXIT_SERVER executed");
                        serverIs = false;
                    default:
                        System.out.println("default executed");
                        serverIs = false;

                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
