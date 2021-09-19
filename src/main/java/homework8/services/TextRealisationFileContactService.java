package homework8.services;

import homework8.models.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextRealisationFileContactService implements ContactService {

    private File file;
    List<Contact> contacts;

    public TextRealisationFileContactService(File file) {
        this.file = file;
        contacts = load();
    }

    private List<Contact> load() {
        if(!file.exists()){
            save(List.of());
        }
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(file))) {
            contacts = bufferedReader.lines()
                    .map(l -> l.split("|"))
                    .map(c -> new Contact(c[0], c[1]))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

        private void save(List<Contact> contacts){
            FileWriter writer;
            try {
                writer = new FileWriter(file);
                for (Contact element: contacts) {
                    writer.write(element + System.getProperty("line.separator"));
                }
                writer.close();
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
