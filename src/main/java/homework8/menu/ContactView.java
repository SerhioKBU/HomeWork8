package homework8.menu;

import homework8.models.Contact;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
public class ContactView {
    private final Scanner scanner;
    List<Contact> contacts;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void writeFindNameContacts(List<Contact> contacts) {
        System.out.println("----------- FOUND NAME CONTACTS -----------");
        for (Contact contact: contacts) {
            System.out.println("Contact - " + contact.getName() +
                                " - tel.# " + contact.getPhoneNumber());
        }
        System.out.println("-------------------------------------------");
    }

    public void writeContacts(List<Contact> contacts) {
        System.out.println("----------- CONTACTS -----------");
        int number = 0;
        for (Contact item : contacts) {
            System.out.println(++ number + "." + " " + item);
       }
        System.out.println("-----------------------------");
    }

    public int deleteContact() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        do {
            System.out.print("Enter the contact which you wanna remove: ");
            System.out.print(" ");
            if (!scanner.hasNextInt()) {
                System.out.println(ANSI_RED + "Entered value isn't a number. Try again!" + ANSI_RESET);
                scanner.nextLine();
            } else {
                break;
            }
        } while (true);
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice - 1;
    }
}
