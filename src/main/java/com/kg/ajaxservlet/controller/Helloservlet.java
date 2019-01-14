
package com.kg.ajaxservlet.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kg.ajaxservlet.model.Country;

/**
 * Helloservlet
 */
@WebServlet("/country/*")
public class Helloservlet extends HttpServlet {
    ArrayList<Country> countryList = new ArrayList<Country>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        System.out.println("do get called");
        String query = "SELECT * from country";
        try {

            List<Object> list = MysqlConnect.getDbCon().resultSetToArrayList(query);
            List<Country> countryList = (List<Country>) (List<?>) list;

            String countryJsonString = new Gson().toJson(countryList);
            System.out.println(countryJsonString);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(countryJsonString);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doDelete(req, resp);
        // setAccessControlHeaders(resp);
        System.out.println("do delete called");
        int countryid = Integer.parseInt(req.getParameter("countryid"));
        System.out.println("countryid " + countryid);
        String sql = "DELETE FROM country WHERE countryid=" + countryid;

        // String pathInfo = req.getPathInfo();
        // System.out.println("pathInfo " + Integer.parseInt(pathInfo));
        try {
            int res = MysqlConnect.getDbCon().delete(sql);
            System.out.println("deleted rows" + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doput method called");
        setAccessControlHeaders(resp);

        String requestData = req.getReader().lines().collect(Collectors.joining());
        System.out.println("????? requestData ?????\n\n" + requestData + "\n\n");
  
        Gson gson = new Gson();
        Country country = gson.fromJson(requestData, Country.class);
  
        System.out.println("$$$$$$$$$$$$$$");
        System.out.println(country.getCountryid()+" ---->"+country.getCountryname());

       int countryid = country.getCountryid();
String countryname=country.getCountryname();
       
        // int countryid = Integer.parseInt(req.getParameter("countryid"));
        // System.out.println("countryid " + countryid);
        // String countryname = req.getParameter("countryname");
        // System.out.println("countryname " + countryname);
        String sql = "INSERT INTO  country(countryid,countryname)values(" + countryid + ",'" + countryname + "')";
        System.out.println(sql);
        try {
            int res = MysqlConnect.getDbCon().insert(sql);
            System.out.println("inserted rows" + res);
        } catch (SQLException e) {
            // TODO: handle
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
       
        System.out.println("do post called");
        setAccessControlHeaders(resp);

        String requestData = req.getReader().lines().collect(Collectors.joining());
        System.out.println("????? requestData ?????\n\n" + requestData + "\n\n");
  
        Gson gson = new Gson();
        Country country = gson.fromJson(requestData, Country.class);
  
        System.out.println("$$$$$$$$$$$$$$");
        System.out.println(country.getCountryid()+" ---->"+country.getCountryname());

        int countryid = country.getCountryid();
        String countryname=country.getCountryname();
               
        // int countryid = Integer.parseInt(req.getParameter("countryid"));
        // System.out.println("countryid " + countryid);
        // String countryname = req.getParameter("countryname");
        // System.out.println("countryname " + countryname);
        // System.out.println(countryid,countryname);
        String sql="UPDATE country SET countryid="+countryid+",countryname='"+countryname+"' WHERE countryid="+countryid;
        System.out.println(sql);
        try {
    int res = MysqlConnect.getDbCon().update(sql);
    System.out.println("updated rows" + res);
} catch (SQLException e) {
    //TODO: handle exception
    e.printStackTrace();
}
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:9090");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Methods", "DELETE");
        
        

    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}