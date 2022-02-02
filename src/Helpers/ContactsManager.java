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
                if(allContacts.get(i).getID().equals(contacts.get(j))) {
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

            addContactInGroupModel(uniqueID, groups);
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

            for(int i = 0; i < contacts.size(); i++) {
                addGroupInContactModel(contacts.get(i), uniqueID);
            }
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void updateContact(Contact contact) {
        try {
            ArrayList<Contact> contacts = getContactsResponse().getContacts();

            for(int i = 0; i < contacts.size(); i++) {
                if(contacts.get(i).getID().equals(contact.getID())) {
                    contacts.get(i).setFirstName(contact.getFirstName());
                    contacts.get(i).setLastName(contact.getLastName());
                    contacts.get(i).setCity(contact.getCity());
                    contacts.get(i).setPhoneNumbers(contact.getPhoneNumbers());
                    contacts.get(i).setGroups(contact.getGroups());
                    
                    GetContactsResponse getContactsModel = new GetContactsResponse();
                    getContactsModel.setContacts(contacts);
        
                    String jsonInString = gson.toJson(getContactsModel);

                    Writer fileWriter = new FileWriter(Constants.ContactsFile, false);
                    fileWriter.write(jsonInString);
                    fileWriter.close();
                    
                    addContactInGroupModel(contact.getID(), contact.getGroups());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deleteContact(Contact contact) {
        try {
            ArrayList<Contact> contacts = getContactsResponse().getContacts();

            for(int i = 0; i < contacts.size(); i++) {

                if(contacts.get(i).getID().equals(contact.getID())) {
                    contacts.remove(i);

                    GetContactsResponse getContactsModel = new GetContactsResponse();
                    getContactsModel.setContacts(contacts);
        
                    String jsonInString = gson.toJson(getContactsModel);

                    Writer fileWriter = new FileWriter(Constants.ContactsFile, false);
                    fileWriter.write(jsonInString);
                    fileWriter.close();

                    ArrayList<Group> groups = getGroupsResponse().getGroups();

                    for(int j = 0; j < groups.size(); j++) {
                        groups.get(i).removeContact(contact.getID());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void updateGroup(Group group) {
        try {
            ArrayList<Group> groups = getGroupsResponse().getGroups();

            for(int i = 0; i < groups.size(); i++) {
                if(groups.get(i).getID().equals(group.getID())) {
                    groups.get(i).setName(group.getName());
                    groups.get(i).setDescription(group.getDescription());
                    groups.get(i).setContacts(group.getContacts());

                    for(int j = 0; j < group.getContacts().size(); j++) {
                        addGroupInContactModel(group.getID(), group.getContacts().get(j));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void addGroupInContactModel(String contactID, String groupID) {
        try {
            ArrayList<Contact> contacts = getContactsResponse().getContacts();

            for(int i = 0; i < contacts.size(); i++) {
                contacts.get(i).removeGroup(groupID);

                if(contacts.get(i).getID().equals(contactID)) {
                    contacts.get(i).addGroup(groupID);
                }
            }

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

    public void addContactInGroupModel(String contactID, ArrayList<String> groupID) {
        try {
            ArrayList<Group> groups = getGroupsResponse().getGroups();

            for(int i = 0; i < groups.size(); i++) {
                groups.get(i).removeContact(contactID);

                for(int j = 0; j < groupID.size(); j++) {
                    if(groups.get(i).getID().equals(groupID.get(j))) {
                        groups.get(i).addContact(contactID);
                    }
                }
            }

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
