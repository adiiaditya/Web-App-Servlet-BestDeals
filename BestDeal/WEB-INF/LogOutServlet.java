/*
 * LogOutServlet.java
 *
 */
import java.io.*;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogOutServlet extends HttpServlet {
   
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
            response.setContentType("text/html;charset=UTF-8");  
            PrintWriter out=response.getWriter();     
            
            HttpSession session=request.getSession();  
            session.invalidate();      
            
            
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
                "<h1><a href='index.html'>Best<span>Deal</span></a></h1>"+
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
                    "<li><a href='#'>Deals</a></li>"+
                    "<li class='end'><a href='login.html'>Sign In</a></li>"+
                "</ul>"+
                "</nav>"+
                "<img class='header-image' src='images/image.jpg' alt='Buildings' />"+
                "<div id='body'>"+
                "<section id='content'>"+
                "<article class='expanded'>");
                
                out.println("<h2>Welcome <b>Guest</b>!</h2>");
                
                
                //Footer
                out.println(
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
                    "<h4>About us</h4>"+
                    "<ul>"+
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
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        
    }
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
        
    }
}
