package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.dao.JediDAO;
import org.example.dao.JediDAOImpl;

@WebServlet("/DeleteJediController")
public class DeleteJediController extends HttpServlet {

    JediDAO jediDAO = new JediDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("Id"));
            jediDAO.delete(id);
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        resp.sendRedirect("pages/list.jsp");
    }
}
