package org.example.controller;

import org.example.dao.JediDAO;
import org.example.dao.JediDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.example.model.Jedi;

@WebServlet("/JediController")
public class JediController extends HttpServlet {

    JediDAO jediDAO = new JediDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        req.setCharacterEncoding("utf8");
        int id = -1;
        try {
            if(!req.getParameter("id").equals("")){
                id = Integer.parseInt(req.getParameter("id"));
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Jedi c = jediDAO.findById(id);

        if(c == null){
            c = new Jedi();
        }
        try {
            c.setName(req.getParameter("name"));
            c.setGender(req.getParameter("gender"));
            c.setRank(req.getParameter("rank"));
            c.setCouncilmember(req.getParameter("councilmember"));
            jediDAO.save(c);
            resp.sendRedirect("pages/list.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Jedi> jedik = jediDAO.findAll();
        req.setAttribute("jedik", jedik);
    }
}
