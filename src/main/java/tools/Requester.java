package tools;

public class Requester<T>{
    private String command;
    private T args;

    public String getCommand() {
        return command;
    }

    public T getArgs() {
        return args;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setArgs(T args) {
        this.args = args;
    }
}
