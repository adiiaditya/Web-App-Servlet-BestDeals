/*
 * DeleteFromCartServlet.java
 *
 */
 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DeleteFromCartServlet extends HttpServlet {

    private static List<String> productDetailsList = null;
    private static HashMap<String, List<String>> productsInCart = null;
     
    protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String productID = request.getParameter("hiddenDelProdID");
        
        HttpSession session = request.getSession();
        ShoppingCart cart;
        cart = (ShoppingCart) session.getAttribute("ShoppingCart");
        cart.deleteFromCart(productID);
        session.setAttribute("ShoppingCart", cart);
        cart = (ShoppingCart) session.getAttribute("ShoppingCart");
        
        productsInCart = cart.getProductsInCart();
        
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
            
            
            for(String key : productsInCart.keySet()) {
            for (HashMap.Entry<String, List<String>> entry : productsInCart.entrySet()) {
            List<String> values = entry.getValue();
            out.println("<tr>");
            out.println("<td>"+values.get(0)+"</td>");
            out.println("<td>"+values.get(1)+"</td>");
            out.println("<td>"+values.get(2)+"</td>");
            out.println("<td>"+values.get(3)+"</td>");
            }
            out.println("<td><form method = 'get' action = '/BestDeal/DeleteFromCartServlet' ''><input type='hidden' name='hiddenDelProdID' value='"+key+"'><input class = 'submit-button' type = 'submit' name = 'deleteButton' value = 'Delete'></form></td>");
            out.println("</tr>");
            }
            out.println(
             "</table>"+
            "<a href='/BestDeal/HomeServlet'>Continue Shopping</a>"+
            "</br></br>"+
            "<form class = 'submit-button' method = 'get' action = '/BestDeal/Checkout'>"+
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
