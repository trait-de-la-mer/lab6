package Commands;

import Collection.LabWork;
import tools.CollectionManager;

public class Add extends Command<LabWork>{
    {setName("add");}
    public Add(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String execute(LabWork arg) {
        CollectionManager cm = getCollectionManager();
        arg.setId(cm.generateId());
        cm.addElement(arg);
        return "Успешно добавлено";
    }
}
