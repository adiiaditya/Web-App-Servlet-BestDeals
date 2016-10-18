/*
 * CancelOrder.java
 *
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class CancelOrder extends HttpServlet {
	
	protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
         
         String orderIDToDelete = request.getParameter("orderID");
         String orderPlacedByFName = request.getParameter("fName");
         String orderPlacedByLName = request.getParameter("lName");
         
         String orderPlacer = orderPlacedByFName + " " +orderPlacedByLName;
         PrintWriter out = response.getWriter();
          HttpSession session=request.getSession(false);  
          
         if(session.getAttribute("user").equals(orderPlacer)) 
         {
            try {
        //XML WRITER
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/OrderCatalog.xml");
        
        NodeList nodesList = document.getElementsByTagName("Order");
        
        if (nodesList != null && nodesList.getLength() > 0) {
                for (int i = 0; i < nodesList.getLength(); i++) {
                    Node node = nodesList.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        Node removeNode = element.getElementsByTagName("orderid").item(0);
                        String orderid = removeNode.getChildNodes().item(0).getNodeValue();
                        if (orderid.equals(orderIDToDelete)) {
                        node.getParentNode().removeChild(node);
                    }
                }
            }
        }
            
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/OrderCatalog.xml");
        transformer.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        			 
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
                    "<b>Order Status</b>"+
                    "</div>"+
                    "</header>"+
                    "<table>"+
                    "<tr>"+
                    "<td>Your Order# "+orderIDToDelete+" has been cancelled!</td>"+
                   "</tr>"+
                	"</table>"+
                	"<a href='/BestDeal/HomeServlet'>Return to Home Page</a>"+
		            "<footer>"+
            		"<div class='footer-bottom'>"+
            		"<p>&copy; 2016 BestDeal. Prices and offers are subject to change. All rights reserved.</p>"+
            		"</div>"+
            		"</footer>"+
            		"</div>"+
            		"</body>"+
            		"</html>");

        }
        else
        {
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
                    "<b>Order Status</b>"+
                    "</div>"+
                    "</header>"+
                    "<table>"+
                    "<tr>"+
                    "<td>You have no such order!</td>"+
                   "</tr>"+
                	"</table>"+
                	"<a href='/BestDeal/HomeServlet'>Return to Home Page</a>"+
		            "<footer>"+
            		"<div class='footer-bottom'>"+
            		"<p>&copy; 2016 BestDeal. Prices and offers are subject to change. All rights reserved.</p>"+
            		"</div>"+
            		"</footer>"+
            		"</div>"+
            		"</body>"+
            		"</html>");
        }
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