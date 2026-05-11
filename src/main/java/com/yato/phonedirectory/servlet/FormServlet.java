package com.yato.phonedirectory.servlet;

import com.yato.phonedirectory.dao.ContactDao;
import com.yato.phonedirectory.entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {

            ContactDao dao = null;
            try {
                dao = new ContactDao();
                int id = Integer.parseInt(idParam);
                Contact contact = dao.findById(id);
                if (contact == null) {
                    response.sendRedirect(request.getContextPath() + "/all");
                    return;
                }
                request.setAttribute("contact", contact);
                request.setAttribute("action", "edit");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/all?error=db");
                return;
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/all");
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
        } else {

            request.setAttribute("action", "add");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        String phone = request.getParameter("phone");
        String birthDate = request.getParameter("birthDate");

        ContactDao dao = null;
        try {
            dao = new ContactDao();
            if (idParam != null && !idParam.isEmpty()) {
                // Обновление
                int id = Integer.parseInt(idParam);
                dao.update(id, firstName, lastName, middleName, phone, birthDate);
                response.sendRedirect(request.getContextPath() + "/all?message=updated");
            } else {

                dao.create(firstName, lastName, middleName, phone, birthDate);
                response.sendRedirect(request.getContextPath() + "/all?message=added");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/all?error=db");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/all?error=invalid_id");
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
}