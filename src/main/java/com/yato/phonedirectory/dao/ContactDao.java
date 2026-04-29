package com.yato.phonedirectory.dao;

import com.yato.phonedirectory.entity.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private static final String url = "jdbc:postgresql://localhost:5432/contactsdb";
    private static final String user = "yato";
    private static final String password = "";
    private final Connection conn;

    public ContactDao() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public List<Contact> findAll() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY last_name, first_name, middle_name";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("middle_name"),
                        rs.getString("phone"),
                        rs.getString("birth_date")
                ));
            }
        }

        return contacts;
    }

    public Contact findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        Contact contact = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("middle_name"),
                        rs.getString("phone"),
                        rs.getString("birth_date")
                );
            }
        }

        return contact;
    }

    public void create(String firstName, String lastName, String middleName, String phone, String birthDate) throws SQLException {
        String sql = "INSERT INTO contacts (first_name, last_name, middle_name, phone, birth_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, middleName);
            pstmt.setString(4, phone);
            pstmt.setString(5, birthDate);
            pstmt.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void update(int id, String firstName, String lastName, String middleName, String phone, String birthDate) throws SQLException {
        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, middle_name = ?, phone = ?, birth_date = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, middleName);
            pstmt.setString(4, phone);
            pstmt.setString(5, birthDate);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}