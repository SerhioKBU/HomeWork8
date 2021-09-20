package homework8.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import homework8.models.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XMLRealisationFileContactService implements ContactService{

    final File file;
    List<Contact> contacts;

    public XMLRealisationFileContactService(File file) {
        this.file = file;
        contacts = load();
    }

    private List<Contact> load() {

        if(!file.exists()){
            save(List.of());
        }
        List<Contact> contacts = new ArrayList<>();
        XmlMapper xmlMapper = new XmlMapper();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(file))) {
            for (Contact person: contacts) {
                xmlMapper.readValue(bufferedReader, Contact.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }


    private void save(List<Contact> contacts){
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, contacts);
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
