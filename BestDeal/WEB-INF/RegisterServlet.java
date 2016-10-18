/*
 * RegisterServlet.java
 *
 */
 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class RegisterServlet extends HttpServlet {

    private static User user = null;
    private List<User> usersList = null;
    
    protected void register(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String userID = request.getParameter("username");
        String password = request.getParameter("password");
        
        user = new User();
        user.setFullName(name);
        user.setUsername(userID);
        user.setPassword(password);
        
        try {
        //XML WRITER
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/users.xml");
        Element root = document.getDocumentElement();
        
        usersList = new ArrayList<User>();
        usersList.add(user);

        for (User user : usersList) {
            // user elements
            Element newUser = document.createElement("user");

            Element ename = document.createElement("fullname");
            ename.appendChild(document.createTextNode(name));
            newUser.appendChild(ename);

            Element eusername = document.createElement("username");
            eusername.appendChild(document.createTextNode(userID));
            newUser.appendChild(eusername);
            
            Element epassword = document.createElement("password");
            epassword.appendChild(document.createTextNode(password));
            newUser.appendChild(epassword);

            root.appendChild(newUser);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/users.xml");
        transformer.transform(source, result);
         showPage(response, name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        String docType = 
        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 "+
        "Transitional//EN\">\n";
        out.println(docType + "<html>"+
            "<head>"+
            "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
            "<title>Best Deal - Login</title>"+
            "<link rel='stylesheet' href='styles.css' type='text/css' />"+
            "</head>"+
            "<body>"+
            "<div id='container'>"+
            "<header>"+
            "<h1>Best Deal</h1>"+
            "<h2></h2>"+
            "</header>"+
            "<h2> Hello " +  message + "! </h2>"+
            "<br>"+
            "<a href='login.html'><h2>Sign In<h2></a>"+
            "<footer>"+
            "<div class='footer-bottom'>"+
            "<p>&copy; 2016 BestDeal. Prices and offers are subject to change. All rights reserved.</p>"+
            "</div>"+
            "</footer>"+
            "</div>"+
            "</body>"+
            "</html>");
        out.close();
    }
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        register(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        register(request, response);
    }
}
