/*
 * TrackOrder.java
 *
 */

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class TrackOrder extends HttpServlet {
	
	//List to hold order object
	private List<Order> ordersList = null;
	
	protected void processPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
         
         String orderID = request.getParameter("trackOrderID");
         String orderDate = null;
         String expectedDeliveryDate = null;
         String firstName = null;
         String lastName = null;
        
        SaxParser4BestDealXMLdataStore dataStore = new SaxParser4BestDealXMLdataStore("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/OrderCatalog.xml");
        ordersList = dataStore.getOrderList();
			
        for (Order order : ordersList) {
        	if(order.orderid.equals(orderID))
        	{
        		orderDate = order.orderDate;
        		expectedDeliveryDate = order.delDate;
        		firstName = order.fname;
        		lastName = order.lname;
        	}
         }
        								
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
                    "<h1><a href='/BestDeal/HomeServlet'>Best<span>Deal</span></a></h1>"+
                    "<h2>Expert Service. Unbelievable Deals.</h2>"+
                    "<div class='panelHeader'>"+
                    "<b>Order Status</b>"+
                    "</div>"+
                    "</header>"+
                    "<table>"+
                    "<tr>"+
                    " <form method='get' action='/BestDeal/CancelOrder'>"+
                    "<td>OrderID: </td>"+
                    "<td>"+orderID+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>Order Date: </td>"+
                    "<td>"+orderDate+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>Expected Delivery Date: </td>"+
                    "<td>"+expectedDeliveryDate+"</td>"+
                	"</tr>"+
                	"<tr>"+
                	"<input type='hidden' name='orderID' value='"+orderID+"'>"+
                	"<input type='hidden' name='fName' value='"+firstName+"'>"+
                	"<input type='hidden' name='lName' value='"+lastName+"'>"+
                	"<center><input class = 'submit-button' type = 'submit' name = 'cancelButton' value = 'Cancel Order'></center>"+
                    "</tr>"+
                    "</form>"+
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