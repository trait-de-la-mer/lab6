package Commands;

import Collection.LabWork;
import Collection.Person;
import tools.CollectionManager;

import java.util.ArrayList;

public class PrintUniqAthors extends Command{
    {setName("uniqAuthor");
        setInfo("Выводи уникальных авторов");}

    public PrintUniqAthors(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(Object arg) {
        String answer = "";
        ArrayList<Person> persons = new ArrayList<>();
        for (LabWork i : getCollectionManager().getLabCollection()){
            boolean isAuthorExist = false;
            Person author = i.getAuthor();
            for (Person j : persons){
                if (j.equals(author)) {
                    isAuthorExist = true;
                    break;
                }
            }
            if (!isAuthorExist){
                persons.add(author);
            }
        }
        for (Person i : persons){
            answer += i.toString() + "\n";
        }
        return "";
    }
}
