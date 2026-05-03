package Commands;

import tools.CollectionManager;
import tools.CommandManager;

public class History extends Command{
    {
        setName("history");
        setInfo("выводит последние 5 команд (без их аргументов)");
    }

    public History(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(Object arg) {
        String answer = "";
        for (String i : CommandManager.getHistory()) {
            answer += i;
        }
        return answer;
    }
}
