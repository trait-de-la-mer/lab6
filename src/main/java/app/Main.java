package app;

import Collection.LabWork;
import tools.Command;
import tools.Consoll;
import tools.MiddleManager;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static  HashMap<String, Command> commands = new HashMap<>();
    private static int port = 6789;
    public static void main(String[] args) {
        commands.put("add", new Command(1, LabWork.class));
        commands.put("show", new Command(0));
        commands.put("help", new Command(0));
        commands.put("remove", new Command(1, int.class));
        MiddleManager mm = null;
        try{
            mm = new MiddleManager(port);
        } catch(IOException e) {
            Consoll.printSmt("нет подключения, попробуйте позже");
            System.exit(0);
        } catch (Exception e) {
            System.exit(-9999999);
        }
        Consoll consoll = new Consoll(mm);
            consoll.startConsole();
    }
}