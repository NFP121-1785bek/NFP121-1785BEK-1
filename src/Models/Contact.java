package Models;

import java.util.*;

public class Contact {
    private String id;
    private String first_name;
    private String last_name;
    private String city;
    private ArrayList<String> groups;
    private ArrayList<PhoneNumber> phone_numbers;

    public Contact(String id, String first_name, String last_name, String city, ArrayList<String> groups, ArrayList<PhoneNumber> phone_numbers) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.groups = groups;
        this.phone_numbers = phone_numbers;
    }

    public String getID() {
        return this.id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getCity() {
        return this.city;
    }

    public ArrayList<String> getGroups() {
        return this.groups;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return this.phone_numbers;
    }
}
