package Commands;

import Collection.LabWork;
import Commands.Command;
import tools.CollectionManager;

import java.util.Iterator;

public class Remove extends Command<Integer> {
    {setName("remove");
        setInfo("удаляет элемент по id");}
    public Remove(CollectionManager cm) {
        super(cm);
    }

    public String execute(Integer args) {
        int key;
        key = args;
        CollectionManager cm = getCollectionManager();
        Iterator<LabWork> iterator = cm.getLabCollection().iterator();
        int counter = 0;
        while (iterator.hasNext()){
            LabWork labWork = iterator.next();
            if (labWork.getId() == key){
                cm.removeElement(counter);
                return ("Эл-т удален" + key + " удален");
            }
            counter++;
        }  throw new IllegalArgumentException("такого id нет");
    }
}