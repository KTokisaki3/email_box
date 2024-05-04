package tool;

import User.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : CSVImporter
 * @Description : 导入CSV
 * @Author : WL
 * @Date : 2024-05-04 12:55
 */
public class CSVImporter {
    public static List<Contact> importContacts(String fileName) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Contact contact = createContactFromCSVLine(line);
                if (contact != null) {
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            System.err.println("Error importing contacts: " + e.getMessage());
        }
        return contacts;
    }

    private static Contact createContactFromCSVLine(String line) {
        String[] fields = line.split(",");
        if (fields.length >= 13) {
            Contact contact = new Contact();
            contact.setName(fields[0]);
            contact.setTelephone(fields[1]);
            contact.setMobile(fields[2]);
            contact.setInstantMessaging(fields[3]);
            contact.setEmail(fields[4]);
            contact.setHomepage(fields[5]);
            contact.setBirthday(fields[6]);
            contact.setPhoto(fields[7]);
            contact.setWorkplace(fields[8]);
            contact.setHomeAddress(fields[9]);
            contact.setZipCode(fields[10]);
            contact.setGroup(fields[11]);
            contact.setNote(fields[12]);
            return contact;
        }
        return null;
    }
}