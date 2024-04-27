package User;

import java.util.ArrayList;

/**
 * @ClassName : User
 * @Description :实现功能
 * @Author : WL
 * @Date : 2024-04-27 15:46
 */
public class User {
    private ArrayList<Contact> allContacts;
    private ArrayList<Group> groups;
    private ArrayList<Contact> otherContacts;

    public User(){
        this.allContacts = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.otherContacts = new ArrayList<>();
    }

    public User(ArrayList<Contact> allContacts,ArrayList<Group> groups) {
        this.allContacts = allContacts;
        this.groups = groups;
        this.otherContacts = new ArrayList<>();
        for(Contact c : allContacts){
            if(c.getGroup() == null){
                this.otherContacts.add(c);
            }
        }
    }
    //添加联系人
    public void addContact(){}

    //添加联系组
    public void addGroup(){}

    //删除联系人
    public void removeContact(){}

    //删除联系组
    public void removeGroup(){}

    //修改联系人
    public  void changeContact(){}

    //将联系人加入分组
    public void addToGroup(){}

    //将联系人从分组删除
    public void removeFromGroup(){}

    //查询联系人
    public void listContacts(){}

    //搜索联系人
    public void searchContact(){}

    //导入
    public void importContacts(){}

    //导出
    public void exportContacts(){}

    public ArrayList<Contact> getAllContacts() {
        return allContacts;
    }

    public void setAllContacts(ArrayList<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Contact> getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts(ArrayList<Contact> otherContacts) {
        this.otherContacts = otherContacts;
    }
}
