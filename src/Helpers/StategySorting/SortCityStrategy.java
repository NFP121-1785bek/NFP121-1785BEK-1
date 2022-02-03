package Helpers.StategySorting;

import java.util.*;
import Models.*;

public class SortCityStrategy implements SortStrategy {
    @Override
    public ArrayList<Contact> sort(ArrayList<Contact> contacts) {
        ArrayList<Contact> sortContacts = contacts;
        Collections.sort(sortContacts, Contact.Comparators.city);
        return sortContacts;
    }
}
