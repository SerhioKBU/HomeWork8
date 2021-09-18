package homework8.services;

import homework8.models.Contact;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BytesRealisationFileContactService implements ContactService {

    private final File file;
    List<Contact> contacts;

    public BytesRealisationFileContactService(File file) {
        this.file = file;
        contacts = load();
    }

    @SneakyThrows
    private List<Contact> load() {
        if(!file.exists()){
            save(List.of());
        }
        List<Contact> contacts = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            for (int i = 0; i < contacts.size(); i++) {
                Contact c = (Contact) ois.readObject();
                contacts.add(c);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private void save(List<Contact> contacts) {
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < contacts.size(); i++) {
                object.writeObject(contacts.get(i));
            }
            object.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        save(contacts);
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
        save(contacts);
    }

    @Override
    public void saveContact(List<Contact> contacts) {
        FileWriter writer;
        try {
            writer = new FileWriter("ContactsBook.txt");
            for (Contact element: contacts) {
                writer.write(element + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
