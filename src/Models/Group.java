package Models;

import java.util.*;

public class Group {
    private String id;
    private String name;
    private String description;
    private ArrayList<String> contacts;

    public Group(String id, String name, String description, ArrayList<String> contacts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contacts = contacts;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getContacts() {
        return this.contacts;
    }

    public void setContacts(ArrayList<String> contacts) {
        this.contacts = contacts;
    }

    public void addContact(String contactID) {
        contacts.add(contactID);
    }

    public void removeContact(String contactID) {
        contacts.remove(contactID);
    }
}
