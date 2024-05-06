package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : Group
 * @Description :联系组
 * @Author : WL
 * @Date : 2024-04-27 15:46
 */
public class Group {
    private String name;
    private List<Contact> contacts;

    public Group(String name) {
        this.name = name;
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setGroup(getName());
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setGroup("");
    }

    public boolean containContact(Contact contact){
        return contacts.contains(contact);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return this.getName().equals(group.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
