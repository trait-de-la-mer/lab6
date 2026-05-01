package tools;

import Collection.LabWork;
import Collection.Person;
import Commands.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectManager {
    /**
     * создаем и закрываем с клиентом подключение при необходимости
     * команды выполняются тут
     *
     * ща попробую на вход дать командный менеджер чтобы через него выполнять окманды
     */
    private Socket sock;
    private ServerSocket serv;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private CommandManager commandManager;

    public ConnectManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void start(int port) {
        try {
            serv = new ServerSocket(port);
            System.out.println("Сервер запущен на порту " + port);
            while (true) {
                try {
                    handleClient();
                } catch (IOException e) {
                    System.err.println("Ошибка клиента: " + e.getMessage());
                    closeClientResources();
                } catch (ClassNotFoundException e) {
                    System.err.println("что-то с классом чтения");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("скорее всего порт занят (connectNanager while)");
            closeClientResources();
        }
    }

    private void handleClient() throws IOException, ClassNotFoundException {
        System.out.println("Ожидание клиента...");
        sock = serv.accept();
        System.out.println("Подключен");
        in = new ObjectInputStream(sock.getInputStream());
        out = new ObjectOutputStream(sock.getOutputStream());
        while (true) {
            Object obj = in.readObject();
            if (obj instanceof Requester req) {
                System.out.println("Получено: " + req);
                String name = req.getCommand();
                Command com = commandManager.getCommands().get(name);
                if (req.getArgs() instanceof LabWork lab) {
                    lab = (LabWork) req.getArgs();
                    String text = com.execute(lab);
                    sendSmt(text);
                } else if (req.getArgs() instanceof Person person) {
                    person = (Person) req.getArgs();
                } else if (req.getArgs() instanceof String str) {
                }
            }
            //TODO вызвать мапу команд и выполнить команду с аргументом
        }
    }

    private void closeClientResources() {
        try { if (in != null) in.close(); } catch (IOException ignored) {}
        try { if (out != null) out.close(); } catch (IOException ignored) {}
        try { if (sock != null) sock.close(); } catch (IOException ignored) {}
    }

    public void stop() throws IOException {
        closeClientResources();
        if (serv != null && !serv.isClosed()) serv.close();
    }

    public void sendSmt(String text) throws IOException {
        Requester<String> requester = new Requester<>();
        requester.setArgs(text);
        out.writeObject(requester);
        out.flush();
    }
}