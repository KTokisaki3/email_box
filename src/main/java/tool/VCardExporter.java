package tool;

import User.Contact;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : VCardExporter
 * @Description : 导出vCard
 * @Author : WL
 * @Date : 2024-05-04 12:58
 */
public class VCardExporter {
    public static void exportContacts(List<Contact> contacts) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String fileName = "contacts_" + timestamp + ".vcf";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.write(contact.toVCard());
            }
            System.out.println("Contacts exported successfully to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error exporting contacts: " + e.getMessage());
        }
    }
}
