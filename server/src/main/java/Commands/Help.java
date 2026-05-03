package Commands;

import tools.CollectionManager;
import tools.CommandManager;

public class Help extends Command{
    {setName("help");
        setInfo("Выводит все команды и их выполнение");}

    public Help(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(Object args) {
        String answer = "";
        for (String nameCommand : CommandManager.getCommands().keySet()){
            answer += nameCommand + " - " + CommandManager.getCommands().get(nameCommand).getInfo() + "\n";
        }
        return answer;
    }
}
