package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToClient
{
    private Socket socket;

    public SendToClient(Socket socket)
    {
        this.socket = socket;
    }

    public void verificationMsg() throws IOException
    {
        // TODO: 3/18/2020 set it properly
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("697");
        printWriter.flush();
        printWriter.close();
    }


    public void systemsVolume() throws IOException
    {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        SystemVolume systemVolume = new SystemVolume();
        printWriter.println(systemVolume.getVolume());
        printWriter.flush();
        printWriter.close();
    }
}
