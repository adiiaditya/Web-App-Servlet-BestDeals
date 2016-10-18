/*
 * HomeServlet.java
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

public class HomeServlet extends HttpServlet {

    protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
         
         String logoutTag = null;
         String trackTag = null;
         
         HttpSession session = request.getSession(false);
         if(session!=null)
        {
             logoutTag = "<li class='end'><a href='/BestDeal/LogOutServlet'>Sign Out</a></li>";
             trackTag = "<li class='end'><a href='trackOrder.html'>Track Order</a></li>";
        }
        else
        {
            logoutTag = "<li class='end'><a href='/BestDeal/login.html'>Sign In</a></li>"; 
            trackTag  = "";
        }
        
        PrintWriter out = response.getWriter();
        
        String docType = 
        "<!doctype html>\n";
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
                "</header>"+
                "<nav>"+
    	        "<ul>"+
        	        "<li class='start selected'><a href='index.html'>Home</a></li>"+
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
                    ""+trackTag+""+
                    ""+logoutTag+""+
                "</ul>"+
                "</nav>"+
                "<img class='header-image' src='images/image.jpg' alt='Buildings' />"+
                "<div id='body'>"+
                "<section id='content'>"+
                "<article class='expanded'>");
                
                out.println("<h2>Hello <b>"+ session.getAttribute("user") +" </b></h2>");
                                
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
