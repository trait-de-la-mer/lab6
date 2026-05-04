package Connecting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionAcceptor {
        private ServerSocket serv;
        public void start(int port) throws IOException {
            serv = new ServerSocket(port);
            System.out.println("Сервер запущен на порту " + port);
        }

        public Socket acceptClient() throws IOException {
            System.out.println("Ожидание клиента...");
            Socket client = serv.accept();
            System.out.println("Подключен: " + client.getInetAddress());
            return client;
        }

        public void stop() throws IOException {
            if (serv != null && !serv.isClosed()) {
                serv.close();
                System.out.println("Сокет закрыт");
            }
        }

        public boolean isRunning() {
            return serv != null && !serv.isClosed();
        }
}
