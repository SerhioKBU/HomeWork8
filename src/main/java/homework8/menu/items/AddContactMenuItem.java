package homework8.menu.items;


import homework8.menu.MenuItem;
import homework8.models.Contact;
import homework8.services.ContactService;

import java.util.Scanner;

public class AddContactMenuItem extends MenuItem {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    Scanner scanner;
    ContactService contactService;

    public AddContactMenuItem(Scanner scanner, ContactService contactService) {
        this.scanner = scanner;
        this.contactService = contactService;
    }

    @Override
    public String getName() {
        return "Add New Contact";
    }

    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Enter contact name: " + ANSI_RESET);
        String name = scanner.nextLine();
        System.out.println(ANSI_CYAN + "Enter contact number: " + ANSI_RESET);
        String number = scanner.nextLine();
        Contact newContact = new Contact(name,number);
        contactService.addContact(newContact);
    }
}
