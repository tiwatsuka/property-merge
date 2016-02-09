package propertymerge;

import java.util.List;

public class UpdateData{

    private List<String> createList;

    private List<String> updateList;

    private List<String> deleteList;

    public void setCreateList(List<String> createList){
        this.createList = createList;
    }

    public void setUpdateList(List<String> updateList){
        this.updateList = updateList;
    }

    public void setDeleteList(List<String> deleteList){
        this.deleteList = deleteList;
    }

    public List<String> getCreateList(){
        return createList;
    }

    public List<String> getUpdateList(){
        return updateList;
    }

    public List<String> getDeleteList(){
        return deleteList;
    }
}
