package com.yato.phonedirectory;

import com.yato.phonedirectory.dao.ContactDao;
import com.yato.phonedirectory.entity.Contact;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            ContactDao dao = new ContactDao();

            List<Contact> contacts = dao.findAll();

            for (Contact contact : contacts) {
                System.out.println(contact);
            }

            dao.create("Автобус", "Никитич", "Владимирович", "+375-29-887-99-76", "1991-11-03");

            // dao.delete(15);

            //dao.update(19, "Анатолий", "Терентий", "Иванович", "+375-33-999-88-77", "1990-05-15");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
    }
}