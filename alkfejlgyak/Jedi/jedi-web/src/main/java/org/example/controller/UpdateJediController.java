package org.example.controller;

import org.example.dao.JediDAO;
import org.example.dao.JediDAOImpl;
import org.example.model.Jedi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateJediController")
public class UpdateJediController extends HttpServlet {
    JediDAO jediDAO = new JediDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String jediId = req.getParameter("Id");
       if(jediId != null && !jediId.isEmpty()){
           int id = Integer.parseInt(jediId);
           Jedi jedi = jediDAO.findById(id);
           req.setAttribute("jedi",jedi);
       }
       req.getRequestDispatcher("pages/add-jedi.jsp").forward(req,resp);
    }
}
