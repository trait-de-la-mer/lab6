package tools;

import Collection.LabWork;
import Collection.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectManager {
    private Socket sock;
    private ServerSocket serv;
    private ObjectInputStream in;
    private ObjectOutputStream out;

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
            System.err.println("скорее всего порт занят");
            closeClientResources();
        }
    }

    private void handleClient() throws IOException, ClassNotFoundException {
        System.out.println("Ожидание клиента...");
        sock = serv.accept();
        System.out.println("Подключен");
        out = new ObjectOutputStream(sock.getOutputStream());
        in = new ObjectInputStream(sock.getInputStream());
        while (true) {
            Object obj = in.readObject();
            if (obj instanceof Requester req) {
                System.out.println("Получено: " + req);
                String name = req.getCommand();
                if (req.getArgs() instanceof LabWork lab) {
                    lab = (LabWork) req.getArgs();
                } else if (req.getArgs() instanceof Person person) {
                    person = (Person) req.getArgs();
                } else {
                }            }
        }
//        closeClientResources();
  //      System.out.println("Клиент отключён, ждём следующего...");
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
}