/*
 * AddToCartServlet.java
 *
 */
 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddToCartServlet extends HttpServlet {
    
    //List to hold product object
    private static List<String> productDetailsList = null;
    private static HashMap<String, List<String>> productsInCart = null;
    
    protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
        
        String productID = request.getParameter("hiddenProductID");
        String productName = request.getParameter("hiddenProductName");
        String productPrice = request.getParameter("hiddenProductPrice");
        
        productDetailsList = new ArrayList<String>();
        productDetailsList.add(productID);
        productDetailsList.add(productName);
        productDetailsList.add(productPrice);
        
        HttpSession session = request.getSession();
        ShoppingCart cart;
        synchronized(session) {
        cart = (ShoppingCart)session.getAttribute("ShoppingCart");
        // New visitors get a fresh shopping cart.
        // Previous visitors keep using their existing cart.
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("ShoppingCart", cart);
        }
        cart.addToCart(productID, productDetailsList);
        session.setAttribute("ShoppingCart", cart);
        productsInCart = cart.getProductsInCart();
        }
        
        PrintWriter out = response.getWriter();
        
        String docType = 
          "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 "+
        "Transitional//EN\">\n";
        out.println(docType +
                "<html>"+
                "<head>"+
                "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
                "<title>BestDeal: Expert Service. Unbelievable Deals.</title>"+
                "<link rel='stylesheet' href='styles.css' type='text/css' />"+
                "</head>"+
                "<body>"+
                "<div id='container'>"+
                "<header>"+
                "<h1><a href='/BestDeal/HomeServlet'>Best<span>Deal</span></a></h1>"+
                "<h2>Expert Service. Unbelievable Deals.</h2>"+
                "<div class='panelHeader'>"+
                "<b>Shopping Cart</b>"+
                "</div>"+
                "</header>"+
                "<table>"+
                "<tr>"+
                "<th>ProductID</th>"+
                "<th>Product Name</th>"+
                "<th>Price</th>"+
                "<th></th>"+
                "</tr>");
                
                //Print Product information
                for(HashMap.Entry<String, List<String>> entry : productsInCart.entrySet())
                {
                   String key = entry.getKey();
                    List<String> values = entry.getValue();
                    out.println("<tr>");
                    out.println("<td>"+values.get(0)+"</td>");
                    out.println("<td>"+values.get(1)+"</td>");
                    out.println("<td>"+values.get(2)+"</td>");
                    out.println("<td><form method = 'get' action = '/BestDeal/DeleteFromCartServlet'><input type='hidden' name='hiddenDelProdID' value='"+key+"'><input class = 'submit-button' type = 'submit' name = 'deleteButton' value = 'Delete'></form></td>");
                    out.println("</tr>");
                }
             out.println(
            "</table>"+
            "<a href='index.html'>Continue Shopping</a>"+
            "</br></br>"+
            "<form class = 'submit-button' method = 'get' action = '/BestDeal/Checkout'>"+
            "<input type = 'hidden' name = 'hiddenProductID' value='"+productID+"'>"+
            "<input type = 'hidden' name = 'hiddenProductName' value='"+productName+"'>"+
            "<input type = 'hidden' name = 'hiddenProductPrice' value='"+productPrice+"'>"+
            "<input class = 'submit-button' type = 'submit' name = 'checkout' value = 'Checkout?'>"+
            "</form>"+
            "<footer>"+
            "<div class='footer-bottom'>"+
            "<p>&copy; 2016 BestDeal. Prices and offers are subject to change. All rights reserved.</p>"+
            "</div>"+
            "</footer>"+
            "</div>"+
            "</body>"+
            "</html>");
        }
        
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processPage(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processPage(request, response);
    }
}