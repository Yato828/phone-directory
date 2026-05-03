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

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect("/phone-directory/contact");
            return;
        }

        try {
            ContactDao dao = new ContactDao();
            Contact c = dao.findById(Integer.parseInt(id));
            dao.close();

            if (c == null) {
                out.println("<h3>Контакт не найден</h3>");
                return;
            }

            out.println("<html><body>");
            out.println("<form method='post' accept-charset='UTF-8'>");
            out.println("<input type='hidden' name='id' value='" + c.getId() + "'>");
            out.println("Имя: <input name='firstName' value='" + c.getFirstName() + "'><br>");
            out.println("Фамилия: <input name='lastName' value='" + c.getLastName() + "'><br>");
            out.println("Отчество: <input name='middleName' value='" + c.getMiddleName() + "'><br>");
            out.println("Телефон: <input name='phone' value='" + c.getPhone() + "'><br>");
            out.println("Дата: <input name='birthDate' value='" + c.getBirth() + "'><br>");
            out.println("<button>Сохранить</button>");
            out.println("</form>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            ContactDao dao = new ContactDao();
            dao.update(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("firstName"),
                    request.getParameter("lastName"),
                    request.getParameter("middleName"),
                    request.getParameter("phone"),
                    request.getParameter("birthDate")
            );
            dao.close();
        } catch (Exception e) {
            System.out.println("ошибка в 3 сервлете " + e.getMessage());
        }
        response.sendRedirect("/phone-directory/contact");
    }
}