package app;

import Collection.LabWork;
import tools.Command;
import tools.Consoll;
import tools.MiddleManager;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static  HashMap<String, Command> commands = new HashMap<>();
    public static void main(String[] args) {
        commands.put("add", new Command(1, LabWork.class));
        commands.put("show", new Command(0));
        commands.put("help", new Command(0));
        commands.put("remove", new Command(1, int.class));
        MiddleManager mm = new MiddleManager();
        Consoll consoll = new Consoll(mm);
            consoll.startConsole();

    }
}