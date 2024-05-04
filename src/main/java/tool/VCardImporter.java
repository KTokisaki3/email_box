package tool;

import User.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * @ClassName : VCardImporter
 * @Description : 导入vCard
 * @Author : WL
 * @Date : 2024-05-04 13:01
 */
public class VCardImporter {
    public static List<Contact> importContacts(String fileName) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Contact contact = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BEGIN:VCARD")) {
                    contact = new Contact();
                } else if (line.startsWith("END:VCARD")) {
                    contacts.add(contact);
                    contact = null;
                } else if (contact != null) {
                    processVCardLine(line, contact);
                }
            }
        } catch (IOException e) {
            System.err.println("Error importing contacts: " + e.getMessage());
        }
        return contacts;
    }

    private static void processVCardLine(String line, Contact contact) {
        String[] parts = line.split(":", 2);
        if (parts.length == 2) {
            String key = parts[0];
            String value = parts[1];

            switch (key) {
                case "FN": // Full name
                    contact.setName(value);
                    break;
                case "TEL": // Telephone number
                    contact.setTelephone(value);
                    break;
                case "EMAIL": // Email address
                    contact.setEmail(value);
                    break;
                case "BDAY": // Birthday
                    contact.setBirthday(value);
                    break;
                case "ORG": // Organization
                    contact.setWorkplace(value);
                    break;
                case "ADR": // Address
                    contact.setHomeAddress(value);
                    break;
                case "GEO": // Geo location
                    // Handle if needed
                    break;
                case "NOTE": // Note
                    contact.setNote(value);
                    break;
                default:
                    // Handle other properties if needed
                    break;
            }
        }
    }
}
