package com.yato.phonedirectory.servlet;

import com.yato.phonedirectory.dao.ContactDao;
import com.yato.phonedirectory.entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts")
public class ContactsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deleteId = request.getParameter("delete");
        if (deleteId != null && !deleteId.isEmpty()) {
            // Удаление контакта
            ContactDao dao = null;
            try {
                dao = new ContactDao();
                int id = Integer.parseInt(deleteId);
                dao.delete(id);
                response.sendRedirect(request.getContextPath() + "/contacts");
                return;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/contacts");
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/contacts");
                return;
            } finally {
                if (dao != null) {
                    try {
                        dao.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Показать список контактов
        ContactDao dao = null;
        try {
            dao = new ContactDao();
            List<Contact> contacts = dao.findAll();
            request.setAttribute("contacts", contacts);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                try {
                    dao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        request.getRequestDispatcher("/WEB-INF/jsp/contacts.jsp").forward(request, response);
    }
}