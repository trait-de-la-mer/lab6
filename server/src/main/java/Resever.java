import tools.ConnectManager;

public class Resever {
    public static void main(String[] args) {
        int port = 6789;
        ConnectManager cm = new ConnectManager();
        cm.start(port);
    }
}