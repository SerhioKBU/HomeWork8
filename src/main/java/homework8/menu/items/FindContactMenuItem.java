package homework8.menu.items;

import homework8.menu.ContactView;
import homework8.menu.MenuItem;
import homework8.models.Contact;
import homework8.services.ContactService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindContactMenuItem extends MenuItem {
    private final ContactService contactService;
    private final ContactView contactView;
    private final Scanner scanner;

    @Override
    public String getName() {
        return "Find contact name";
    }

    @Override
    public void run() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        List<Contact> contacts = contactService.findContactName(name);
        contactView.writeFindNameContacts(contacts);
    }
}
