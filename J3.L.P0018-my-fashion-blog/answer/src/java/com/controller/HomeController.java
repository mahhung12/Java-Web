/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.HomeModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thuongnnse05095
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HomeModel home = new HomeModel();

            int page = 1, pageSize = 4;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            request.setAttribute("homes", home.getPost(page, pageSize));
            request.setAttribute("page", page);
            request.setAttribute("totalPage", home.getTotalPage(pageSize));
            request.getRequestDispatcher("Blog.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
