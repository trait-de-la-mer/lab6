package Commands;

import Collection.LabWork;
import tools.CollectionManager;
import tools.UpdateArgs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Update extends Command<UpdateArgs>{
    {
        setName("update");
        setInfo("обновляет лабу по айди");
    }

    public Update(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(UpdateArgs updateArgs) {
        Long needId = updateArgs.getId();
        LabWork labWork = updateArgs.getLabWork();
        for (LabWork lab : getCollectionManager().getLabCollection()){
            Long currentId = lab.getId();
            if (Objects.equals(needId, currentId)){
                CollectionManager cm = getCollectionManager();
                labWork.setId(currentId);
                Iterator<LabWork> iterator = cm.getLabCollection().iterator();
                int counter = 0;
                while (iterator.hasNext()){
                    LabWork someLabWork = iterator.next();
                    if (Objects.equals(someLabWork.getId(), needId)){
                        cm.changeLab(labWork, counter);
                        cm.setLastId(cm.getLastId() - 1);
                    }
                    counter++;
                }
                return "Обновлена лаба по id " + needId;
            }
        }
        return "Такого id нет";
    }
}
