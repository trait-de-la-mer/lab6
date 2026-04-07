package tools;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import Collection.*;
import app.Main;

public class MiddleManager {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private static final LinkedList<String> history = new LinkedList<>();
    public void sendCom(String... nameCommand) { // проверяет на арг-ты и просит ввести сложные арг-ты и дает команду отпр
        if (nameCommand != null && nameCommand.length != 0 && !Objects.equals(nameCommand[0], "")
                && Main.commands.containsKey(nameCommand[0])) {
            int len = nameCommand.length;
            String name = nameCommand[0];
            int argCount = Main.commands.get(name).getArgCount();
            Class<?> type = Main.commands.get(name).getObjectClass();
            if (type == null) {
                Requester<Objects> requester = new Requester<>();
                pullRequest(requester, name, null);
            } else if (!type.isPrimitive()) {
                if (len > 1) {Consoll.printSmt("Тут аргумент не очень нужны, но ладно");}
                if (type.equals(LabWork.class)) {
                    LabWork labWork = Main.commands.get(nameCommand[0]).makeLab();
                    Requester<LabWork> requester = new Requester<>();
                    pullRequest(requester, nameCommand[0], labWork);
                    sendObj(requester);
                } else if (type.equals(Person.class)) {
                    Requester<Person> requester = new Requester<>();
                    Person person = Main.commands.get(nameCommand[0]).makePerson();
                    pullRequest(requester, nameCommand[0], person);
                    sendObj(requester);
                } else {
                    Consoll.printSmt("нужен неизвестный тип");
                }
            } else {
                if (len - 1 == argCount) {
                    //тут можно сделать чтобы несколько аргуметнов отсылалось при необзодимости но пока незачем
                    String arg = nameCommand[1];
                    Requester<String> requester = new Requester<>();
                    pullRequest(requester, nameCommand[0], arg);
                } else {Consoll.printSmt("что-то не так с кол-ом аргументов");}
            }
        } else {Consoll.printSmt("уверен что написал правильно?");}
    }

    public <T> void pullRequest(Requester<T> requester, String name, T obj){
            requester.setArgs(obj);
            requester.setCommand(name);
    }
    public MiddleManager(int port) throws IOException {
        socket = new Socket("localhost", port);
        Consoll.printSmt("Вроде подключились по порту: " + port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public <T> void sendObj(Requester<T> requester) {
        try {
            out.writeObject(requester);
            out.flush();
            System.out.println("Отправлено");
            // Requester<T> response = (Requester<T>) in.readObject();
            System.out.println("Получено");
            //TODO ретернуть и обработать ответ
        } catch (EOFException e){
            System.out.println("Сервер отлючился :(");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
