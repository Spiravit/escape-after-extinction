package group7.levels;

import group7.Game;

import java.io.*;

public class GameSaver
{
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private int highestLevelCompleted;
    private Game game = new Game();

    public void save_game(Game game)
    {
        // this is the save game
        try
        {
            fileOutputStream = new FileOutputStream("game.saver");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        //this is try to save the highest level
        try
        {
            FileOutputStream fileStream = new FileOutputStream("highestLevel.dat");
            DataOutputStream dataStream = new DataOutputStream(fileStream);
            dataStream.writeInt(highestLevelCompleted);
            dataStream.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void load_game()
    {
        try
        {
            fileInputStream = new FileInputStream("game.saver");
            objectInputStream= new ObjectInputStream(fileInputStream);
            game = (Game) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (ClassNotFoundException | IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
