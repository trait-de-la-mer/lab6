package start;

import Commands.*;
import tools.CollectionManager;
import tools.CommandManager;
import tools.ConnectManager;

public class Resever {
    public static void main(String[] args) {
        int port = 6789;
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(
                new Add(collectionManager),
                new Remove(collectionManager),
                new Clear(collectionManager),
                new CountLessMin(collectionManager),
                new Head(collectionManager),
                new History(collectionManager),
                new Help(collectionManager),
                new Info(collectionManager),
                new Show(collectionManager)
        );
        ConnectManager cm = new ConnectManager(commandManager);
        cm.start(port);
    }
}