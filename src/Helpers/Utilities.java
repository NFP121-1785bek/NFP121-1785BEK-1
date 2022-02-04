package Helpers;

public class Utilities {
    public static String getLocalizedText(String localizedString, AppLanguage language) {
        switch (language) {
            case ENGLISH: 
            if(localizedString == "sort_first_name") {
                return "Sort by first name";
            } else if (localizedString == "sort_last_name") {
                return "Sort by last name";
            } else if (localizedString == "sort_city") {
                return "Sort by city";
            } else if (localizedString == "add_contact") {
                return "Add new contact";
            } else if (localizedString == "view") {
                return "View";
            } else if (localizedString == "update") {
                return "Update";
            } else if (localizedString == "delete") {
                return "Delete";
            } else if (localizedString == "add_group") {
                return "Add new Group";
            } else if (localizedString == "contacts") {
                return "Contacts";
            } else if (localizedString == "show_groups") {
                return "Show groups";
            } else if (localizedString == "search") {
                return "Search";
            } else if (localizedString == "clear") {
                return "Clear";
            } else if (localizedString == "first_name") {
                return "First name";
            } else if (localizedString == "last_name") {
                return "Last name";
            } else if (localizedString == "city") {
                return "City";
            } else if (localizedString == "groups") {
                return "Groups";
            } else if (localizedString == "list_of_groups") {
                return "List of groups";
            } else if (localizedString == "group_name") {
                return "Group Name";
            } else if (localizedString == "number_contacts") {
                return "Nb of contacts";
            } else if (localizedString == "contact_name") {
                return "Contact Name";
            } else if (localizedString == "contact_city") {
                return "Contact City";
            } else if (localizedString == "update_group") {
                return "Update Group";
            } else if (localizedString == "back") {
                return "Back";
            } else if (localizedString == "add_contact_to_groups") {
                return "Add contact to Groups";
            } else if (localizedString == "new_contact") {
                return "New Contact";
            } else if (localizedString == "save") {
                return "Save";
            } else if (localizedString == "group_name") {
                return "Group name";
            } else if (localizedString == "description") {
                return "Description";
            } else if (localizedString == "add_to_group") {
                return "Add to group";
            } else if (localizedString == "save_group") {
                return "Save Group";
            } else if (localizedString == "cancel") {
                return "Cancel";
            } else if (localizedString == "manage_contacts") {
                return "Manage Contacts";
            } else if (localizedString == "phone_numbers") {
                return "Phone Numbers";
            } else if (localizedString == "update_contact") {
                return "Update Contact";
            } else if (localizedString == "switch_language") {
                return "Switch Language";
            }
            case FRENCH:
            if(localizedString == "sort_first_name") {
                return "Trier par prénom";
            } else if (localizedString == "sort_last_name") {
                return "Trier par nom";
            } else if (localizedString == "sort_city") {
                return "Trier par ville";
            } else if (localizedString == "add_contact") {
                return "Ajouter un contact";
            } else if (localizedString == "view") {
                return "Voir";
            } else if (localizedString == "update") {
                return "Mettre à jour";
            } else if (localizedString == "delete") {
                return "Effacer";
            } else if (localizedString == "add_group") {
                return "Ajouter un groupe";
            } else if (localizedString == "contacts") {
                return "Contacts";
            } else if (localizedString == "show_groups") {
                return "Voir les groupes";
            } else if (localizedString == "search") {
                return "Chercher";
            } else if (localizedString == "clear") {
                return "Annuler";
            } else if (localizedString == "first_name") {
                return "Prénom";
            } else if (localizedString == "last_name") {
                return "Nom";
            } else if (localizedString == "city") {
                return "Ville";
            } else if (localizedString == "groups") {
                return "Groupes";
            } else if (localizedString == "list_of_groups") {
                return "Liste de Groupes";
            } else if (localizedString == "group_name") {
                return "Nom du groupe";
            } else if (localizedString == "number_contacts") {
                return "Nombre de contacts";
            } else if (localizedString == "contact_name") {
                return "Nom du contact";
            } else if (localizedString == "contact_city") {
                return "Ville";
            } else if (localizedString == "update_group") {
                return "Groupe mis à jour";
            } else if (localizedString == "back") {
                return "Arrière";
            } else if (localizedString == "add_contact_to_groups") {
                return "Ajouter contact aux groupes";
            } else if (localizedString == "new_contact") {
                return "Nouveau contact";
            } else if (localizedString == "save") {
                return "Enregistrer";
            } else if (localizedString == "group_name") {
                return "Nom du groupe";
            } else if (localizedString == "description") {
                return "Description";
            } else if (localizedString == "add_to_group") {
                return "Ajouter au groupe";
            } else if (localizedString == "save_group") {
                return "Enregister groupe";
            } else if (localizedString == "cancel") {
                return "Annuler";
            } else if (localizedString == "manage_contacts") {
                return "Gestion des contacts";
            } else if (localizedString == "phone_numbers") {
                return "Numéros de téléphone";
            } else if (localizedString == "update_contact") {
                return "Mettre à jour";
            } else if (localizedString == "switch_language") {
                return "Changer de langues";
            }
        }
        return "";
    }
}
