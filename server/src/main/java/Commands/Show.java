package Commands;

import Collection.LabWork;
import tools.CollectionManager;

public class Show extends Command{
    {setName("show");
        setInfo("выводит все элементы в коллекции");}
    public Show(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(Object arg) {
        String answer = "";
        for (LabWork lab : getCollectionManager().getLabCollection()){
            answer += lab.toString() + "\n";
        }
        return answer;
    }
}
