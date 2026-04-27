package phonedirectory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            ContactDao dao = new ContactDao();

            List<Contact> contacts = dao.getAll();

            for (Contact contact : contacts) {
                System.out.println(contact);
            }

            dao.create("Автобус", "Никитич", "Владимирович", "+375-29-887-99-76", "1991-11-03");

            // dao.delete(15);

            dao.update(19, "Анатолий", "Терентий", "Иванович", "+375-33-999-88-77", "1990-05-15");

        } catch (SQLException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
    }
}