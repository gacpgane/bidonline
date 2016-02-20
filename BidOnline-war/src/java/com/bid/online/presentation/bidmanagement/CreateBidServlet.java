/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.bidmanagement;

import com.bid.online.persistence.bid.*;
import com.bid.online.persistence.user.*;
import com.bid.online.presentation.model.bidmanagement.BidView;

import com.bid.online.presentation.util.StringUtils;
import com.bid.online.service.bidmanagement.BidServiceLocal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prabuddha
 */
public class CreateBidServlet extends HttpServlet {

    @EJB
    private BidServiceLocal bidService;

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
        String value = request.getParameter("bidValue");
        String itemId = request.getParameter("itemId");
        User user = (User) request.getSession().getAttribute(StringUtils.USER);
        Bid bid = new Bid();
        bid.setCreateTimestamp(new Date());
        Item item = bidService.getItemById(Long.valueOf(itemId));
        bid.setItem(item);
        bid.setUser(user);
        bid.setValue(new BigDecimal(value));
        bid=bidService.createBid(bid);
        bid=bidService.getHighestBid(item.getId());
        
        BidView view=new BidView();
        view.setItemId(String.valueOf(item.getId()));
        if(item.getInitialBidValue().compareTo(bid.getValue())==1){
            view.setValue(item.getInitialBidValue().toString());
        }else{
            view.setValue(bid.getValue().toString());
        }
        
        DateFormat df=new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        view.setCreateDate(df.format(bid.getCreateTimestamp()));
        
        response.setContentType("text/html;charset=UTF-8");
        Gson gson = new Gson();
        JsonObject jResponse = new JsonObject();
        JsonElement bidItem = gson.toJsonTree(view);
        jResponse.addProperty("success", Boolean.TRUE);
        jResponse.add("bidItem", bidItem);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        System.out.println(jResponse.toString());
        out.println(jResponse.toString());
        out.close();
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
