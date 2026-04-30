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

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><meta charset='UTF-8'><title>Добавить контакт</title></head><body>");
        out.println("<h1> Добавить новый контакт</h1>");
        out.println("<form method='post' action='/phone-directory/add'>");
        out.println("Имя: <input type='text' name='firstName' required><br><br>");
        out.println("Фамилия: <input type='text' name='lastName' required><br><br>");
        out.println("Отчество: <input type='text' name='middleName'><br><br>");
        out.println("Телефон: <input type='text' name='phone' required><br><br>");
        out.println("Дата рождения: <input type='date' name='birthDate'><br><br>");
        out.println("<button type='submit'>Сохранить</button>");
        out.println("<a href='/phone-directory/contact'>Назад</a>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        String phone = request.getParameter("phone");
        String birthDate = request.getParameter("birthDate");
        try {
            ContactDao dao = new ContactDao();
            dao.create(firstName, lastName, middleName, phone, birthDate);
            dao.close();

            response.sendRedirect("/phone-directory/contact"); // чтобы вернуться на первую страницу

        }catch (SQLException | ClassNotFoundException e){
            System.out.println("ошибка во втором сервлете " + e.getMessage());
        }
    }
}
