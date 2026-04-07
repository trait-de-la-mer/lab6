import tools.Requester;

import java.io.*;
import java.net.*;

public class Resever {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 6789;
        try (ServerSocket serv = new ServerSocket(port)) {
            System.out.println("Сервер ожидает подключение на порту " + port);
            try (Socket sock = serv.accept();
                 ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
                 ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream())) {
                Object obj = ois.readObject();
                if (obj instanceof Requester req) {
                    System.out.println("Обработан: " + req);
                } else {
                    System.out.println("что-то не так с типом при приемке");
                }
            }
        }
    }
}