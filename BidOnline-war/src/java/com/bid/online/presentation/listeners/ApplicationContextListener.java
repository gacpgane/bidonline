/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.listeners;

import com.bid.online.presentation.model.bidmanagement.CategoryView;
import com.bid.online.service.bidmanagement.BidServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.bid.online.persistence.bid.Category;
import com.bid.online.persistence.bid.SubCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Web application lifecycle listener.
 *
 * @author prabuddha
 */
public class ApplicationContextListener implements ServletContextListener {

    @EJB
    private BidServiceLocal bidService;
    private List<CategoryView> categoryList;
    private Map<String,List<CategoryView>> subCategoryMap;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initCategoryList();
        sce.getServletContext().setAttribute("categoryList", categoryList);
        sce.getServletContext().setAttribute("subCategoryMap", subCategoryMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        categoryList = null;
    }

    private void initCategoryList() {
        categoryList = new ArrayList<>();
        List<Category> categories = bidService.getCategoryList();
        subCategoryMap=new HashMap<>();
        for (Category c : categories) {
            CategoryView view = new CategoryView();
            view.setCode(String.valueOf(c.getId()));
            view.setName(c.getDescription());
            List<CategoryView> subCategories = new ArrayList<>();
            for (SubCategory s : c.getSubCategories()) {
                CategoryView subView = new CategoryView();
                subView.setCode(String.valueOf(s.getId()));
                subView.setName(s.getDescription());
                subCategories.add(subView);
            }
            
            view.setSubCategories(subCategories);
            categoryList.add(view);
            subCategoryMap.put(view.getCode(),subCategories);
        }

    }

}
