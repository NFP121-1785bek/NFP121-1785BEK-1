package Models;

import java.util.*;

public class GetContactsResponse {
    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
