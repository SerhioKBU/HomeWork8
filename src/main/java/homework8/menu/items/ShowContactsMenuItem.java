package homework8.menu.items;

import homework8.menu.ContactView;
import homework8.menu.MenuItem;
import homework8.models.Contact;
import homework8.services.ContactService;

import java.util.List;

public class ShowContactsMenuItem extends MenuItem {
    ContactService contactService;
    ContactView contactView;

    public ShowContactsMenuItem(ContactService contactService, ContactView contactView) {
        this.contactService = contactService;
        this.contactView = contactView;
    }

    @Override
    public String getName() {
        return "Show Contacts List";
    }

    @Override
    public void run() {
        List<Contact> contacts = contactService.showAllContacts();
        contactView.writeContacts(contacts);
    }
}
