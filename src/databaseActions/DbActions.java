package databaseActions;

import notifications.WindowsNotification;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DbActions
{

    private Path databasePath;
    private Path databasePathInCaseOfArtifact;

    public DbActions(Path databasePath,Path databasePathInCaseOfArtifact)
    {
        this.databasePath = databasePath;
        this.databasePathInCaseOfArtifact = databasePathInCaseOfArtifact;
    }


    public int getPort() throws AWTException
    {

        int port =7800;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(databasePath.toString()));
            port = Integer.parseInt(reader.readLine());
            reader.close();

        } catch (Exception e)
        {
            try
            {
                Path newPath = Paths.get(databasePathInCaseOfArtifact.toString());
                BufferedReader reader = new BufferedReader(new FileReader(newPath.toString()));
                port = Integer.parseInt(reader.readLine());
                reader.close();

            }catch (Exception e2)
            {
                WindowsNotification windowsNotification = new WindowsNotification();
                windowsNotification.displayTray(e2.getMessage());
                windowsNotification.displayTray("Server crashed");
                System.exit(0);
            }

        }

        return port;
    }


    public void setPort(String port) throws AWTException
    {
        try
        {
            // TODO: 4/24/2019 #1 check validity !!!!!
            // TODO: 4/24/2019 split in new class
            BufferedWriter writer = new BufferedWriter(new FileWriter(databasePath.toString()));
            writer.write(port);
            writer.close();
        }catch (Exception e)
        {
            try
            {
                Path newPath = Paths.get(databasePathInCaseOfArtifact.toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(newPath.toString()));
                writer.write(port);
                writer.close();

            }catch (Exception e2)
            {
                WindowsNotification windowsNotification = new WindowsNotification();
                windowsNotification.displayTray(e2.getMessage());
                windowsNotification.displayTray("Server crashed");
                System.exit(0);
            }
        }
    }


}