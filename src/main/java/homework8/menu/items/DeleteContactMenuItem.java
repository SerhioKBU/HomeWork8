package homework8.menu.items;

import homework8.menu.ContactView;
import homework8.menu.MenuItem;
import homework8.services.ContactService;

public class DeleteContactMenuItem extends MenuItem {
    ContactService contactService;
    ContactView contactView;

    public DeleteContactMenuItem(ContactService contactService, ContactView contactView) {
        this.contactService = contactService;
        this.contactView = contactView;
    }

    @Override
    public String getName() {
        return "Remove the Contact";
    }

    @Override
    public void run() {
        contactService.removeContact(contactView.deleteContact());
        System.out.println("------------------");
    }
}



