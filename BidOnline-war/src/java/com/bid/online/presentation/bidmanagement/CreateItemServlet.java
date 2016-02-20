/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.bidmanagement;

import com.bid.online.persistence.bid.Image;
import com.bid.online.presentation.model.bidmanagement.ItemView;
import com.bid.online.service.bidmanagement.BidServiceLocal;
import com.bid.online.persistence.bid.Item;
import com.bid.online.persistence.bid.SubCategory;
import com.bid.online.persistence.user.User;
import com.bid.online.presentation.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prabuddha
 */
public class CreateItemServlet extends HttpServlet {

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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String marketPrice = request.getParameter("marketPrice");
        String bidPrice = request.getParameter("bidPrice");
        String code = request.getParameter("code");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String imgId = request.getParameter("imgId");
        String imgUrl = request.getParameter("imgUrl");
        
        Item item = new Item();
        item.setActualValue(new BigDecimal(marketPrice));
        SimpleDateFormat format=new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        try {
            Date bidStartDate=format.parse(startDate);
            Date bidEndDate=format.parse(endDate);
            
            Calendar cs=Calendar.getInstance();
            cs.setTime(bidStartDate);
            item.setBidStartDate(cs);
            
            Calendar ce=Calendar.getInstance();
            ce.setTime(bidEndDate);
            item.setBidEndDate(ce);
        } catch (ParseException ex) {
            Logger.getLogger(CreateItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (code != null) {
            SubCategory category = bidService.getSubCategoryById(Long.valueOf(code));
            item.setCategory(category);
        }
        item.setCreateTimestamp(new Date());
        item.setDescription(description);
        item.setInitialBidValue(new BigDecimal(bidPrice));
        item.setName(name);
        if (request.getSession().getAttribute(StringUtils.USER) != null) {
            User user = (User) request.getSession().getAttribute(StringUtils.USER);
            item.setOwner(user);
        }
        if (imgId != null) {
            Image img = bidService.getImageById(Long.valueOf(imgId));
            item.setImage(img);
        }
        item=bidService.createItem(item);
        ItemView view = new ItemView();

        view.setId(String.valueOf(item.getId()));
        view.setName(name);
        view.setPrice(bidPrice);
        view.setImgUrl(imgUrl);
        Gson gson = new Gson();
        JsonObject jResponse = new JsonObject();
        JsonElement jasonElement = gson.toJsonTree(view);

        jResponse.addProperty("success", Boolean.TRUE);
        jResponse.add("item", jasonElement);
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
