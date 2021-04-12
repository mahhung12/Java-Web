/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Information;
import com.model.ArticleModel;
import com.model.InfoModel;
import com.model.ViewModel;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ArticleModel model = new ArticleModel();
            InfoModel infoModel = new InfoModel();
            ViewModel viewModel = new ViewModel();
            List<Information> info = infoModel.getInfoPage();

            int page = 1, pageSize = 2;
            int totalPage = model.getTotalRows(); // get number of content in DB
            if (request.getParameter("page") != null) { // check param page
                page = Integer.parseInt(request.getParameter("page")); // get param page
            }

            if (totalPage % pageSize == 0) { // calculator total page to show information
                totalPage = totalPage / pageSize;
            } else {
                totalPage = totalPage / pageSize + 1;
            }

            if (page > totalPage) {
                request.setAttribute("noContent", "No article here!");
            } else {
                request.setAttribute("content", model.getArticlesFromTo(page, pageSize));
            }
            HttpSession session = request.getSession();
            if (session.isNew()) {
                viewModel.updatedView();
            }

            request.setAttribute("view", String.format("%05d", viewModel.getView()));
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("urlCover", infoModel.getUrlCover(info));
            request.setAttribute("active", "HomeColor");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            response.sendRedirect("error.jsp");
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
