/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.bidmanagement;

import com.bid.online.service.bidmanagement.BidServiceLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bid.online.persistence.bid.*;
import com.bid.online.presentation.model.bidmanagement.ItemView;
import com.bid.online.presentation.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author prabuddha
 */
public class ItemByCategory extends HttpServlet {

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
        String code =request.getParameter("code");
        List<Item> itemList=bidService.getItemByCategory(Long.valueOf(code));
        
        List<ItemView> itemViewList = new ArrayList<>();
        for(Item item:itemList){
            ItemView view = new ItemView();
            view.setId(String.valueOf(item.getId()));
            view.setName(item.getName());
            view.setDescription(item.getDescription());
            Date currentDate=new Date();
            long diff=item.getBidEndDate().getTime().getTime()-currentDate.getTime();
            if(diff<0){
                diff=0;
            }
            view.setTimeInSeconds(String.valueOf((long)(diff/1000)));
            DateFormat df=new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
            view.setEndDate(df.format(item.getBidEndDate().getTime()));
            view.setStartDate(df.format(item.getBidStartDate().getTime()));
            
            StringBuilder sb=new StringBuilder();
            sb.append(item.getInitialBidValue());
            sb.append(StringUtils.SPACE);
            sb.append(StringUtils.SGD);
            view.setPrice(sb.toString());
            
            view.setImgUrl(item.getImage().getUrl());
            view.setMarketPrice(item.getActualValue().toString());
            itemViewList.add(view);
        }
        

          
        response.setContentType("text/html;charset=UTF-8");
        Gson gson = new Gson();
        JsonObject jResponse = new JsonObject();
        JsonElement items = gson.toJsonTree(itemViewList);
        jResponse.addProperty("success", Boolean.TRUE);
        jResponse.add("items", items);
        
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
