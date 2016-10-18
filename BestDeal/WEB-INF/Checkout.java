/*
 * Checkout.java
 *
 */
 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Checkout extends HttpServlet {

    protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String productID = request.getParameter("hiddenProductID");
        String productName = request.getParameter("hiddenProductName");
        String productPrice = request.getParameter("hiddenProductPrice");
        
        HttpSession session = request.getSession();
        ShoppingCart cart;
        synchronized(session) {
        cart = (ShoppingCart)session.getAttribute("ShoppingCart");
        session.setAttribute("ShoppingCart", cart);
        cart = (ShoppingCart)session.getAttribute("ShoppingCart");
        }

        List<String> productIDs = new ArrayList<String>();
        HashMap<String, List<String>> items = cart.getProductsInCart(); 
        Double total = 0.00;
        
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
                    "<b>Checkout Page</b>"+
                    "</div>"+
                    "</header>"+
                    "<h2>Order List</h2>"+                         
                    " <form method='get' action='/BestDeal/SubmitOrder'>"+
                    "<table>"+
                    "<tr>"+
                    "<th>ProductID</th>"+
                    "<th>Product Name</th>"+
                    "<th>Price</th>"+
                "</tr>");
                for (HashMap.Entry<String, List<String>> entry : items.entrySet()) 
                {
                    String key = entry.getKey();
                    List<String> values = entry.getValue();

                    out.println("<tr>");
                    out.println("<td>ProductID: <input type='text' name='productID' value= '"+values.get(0)+"' readonly> </td>");
                    out.println("<td>Product Name: <input type='text' name='productName' value= '"+values.get(1)+"' readonly> </td>");
                    out.println("<td>Price: <input type='text' name='productPrice' value= '"+values.get(2)+"' readonly> </td>");
                    out.println("</tr>");
                    total = total + Double.parseDouble(values.get(2));
                    productIDs.add(values.get(0));
                }
                String idList = productIDs.toString();
                String prodIDS = idList.substring(1, idList.length() - 1).replace(", ", ",");
        out.println(
            "<tr>"+
            "<td colspan ='3'>Total : "+total+"</td>"+
            "</tr>"+
            "</table>"+
            "<fieldset>"+
            "<legend>Personal information:</legend>"+
            "<table>"+
            "<tr>"+
            "<td> First name: </td>"+
            "<td> <input required type='text' name='firstName'> </td>"+
            "</tr>"+
            "<tr>"+
            "<td> Last name: </td>"+
            "<td> <input required type='text' name='lastName'> </td>"+
            "</tr>"+
            "</tr>"+
            "<tr>"+
            "<td> Address: </td>"+
            "<td> <input required type='text' name='address'> </td>"+
            "</tr>"+
            "<tr>"+
            "<td> Phone: </td>"+
            "<td> <input required type='text' name='phoneNumber'> </td>"+
            "</tr>"+
            "<tr>"+
            "<td> CreditCard: </td>"+
            "<td> <input required type='password' maxlength='16' name='creditcard'> </td>"+
            "</tr>"+
            "</table>"+
            "<br><br>"+
            "<input type='hidden' name='hiddenOrderTotal' value='"+total+"'>"+
            "<input type='hidden' name='hiddenProductIDs' value='"+prodIDS+"'>"+
            "<center><input class = 'submit-button' type = 'submit' name = 'orderButton' value = 'Place Order'></center>"+
            "</fieldset>"+
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
