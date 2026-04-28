package com.yato.phonedirectory.servlet;

import com.yato.phonedirectory.dao.ContactDao;
import com.yato.phonedirectory.entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // ПРИНУДИТЕЛЬНАЯ ЗАГРУЗКА ДРАЙВЕРА
            Class.forName("org.postgresql.Driver");

            ContactDao dao = new ContactDao();
            List<Contact> contacts = dao.findAll();

            out.println("<html><body>");
            out.println("<h1>Phone Directory</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Middle Name</th><th>Phone</th><th>Birth Date</th></tr>");

            for (Contact c : contacts) {
                out.println("<tr>");
                out.println("<td>" + c.getId() + "</td>");
                out.println("<td>" + c.getFirstName() + "</td>");
                out.println("<td>" + c.getLastName() + "</td>");
                out.println("<td>" + c.getMiddleName() + "</td>");
                out.println("<td>" + c.getPhone() + "</td>");
                out.println("<td>" + c.getBirth() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (ClassNotFoundException e) {
            out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQL error: " + e.getMessage());
        }
    }
}