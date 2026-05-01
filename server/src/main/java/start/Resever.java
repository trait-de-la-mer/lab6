package start;

import Commands.Add;
import Commands.Command;
import Commands.Remove;
import tools.CollectionManager;
import tools.CommandManager;
import tools.ConnectManager;

public class Resever {
    public static void main(String[] args) {
        int port = 6789;
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(
                new Add(collectionManager),
                new Remove(collectionManager)
        );
        ConnectManager cm = new ConnectManager(commandManager);
        cm.start(port);
    }
}