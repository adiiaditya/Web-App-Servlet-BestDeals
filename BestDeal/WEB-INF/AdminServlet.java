/*
 * AdminServlet.java
 *
 */
 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AdminServlet extends HttpServlet {

    //List to hold product object
    private static List<Product> productsList = null;
    
    protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
        
      SaxParser4BestDealXMLdataStore dataStore = new SaxParser4BestDealXMLdataStore("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/ProductCatalog.xml");
       productsList = dataStore.getProductList();
        
        
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
            "<b>Admin Panel</b>"+
            "</div>"+
            "</header>"+
            "<form class = 'button' method = 'get' action = 'login.html'>"+
            "<p>Welcome <b>Admin</b>! <input style='float: right;' class = 'submit-button' type = 'submit' name = 'signout' value = 'Sign Out'></p>"+
            "<a href='/BestDeal/AddProduct.html'>Add Products</a>"+
            "</form>"+
            "<table>"+
            "<tr>"+
            "<th>ProductID</th>"+
            "<th>Name</th>"+
            "<th>Description</th>"+
            "<th>Type</th>"+
            "<th>Brand</th>"+
            "<th>Price</th>"+
            "<th></th>"+
            "</tr>");
            
            //Print Product information
            for(Product product : productsList)
            {
            out.println("<tr>"+
            "<td contenteditable='true' >"+product.id+"</td>"+
            "<td contenteditable='true'>"+product.name+"</td>"+
            "<td contenteditable='true'>"+product.description+"</td>"+
            "<td contenteditable='true'>"+product.type+"</td>"+
            "<td contenteditable='true'>"+product.brand+"</td>"+
            "<td contenteditable='true'>"+product.price+ "$"+"</td>"+
            "<form class = 'button' method = 'get' action = '/BestDeal/DeleteProductServlet'>"+
            "<input type = 'hidden' name = 'hiddenProductID' value = '"+product.id+"'>"+
            "<td><input class = 'submit-button' type = 'submit' name = 'deleteButton' value = 'Delete'></form>  <input class = 'submit-button' type = 'submit' name = 'updateButton' value = 'Update'>"+
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
