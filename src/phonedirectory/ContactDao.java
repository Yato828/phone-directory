package phonedirectory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

     private static final String url = "jdbc:postgresql://localhost:5432/contactsdb";
     private static final String user = "yato";
    private static final String password = "";
    private final Connection conn;

    public ContactDao() throws SQLException {
         this.conn = DriverManager.getConnection(url, user, password);
    }
        public List<Contact> getAll () {
            List<Contact> contacts = new ArrayList<>();
            String sql = "SELECT * FROM contacts ORDER BY last_name, first_name, middle_name";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Contact contact = new Contact(

                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("middle_name"),
                            rs.getString("phone"),
                            rs.getString("birth_date")
                    );
                    contacts.add(contact);
                }

            } catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            return contacts;
        }

        public Contact getById (Integer id){
            String sql = "SELECT * FROM contacts WHERE id = ?";
            Contact contact = null;

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    contact = new Contact(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("middle_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("birth_date")
                    );
                }

            } catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            return contact;
        }

        public void create (String firstName, String lastName, String middleName, String phone, String birthDate){
            String sql = "INSERT INTO contacts (first_name, last_name, middle_name, phone, birth_date) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, middleName);
                pstmt.setString(4, phone);
                pstmt.setString(5, birthDate);

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Контакт добавлен!");
                }

            } catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        public void delete (Integer id){
            String sql = "DELETE FROM contacts WHERE id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        public void update ( int id, String firstName, String lastName, String middleName, String phone, String
        birthDate){
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
            catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } public void close() throws SQLException{
        conn.close();    }
    }
