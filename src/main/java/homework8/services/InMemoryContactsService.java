package homework8.services;

import homework8.models.Contact;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryContactsService implements ContactService {
    List<Contact> contacts = new ArrayList<>();


    @Override
    public List<Contact> showAllContacts() {
        return contacts;
    }

    @Override
    public List<Contact> findContactName(String name) {
        List<Contact> findNameResult = new ArrayList<>();
        for (Contact contact: contacts) {
            if(contact.getName().startsWith(name)) {
                findNameResult.add(contact);
            }
        }
        return findNameResult;
    }

    @Override
    public void removeContact(int index) {
        contacts.remove(index);
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void saveContact(List<Contact> contacts) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("ContactBook.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Contact element: contacts) {
            try {
                writer.write(element + System.getProperty("line.separator"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

