/*
 * SalesmanServlet.java
 *
 */

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Random;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class SalesmanServlet extends HttpServlet {
    
    //List to hold order object
    private static List<Order> ordersList = null;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            response.setContentType("text/html;charset=UTF-8");
            
            SaxParser4BestDealXMLdataStore dataStore = new SaxParser4BestDealXMLdataStore("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/OrderCatalog.xml");
           ordersList = dataStore.getOrderList();
            
            PrintWriter out = response.getWriter();

            String docType = 
        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 "+
        "Transitional//EN\">\n";
        out.println(docType + "<html>"+
            "<head>"+
            "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
            "<title>BestDeal: Expert Service. Unbelievable Deals.</title>"+
            "<link rel='stylesheet' href='styles.css' type='text/css' />"+
            "</head>"+
            "<body>"+
            "<div id='container'>"+
            "<header>"+
            "<h1><a href='index.html'>Best<span>Deal</span></a></h1>"+
            "<h2>Expert Service. Unbelievable Deals.</h2>"+
            "<div class='panelHeader'>"+
            "<b>Salesman Panel</b>"+
            "</div>"+
            "</header>"+
            "<form class = 'button' method = 'get' action = 'login.html'>"+
            "<p>Welcome <b>Salesman</b>! <input style='float: right;' class = 'submit-button' type = 'submit' name = 'signout' value = 'Sign Out'></p>"+
            "<a href='/BestDeal/registration.html'>Create User</a>"+
            "<a href=''>Create New Order</a>"+
            "</form>"+
            "<table>"+
            "<tr>"+
            "<th>OrderID</th>"+
            "<th>Full Name</th>"+
            "<th>Address</th>"+
            "<th>Order Date</th>"+
            "<th>Delivery Date</th>"+
            "<th>Total</th>"+
            "<th></th>"+
            "</tr>");
            
            //Print Order information
            for(Order order : ordersList)
            {
            out.println("<tr>"+
            "<td>"+order.orderid+"</td>"+
            "<td>"+order.fname+" "+order.lname+"</td>"+
            "<td>"+order.address+"</td>"+
            "<td>"+order.orderDate+"</td>"+
            "<td>"+order.delDate+"</td>"+
            "<td>"+order.orderTotal+ "$"+"</td>"+
            "<td><input class = 'submit-button' type = 'submit' name = 'cancelButton' value = 'Cancel'><input class = 'submit-button' type = 'submit' name = 'updateButton' value = 'Update'>"+
            "</tr>");
            }

           out.println(
            "</table>"+
            "<footer>"+
            "<div class='footer-bottom'>"+
            "<p>&copy; 2016 BestDeal. Prices and offers are subject to change. All rights reserved.</p>"+
            "</div>"+
            "</footer>"+
            "</div>"+
            "</body>"+
            "</html>");
    }
}