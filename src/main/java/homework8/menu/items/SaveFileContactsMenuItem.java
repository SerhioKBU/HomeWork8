package homework8.menu.items;

import homework8.menu.MenuItem;
import homework8.models.Contact;
import homework8.services.ContactService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SaveFileContactsMenuItem extends MenuItem {
    private final ContactService contactService;

    @Override
    public String getName() {
        return "Save contacts to TXT file";
    }

    @Override
    public void run() {
        List<Contact> contacts = contactService.showAllContacts();
        contactService.saveContact(contacts);
    }
}
