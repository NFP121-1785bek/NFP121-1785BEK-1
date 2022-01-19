package Helpers;

import java.util.*;
import com.google.gson.*;

import java.io.*;
import Models.*;

public class ContactsManager {

    private static ContactsManager sharedInstance;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static ContactsManager sharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ContactsManager();
        }

        return sharedInstance;
    }

    public GetContactsResponse getContactsResponse() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        return this.gson.fromJson(new FileReader(Constants.ContactsFile), GetContactsResponse.class);
    }

    public GetGroupsResponse getGroupsResponse() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        return this.gson.fromJson(new FileReader(Constants.GroupsFile), GetGroupsResponse.class);
    }

    public ArrayList<Contact> getContactsByGroupID(Group group) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        ArrayList<String> contacts = group.getContacts();
        ArrayList<Contact> allContacts = getContactsResponse().getContacts();

        ArrayList<Contact> fetchedContacts = new ArrayList<Contact>();

        for (int i = 0; i < allContacts.size(); i++) {
            for (int j = 0; j < contacts.size(); j++) {
                if(allContacts.get(i).getID() == contacts.get(j)) {
                    fetchedContacts.add(allContacts.get(i));
                }
             }
        }
        return fetchedContacts;
    }

    public ArrayList<Group> getGroupsByContactID(Contact contact) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        ArrayList<String> groups = contact.getGroups();
        ArrayList<Group> allGroups = getGroupsResponse().getGroups();

        ArrayList<Group> fetchedGroups = new ArrayList<Group>();

        for (int i = 0; i < allGroups.size(); i++) {
            for (int j = 0; j < groups.size(); j++) {
                if(allGroups.get(i).getID() == groups.get(j)) {
                    fetchedGroups.add(allGroups.get(i));
                }
             }
        }
        return fetchedGroups;
    }

    public void insertContact(String firstName, String lastName, String city, ArrayList<PhoneNumber> phoneNumbers, ArrayList<String> groups) {
        String uniqueID = UUID.randomUUID().toString();

        Contact contact = new Contact(uniqueID, firstName, lastName, city, groups, phoneNumbers);

        try {
            ArrayList<Contact> contacts = getContactsResponse().getContacts();
            contacts.add(contact);

            GetContactsResponse getContactsModel = new GetContactsResponse();
            getContactsModel.setContacts(contacts);

            String jsonInString = gson.toJson(getContactsModel);

            Writer fileWriter = new FileWriter(Constants.ContactsFile, false);
            fileWriter.write(jsonInString);
            fileWriter.close();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public void insertGroup(String name, String description, ArrayList<String> contacts) {
        String uniqueID = UUID.randomUUID().toString();

        Group group = new Group(uniqueID, name, description, contacts);

        try {
            ArrayList<Group> groups = getGroupsResponse().getGroups();
            groups.add(group);

            GetGroupsResponse getGroupsModel = new GetGroupsResponse();
            getGroupsModel.setGroups(groups);

            String jsonInString = gson.toJson(getGroupsModel);

            Writer fileWriter = new FileWriter(Constants.GroupsFile, false);
            fileWriter.write(jsonInString);
            fileWriter.close();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
