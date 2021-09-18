package homework8.services;


import homework8.models.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> showAllContacts();
    List<Contact> findContactName(String name);
    void removeContact(int index);
    void addContact(Contact contact);
    void saveContact(List<Contact> contacts);
}
