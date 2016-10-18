import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.*;
import java.util.Set;
import java.util.Date;
import java.util.Random;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class SubmitOrder extends HttpServlet {
	
	private static Order order = null;
	private List<Order> ordersList = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			//Get the values from the form
			String prodIDs = request.getParameter("hiddenProductIDs");
			String prodTotal = request.getParameter("hiddenOrderTotal");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String creditCard = request.getParameter("creditcard");

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	   		//get current date time with Date()
	   		Date date = new Date();
	   		String orderDate = dateFormat.format(date);

	   		Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());

	   		cal.add(Calendar.DATE, 14);  // add 14 days
	   		String expDeliveryDate = dateFormat.format(cal.getTime());

	   		Random r = new Random();
			int low = 10000;
			int high = 99999;
			int orderID = r.nextInt(high-low) + low;
			
			//
			
			order = new Order();
			order.setOrderId(Integer.toString(orderID));
			order.setFname(firstName);
			order.setLname(lastName);
			order.setAddress(address);
			order.setPnumber(phoneNumber);
			order.setOrderDate(orderDate);
			order.setDelDate(expDeliveryDate);
			order.setOrderTotal(Double.parseDouble(prodTotal));
			
			try {
        //XML WRITER
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/OrderCatalog.xml");
        Element root = document.getDocumentElement();
        
        ordersList = new ArrayList<Order>();
        ordersList.add(order);

        for (Order order : ordersList) {
            // order elements
            Element newOrder = document.createElement("Order");
            
            Element eId = document.createElement("orderid");
            eId.appendChild(document.createTextNode(Integer.toString(orderID)));
            newOrder.appendChild(eId);
            
            Element eFirstName = document.createElement("firstname");
            eFirstName.appendChild(document.createTextNode(firstName));
            newOrder.appendChild(eFirstName);

            Element eLastName = document.createElement("lastname");
            eLastName.appendChild(document.createTextNode(lastName));
            newOrder.appendChild(eLastName);

            Element eAddress = document.createElement("address");
            eAddress.appendChild(document.createTextNode(address));
            newOrder.appendChild(eAddress);
            
            Element ePhone = document.createElement("phonenumber");
            ePhone.appendChild(document.createTextNode(phoneNumber));
            newOrder.appendChild(ePhone);
            
            Element eOrdDate = document.createElement("orderdate");
            eOrdDate.appendChild(document.createTextNode(orderDate));
            newOrder.appendChild(eOrdDate);
            
            Element eDDate = document.createElement("deliverydate");
            eDDate.appendChild(document.createTextNode(expDeliveryDate));
            newOrder.appendChild(eDDate);
            
            Element eTotal = document.createElement("total");
            eTotal.appendChild(document.createTextNode(prodTotal));
            newOrder.appendChild(eTotal);
            

            root.appendChild(newOrder);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/OrderCatalog.xml");
        transformer.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
			
			
			
			//
										
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
                    "<b>Order Summary</b>"+
                    "</div>"+
                    "</header>"+
                    "<table>"+
                    "<tr>"+
                    "<td>OrderID: </td>"+
                    "<td>"+orderID+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>FirstName: </td>"+
                    "<td>"+firstName+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>LastName: </td>"+
                    "<td>"+lastName+"</td>"+
                	"</tr>"+
                	"<tr>"+
                   "<td>Address: </td>"+
                    "<td>"+address+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>Phone Number: </td>"+
                    "<td>"+phoneNumber+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>Order Date: </td>"+
                    "<td>"+orderDate+"</td>"+
                	"</tr>"+
                	"<tr>"+
                    "<td>Expected Delivery Date: </td>"+
                    "<td>"+expDeliveryDate+"</td>"+
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