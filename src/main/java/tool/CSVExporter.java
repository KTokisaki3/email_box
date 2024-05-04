package tool;

import User.Contact;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : CSVExporter
 * @Description : 导出CSV
 * @Author : WL
 * @Date : 2024-05-04 12:53
 */
public class CSVExporter {
    public static void exportContacts(List<Contact> contacts) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String fileName = "contacts_" + timestamp + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write CSV header
            writer.write("Name,Telephone,Mobile,Email,Homepage,Birthday,Workplace,HomeAddress,ZipCode,Group,Note\n");

            // Write each contact to CSV
            for (Contact contact : contacts) {
                writer.write(contact.toCSV() + "\n");
            }

            System.out.println("Contacts exported successfully to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error exporting contacts: " + e.getMessage());
        }
    }
}