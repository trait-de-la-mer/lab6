package Commands;

import Collection.LabWork;
import tools.CollectionManager;
import tools.Consoll;

public class CountLessMin extends Command<Double>{
    {
        setName("countLessMin");
        setInfo("вывести количество элементов, значение поля minimalPoint которых меньше заданного");
    }

    public CountLessMin(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(Double arg) {
        String answer = "";
        for (LabWork i : getCollectionManager().getLabCollection()) {
            if (i.getMinimalPoint() < arg) {
                answer += i + "\n";
            }
        }
        return answer;
    }
}
