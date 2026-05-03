package tools;

import Commands.Command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class CommandManager {
    private static final HashMap<String, Command<?>> commands= new HashMap<>();
    private static final LinkedList<String> history = new LinkedList<>();

    public CommandManager(Command... needComands) {
        for (Command com : needComands){
            commands.put(com.getName().toLowerCase(Locale.ENGLISH), com);
        }
    }

    public <T> void executC(String name, T arg){
        Command cmd = commands.get(name);
        try {
            cmd.execute(arg);
            if (history.size() == 5) {
                history.removeLast();
            }
            history.addFirst(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static HashMap<String, Command<?>> getCommands() {
        return commands;
    }

    public static LinkedList<String> getHistory() {
        return history;
    }
}

