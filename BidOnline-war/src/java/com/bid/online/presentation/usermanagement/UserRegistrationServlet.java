/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.usermanagement;

import com.bid.online.persistence.user.User;
import com.bid.online.presentation.util.StringUtils;
import com.bid.online.presentation.util.messages.UIMessages;
import com.bid.online.service.usermanagement.UserServiceLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prabuddha
 */
public class UserRegistrationServlet extends HttpServlet {

    @EJB
    private UserServiceLocal userService;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String middleName = request.getParameter("middle_name");
        String lastName = request.getParameter("last_name");
        String phoneNo = request.getParameter("contact_no");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        User user = userService.findUserByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            user.setPhoneNo(phoneNo);
            user.setEmail(email);
            user.setAddress(address);
            user.setPassword(password);
            user = userService.addUser(user);
            request.setAttribute(StringUtils.MESSAGE_SUCCESS_KEY, UIMessages.USER_CREATION_SUCCESS);
            request.setAttribute(StringUtils.USER, user);
        } else {
            request.setAttribute(StringUtils.MESSAGE_ERROR_KEY, UIMessages.USER_EXIST);
        }
        
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
         
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
