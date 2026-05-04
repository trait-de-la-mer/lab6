package Connecting;

import Commands.Command;
import tools.Requester;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RequestReader {
    private ObjectInputStream in;

    public void init(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
    }

    public Requester<?> readRequest() throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if (obj instanceof Requester<?> req) {
            System.out.println("Получено: " + req);
            return req;
        }
        throw new ClassCastException("неизвестный тип: " + obj.getClass());
    }

    public void close() {
        try { if (in != null) in.close(); } catch (IOException ignored) {}
    }
}
