/*
 * LoginServlet.java
 *
 */
import java.io.*;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
   
    private static List<User> usersList = null;
    String realpassword = null;
    String realUserName = null;
    String realFullName = null;
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        
      SaxParser4BestDealXMLdataStore dataStore = new SaxParser4BestDealXMLdataStore("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/users.xml");
      usersList = dataStore.getUserList();
        
        if(userid != null &&
            password != null) {
        if(userid.equals("admin"))
        {
            if(password.equals("admin"))
            {
                response.sendRedirect("/BestDeal/AdminServlet");
            }
            else
            {
                showPage(response, "Admin Login Failure! Please enter the correct password");
            }
        }
        else if(userid.equals("salesman"))
        {
            if(password.equals("salesman"))
            {
                response.sendRedirect("/BestDeal/SalesmanServlet");
            }
            else
            {
                showPage(response, "Salesman Login Failure! Please enter the correct password");
            }
        }
        else{
            
            for(User user : usersList)
            {
                if(user.username.equals(userid))
                {
                     realpassword = user.password;
                     realUserName = user.username;
                     realFullName= user.fullname;
                    break;
                }
            }
            
            if(realUserName.equals(userid) &&
                    realpassword.equals(password)) {
                        HttpSession session=request.getSession();  
                        session.setAttribute("user",realFullName);  
                    showPage(response, realFullName);           
            }
            else
            {
                showPage(response, "Login Failure! You must supply correct username and password");
            }
        }
    } else
    {
        showPage(response, "Login Failure! You must supply a username and a password");
    }
 } 
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        
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
                "<h1><a href='BestDeal/HomeServlet'>Best<span>Deal</span></a></h1>"+
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
                    "<li class='end'><a href='trackOrder.html'>Track Order</a></li>"+
                    "<li class='end'><a href='/BestDeal/LogOutServlet'>Sign Out</a></li>"+
                "</ul>"+
                "</nav>"+
                "<img class='header-image' src='images/image.jpg' alt='Buildings' />"+
                "<div id='body'>"+
                "<section id='content'>"+
                "<article class='expanded'>");
                
                out.println("<h2>Hello <b>"+ message +" </b></h2>");
                
                
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
