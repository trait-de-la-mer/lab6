package Commands;

import Collection.LabWork;
import Collection.Person;
import tools.CollectionManager;

public class LessThanAuthor extends Command<Person>{
    {setName("lessThanAuthor");
        setInfo("вывести элементы, значение поля author которых меньше заданного");}
    public LessThanAuthor(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(Person person) {
        String answer = "";
        for (LabWork lab : getCollectionManager().getLabCollection()){
            if (lab.getAuthor().compareTo(person) < 0){
                answer += lab.toString() + "\n";
            }
        }
        return answer;
    }
}
