package Helpers.StrategySorting;

import java.util.ArrayList;
import Models.*;

public class SortContext {
    private final SortStrategy strategy; 

    public SortContext(SortStrategy strategy) { 
        this.strategy = strategy; 
    } 

    public ArrayList<Contact> arrange(ArrayList<Contact> input) { 
        ArrayList<Contact> sortContacts = input;
        strategy.sort(sortContacts); 

        return sortContacts;
    } 
}
