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

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getContacts() {
        return this.contacts;
    }
}
