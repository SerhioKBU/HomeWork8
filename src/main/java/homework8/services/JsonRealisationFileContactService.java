package homework8.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import homework8.models.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonRealisationFileContactService implements ContactService {
    final File file;
    List<Contact> contacts;

    public JsonRealisationFileContactService(File file) {
        this.file = file;
        contacts = load();
    }

    private List<Contact> load() {
        if(!file.exists()){
            save(List.of());
        }
        List<Contact> contacts = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(file))) {
            for (Contact person: contacts) {
                objectMapper.readValue(bufferedReader, Contact.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private void save(List<Contact> contacts){
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, contacts);
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
        try {
            FileWriter writer = new FileWriter("ContactsBook.txt");
            for (Contact element: contacts) {
                writer.write(element + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
