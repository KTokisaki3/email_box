package User;

import tool.CSVExporter;
import tool.VCardExporter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : User
 * @Description :实现功能
 * @Author : WL
 * @Date : 2024-04-27 15:46
 */
public class User {
    private List<Contact> allContacts;     //所有联系人
    private List<Group> groups;            //所有联系组
    private List<Contact> otherContacts;   //没有分组的联系人

    public User(){
        this.allContacts = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.otherContacts = new ArrayList<>();
    }

    public User(List<Contact> allContacts) {
        this.allContacts = allContacts;
        setGroups();
        setOtherContacts();
    }
    //添加联系人,界面弹窗填写联系人资料(默认没有分组)，然后创造一个联系人对象
    public boolean addContact(Contact contact){
        if(allContacts.contains(contact)){
            System.out.println("联系人已存在！");
            return false;
        }
        allContacts.add(contact);
        if(contact.getGroup().isEmpty()) {
            otherContacts.add(contact);
        }
        return true;
    }

    //添加联系组,联系组里的联系人在otherContacts中选取
    public boolean addGroup(Group group){
        if(groups.contains(group)){
            System.out.println("联系组已存在！");
            return false;
        }
        groups.add(group);
        for(Contact contact : group.getContacts()){
            otherContacts.remove(contact);      //从未分组里移除
        }
        return true;
    }

    //删除联系人,先选中联系人
    public boolean removeContact(Contact contact){
        if(allContacts.contains(contact)){
            allContacts.remove(contact);
            if(contact.getGroup().isEmpty()){
                otherContacts.remove(contact);
                return true;
            }
            for(Group group : groups){
                if(group.containContact(contact)){
                    group.removeContact(contact);
                    return true;
                }
            }
        }
        System.out.println("需要删除的联系人不存在！");
        return false;
    }

    //删除联系组,先选中联系组
    public boolean removeGroup(Group group){
        if(groups.contains(group)){
            for(Contact contact : group.getContacts()){
                contact.setGroup(null);
                otherContacts.add(contact);
            }
            groups.remove(group);
            return true;
        }else {
            System.out.println("需要删除的联系组不存在！");
            return false;
        }
    }

    //修改联系人
    public  void changeContact(Contact contact,String name, String telephone, String mobile, String instantMessaging, String email, String homepage,
                               String birthday, String photo, String workplace, String homeAddress, String zipCode,  String note){
        contact.setName(name);
        contact.setTelephone(telephone);
        contact.setMobile(mobile);
        contact.setInstantMessaging(instantMessaging);
        contact.setEmail(email);
        contact.setHomepage(homepage);
        contact.setBirthday(birthday);
        contact.setPhoto(photo);
        contact.setWorkplace(workplace);
        contact.setHomeAddress(homeAddress);
        contact.setZipCode(zipCode);
        contact.setNote(note);
    }

    //将联系人加入分组
    public boolean addToGroup(Contact contact, Group group){
        if(!groups.contains(group)){
            System.out.println("联系组不存在！");
            return false;
        }
        if(group.containContact(contact)){
            System.out.println("联系人已经在联系组！");
            contact.setGroup(group.getName());
            return true;
        }else {
            if(otherContacts.contains(contact)){
                otherContacts.remove(contact);
                group.addContact(contact);
                return true;
            }
            for(Group g : groups){
                if(g.containContact(contact)){
                    g.removeContact(contact);
                    group.addContact(contact);
                    return true;
                }
            }
            System.out.println("联系人不存在！");
            return false;
        }
    }
    public boolean addToGroup(List<Contact> contacts, Group group){
        for(Contact contact : contacts){
            if(!addToGroup(contact,group)){
                return false;
            }
        }
        return true;
    }

    //将联系人从分组删除
    public boolean removeFromGroup(Contact contact){
        if(contact.getGroup().isEmpty()){
            System.out.println("联系人不在联系组中！");
        }else {
            for(Group group : groups){
                if(group.containContact(contact)){
                    group.removeContact(contact);
                    otherContacts.add(contact);
                }
            }
            for (Group group : groups){
                if(group.containContact(contact)){
                    return false;
                }
            }
            System.out.println("联系组不存在！");
            contact.setGroup(null);
        }
        return true;
    }
    public boolean removeFromGroup(List<Contact> contacts){
        for(Contact contact : contacts){
            if(!removeFromGroup(contact)){
                return false;
            }
        }
        return true;
    }

    //根据组名返回组
    public Group findGroup(String groupName){
        for(Group group : groups){
            if(groupName.equals(group.getName())){
                return group;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return allContacts;
    }

    public void setAllContacts(ArrayList<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups() {
        this.groups = new ArrayList<>();
        for(Contact contact : allContacts){
            if(!contact.getGroup().isEmpty()){
                Group group = new Group(contact.getGroup());
                group.addContact(contact);
                if(!groups.contains(group)){
                    groups.add(group);
                }
            }
        }
    }

    public List<Contact> getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts() {
        this.otherContacts = new ArrayList<>();
        for(Contact contact : allContacts){
            if(contact.getGroup().isEmpty()){
                this.otherContacts.add(contact);
            }
        }
    }
}
