/*
 * AddProductServlet.java
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

public class AddProductServlet extends HttpServlet {
    
    private static Product product = null;
    private List<Product> productsList = null;

    protected void register(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String newProductID = request.getParameter("productID");
        String newProductName = request.getParameter("productName");
        String newProductDesc = request.getParameter("productDesc");
        String newProductType= request.getParameter("productType");
        String newProductBrand = request.getParameter("productBrand");
        String newProductPrice = request.getParameter("productPrice");
        
        product = new Product();
        product.setId(newProductID);
        product.setName(newProductName);
        product.setDescription(newProductDesc);
        product.setType(newProductType);
        product.setBrand(newProductBrand);
        product.setPrice(Integer.parseInt(newProductPrice));
        product.setRetailer("BestDeal");
        product.setImage("");
        

        try {
        //XML WRITER
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/ProductCatalog.xml");
        Element root = document.getDocumentElement();
        
        productsList = new ArrayList<Product>();
        productsList.add(product);

        for (Product product : productsList) {
            // product elements
            Element newProduct = document.createElement("Product");
            
            Element eId = document.createElement("id");
            eId.appendChild(document.createTextNode(newProductID));
            newProduct.appendChild(eId);
            
            Element eName = document.createElement("name");
            eName.appendChild(document.createTextNode(newProductName));
            newProduct.appendChild(eName);

            Element eType = document.createElement("type");
            eType.appendChild(document.createTextNode(newProductType));
            newProduct.appendChild(eType);

            Element eRetailer = document.createElement("retailer");
            eRetailer.appendChild(document.createTextNode("BestDeal"));
            newProduct.appendChild(eRetailer);
            
            Element eBrand = document.createElement("brand");
            eBrand.appendChild(document.createTextNode(newProductBrand));
            newProduct.appendChild(eBrand);
            
            Element ePrice = document.createElement("price");
            ePrice.appendChild(document.createTextNode(newProductPrice));
            newProduct.appendChild(ePrice);
            
            Element eDesc = document.createElement("description");
            eDesc.appendChild(document.createTextNode(newProductDesc));
            newProduct.appendChild(eDesc);
            
            Element eImage = document.createElement("image");
            eImage.appendChild(document.createTextNode(""));
            newProduct.appendChild(eImage);

            root.appendChild(newProduct);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult("/usr/local/Cellar/tomcat/8.5.5/libexec/webapps/BestDeal/WEB-INF/ProductCatalog.xml");
        transformer.transform(source, result);
         showPage(response, "Product has been added successfully");
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
