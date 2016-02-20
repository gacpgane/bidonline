/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.bidmanagement;

import com.bid.online.presentation.model.bidmanagement.ImageView;
import com.bid.online.service.bidmanagement.BidServiceLocal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bid.online.persistence.bid.Image;

/**
 *
 * @author prabuddha
 */
public class UploadFile extends HttpServlet {
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
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String url = "";
        FileItem item = null;
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List items = upload.parseRequest(request);
                Iterator it = items.iterator();

                while (it.hasNext()) {
                    item = (FileItem) it.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        String root = getServletContext().getRealPath("/");
                        File path = new File(root + "/uploads");
                        if (!path.exists()) {
                            boolean status = path.mkdir();
                        }
                        File uploadedFile = new File(path + "/" + fileName);
                        //System.out.println(uploadedFile.getAbsolutePath());
                        url = "uploads" + "/" + fileName;
                        item.write(uploadedFile);
                    }
                }

            } catch (FileUploadException e) {

            } catch (Exception e) {

            }
            Gson gson = new Gson();
            JsonObject jResponse = new JsonObject();
            if (item != null) {
                Image img=new Image();
                img.setName(item.getName());
                img.setUrl(url);
                img=bidService.createImage(img);
                
                ImageView imgView = new ImageView();
                imgView.setId(String.valueOf(img.getId()));
                imgView.setName(item.getName());
                imgView.setUrl(url);
                JsonElement imgJason = gson.toJsonTree(imgView);
                jResponse.addProperty("success", Boolean.TRUE);
                jResponse.add("img", imgJason);
            }else{
                jResponse.addProperty("success", Boolean.FALSE);
            }
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
