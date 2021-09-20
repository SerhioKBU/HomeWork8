package homework8;

import homework8.menu.ContactView;
import homework8.menu.Menu;
import homework8.menu.MenuItem;
import homework8.menu.items.*;
import homework8.services.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        ContactService contactService = new InMemoryContactsService();
        ContactService contactService = new TextRealisationFileContactService
             (new File("ContactsBook.csv"));
//        ContactService contactService = new BytesRealisationFileContactService
//               (new File("ContactsBook.obj"));
//        ContactService contactService = new XMLRealisationFileContactService
//                (new File("ContactsBook.xml"));
//        ContactService contactService = new JsonRealisationFileContactService
//                (new File("ContactsBook.json"));
        ContactView contactView = new ContactView(scanner);
        List<MenuItem> menuItemList = Arrays.asList(
                new AddContactMenuItem(scanner, contactService),
                new ShowContactsMenuItem(contactService, contactView),
                new DeleteContactMenuItem(contactService, contactView),
                new FindContactMenuItem(contactService, contactView, scanner),
                new SaveFileContactsMenuItem(contactService)
        );
        Menu menu = new Menu(scanner, menuItemList);
        menu.makeMenu();
    }
}
