package com.yato.phonedirectory.servlet;

import com.yato.phonedirectory.dao.ContactDao;
import com.yato.phonedirectory.entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContactsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deleteId = request.getParameter("delete");
        if (deleteId != null) {
            ContactDao dao = null;
            try {
                dao = new ContactDao();
                int id = Integer.parseInt(deleteId);
                dao.delete(id);
                response.sendRedirect("/phone-directory/contacts?message=deleted");
                return;
            } catch (Exception e) {
                response.sendRedirect("/phone-directory/contacts?error=delete");
                return;
            } finally {
                if (dao != null) {
                    try { dao.close(); } catch (Exception ignored) {}
                }
            }
        }

        // Показать список
        ContactDao dao = null;
        try {
            dao = new ContactDao();
            List<Contact> contacts = dao.findAll();
            request.setAttribute("contacts", contacts);
        } catch (Exception e) {
            request.setAttribute("error", "DB error: " + e.getMessage());
        } finally {
            if (dao != null) {
                try { dao.close(); } catch (Exception ignored) {}
            }
        }

        request.getRequestDispatcher("/WEB-INF/jsp/contacts.jsp").forward(request, response);
    }
}