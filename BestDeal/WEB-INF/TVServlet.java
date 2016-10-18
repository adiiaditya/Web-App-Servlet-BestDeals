/*
 * TVServlet.java
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

public class TVServlet extends HttpServlet {
    
    //List to hold product object
    private static List<Product> productsList = null;
    
    String logoutTag = null;
    String trackTag = null;
    String homeTag = null;
    String navHomeTag = null;
    
    protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
         
       HttpSession session = request.getSession(false);
        if(session!=null)
        {
             logoutTag = "<li class='end'><a href='/BestDeal/LogOutServlet'>Sign Out</a></li>";
             trackTag = "<li class='end'><a href='trackOrder.html'>Track Order</a></li>";
             homeTag= "<h1><a href='/BestDeal/HomeServlet'>Best<span>Deal</span></a></h1>";
             navHomeTag="<li class='start selected'><a href='/BestDeal/HomeServlet'>Home</a></li>";
        }
        else
        {
            logoutTag = "<li class='end'><a href='/BestDeal/login.html'>Sign In</a></li>"; 
            trackTag  = "";
            homeTag = "<h1><a href='index.html'>Best<span>Deal</span></a></h1>";
            navHomeTag="<li class='start selected'><a href='index.html'>Home</a></li>";
        }
        
        SaxParser4BestDealXMLdataStore dataStore = new SaxParser4BestDealXMLdataStore("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/ProductCatalog.xml");
        productsList = dataStore.getProductList();
        
        PrintWriter out = response.getWriter();
        
        String docType = 
        "<!doctype html>\n";
        out.println(docType +
                "<html>"+
                "<head>"+
                "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
                "<title>Best Deal</title>"+
                "<link rel='stylesheet' href='styles.css' type='text/css' />"+
                "</head>"+
                "<body>"+
                "<div id='container'>"+
                "<header>"+
               ""+homeTag+""+
                "<h2>Expert Service. Unbelievable Deals.</h2>"+
                "</header>"+
                "<nav>"+
    	        "<ul>"+
        	        ""+navHomeTag+""+
                    "<li class='dropdown'>"+
                    "<a class='dropbtn' href=''>Products</a>"+
                    "<div class='dropdown-content'>"+
                    "<a href='/BestDeal/MobileServlet'>Mobile Phones</a>"+
                    "<a href='/BestDeal/TabletServlet'>Tablets</a>"+
                    "<a href='/BestDeal/LaptopServlet'>Laptops</a>"+
                    "<a href='/BestDeal/TVServlet'>TV</a>"+
                    "</div>"+
                    "</li>"+
                    "<li><a href='/BestDeal/AccessoryServlet'>Accessories</a></li>"+
                    "<li><a href='#'>Deals</a></li>"+
                    ""+trackTag+""+
                    ""+logoutTag+""+
                "</ul>"+
                "</nav>"+
                "<div id='body'>"+
                "<section id='content'>"+
                "<br/>"+
                "<div class='panelHeader'>"+
                "<b>TV</b>"+
                "</div>"+
                "<article class='expanded'>"+
                "<table>");
                
                //Print Product information
                for(Product product : productsList)
                {
                    if(product.type.equals("TV"))
                    {
                        out.println("<tr>");
                        out.println("<td>"); 
                        out.println("<img src = 'images/"+product.image + " ' "+"width = '200' height = '200'  />");
                        out.println("</td>");
                        out.println("<td>");
                        out.println("<b>Product Name: </b>"+product.name+"</br>");
                        out.println("<b>Brand: </b>"+product.brand+"</br>");
                        out.println("<b>Description: </b>"+product.description+"</br>");
                        out.println("<b>Price: </b>"+product.price+ "$"+"</br>");
                        out.println("<b>Manufacturer: </b>"+product.brand+"</br>");
                        out.println("<form class = 'button' method = 'get' action = '/BestDeal/AddToCartServlet'>");
                        out.println("<input type = 'hidden' name = 'hiddenProductID' value = '"+product.id+"'>");
                        out.println("<input type = 'hidden' name = 'hiddenProductName' value = '"+product.name+"'>");
                        out.println("<input type = 'hidden' name = 'hiddenProductPrice' value = '"+product.price+"'>");
                        out.println("</td>");
                        out.println("<td>");
                        out.println("<input type='image' name='submit' src='https://www.paypalobjects.com/webstatic/en_US/i/btn/png/btn_addtocart_120x26.png' alt='Add to Cart'>");
                        out.println("</br>");
                        out.println("</br>");
                        out.println("</form>");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                }
                
                //Footer
                out.println(
                    "</table>"+
                    "</article>"+
                    "</section>"+
                    "<aside class='sidebar'>"+
	                "<ul>	"+
                    "<li>"+
                    "<h4>Shop By Products</h4>"+
                    "<ul>"+
                        "<li><a href='/BestDeal/MobileServlet'>Smart Phones</a></li>"+
                        "<li><a href='/BestDeal/TabletServlet'>Tablets</a></li>"+
                        "<li><a href='/BestDeal/LaptopServlet'>Laptops</a></li>"+
                        "<li><a href='/BestDeal/TVServlet'>Television</a></li>"+
                    "</ul>"+
                    "</li>"+
                    "<li>"+
                    "<h4>Contact us</h4>"+
                    "<ul>"+
                    "<li class='text'>"+
                    "<p style='margin: 0;'>2901 S King Dr, Chicago-60616</p>"+
                    "</li>"+
                    "</ul>"+
                    "</li>  "+
                    "</ul>"+
                    "</aside>"+
                    "<div class='clear'></div>"+
                    "</div>"+
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
