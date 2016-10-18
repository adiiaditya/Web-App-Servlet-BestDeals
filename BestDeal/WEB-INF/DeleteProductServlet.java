/*
 * DeleteProductServlet.java
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

public class DeleteProductServlet extends HttpServlet {

    protected void register(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String productIDToDelete = request.getParameter("hiddenProductID");
        
        try {
        //XML WRITER
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/ProductCatalog.xml");
        
        NodeList nodesList = document.getElementsByTagName("Product");
        
        if (nodesList != null && nodesList.getLength() > 0) {
                for (int i = 0; i < nodesList.getLength(); i++) {
                    Node node = nodesList.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        Node removeNode = element.getElementsByTagName("id").item(0);
                        String id = removeNode.getChildNodes().item(0).getNodeValue();
                        if (id.equals(productIDToDelete)) {
                        node.getParentNode().removeChild(node);
                    }
                }
            }
        }
            
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/ProductCatalog.xml");
        transformer.transform(source, result);
        showPage(response, "Product has been deleted successfully");
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
            "<title>BestDeal: Expert Service. Unbelievable Deals.</title>"+
            "<link rel='stylesheet' href='styles.css' type='text/css' />"+
            "</head>"+
            "<body>"+
            "<div id='container'>"+
            "<header>"+
            "<h1><a href='index.html'>Best<span>Deal</span></a></h1>"+
            "<h2>Expert Service. Unbelievable Deals.</h2>"+
             "<div class='panelHeader'>"+
            "<b>" + message + "</b>"+
            "</div>"+
            "</header>"+
            "<a href='/BestDeal/AdminServlet'><h2>Back to Admin Panel</h2></a>"+
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
