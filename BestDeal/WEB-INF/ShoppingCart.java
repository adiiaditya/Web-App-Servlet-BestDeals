/*
 * ShoppingCart.java
 *
 */
 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class ShoppingCart {
        HashMap<String, List<String>> productsInCart;
        
        /** Builds an empty shopping cart. */
        
        public ShoppingCart(){
        	productsInCart = new HashMap<String,List<String>>();
    	}

    /** Returns List of ItemOrder entries giving
    *  Item and number ordered. Declared as List instead
    *  of ArrayList so that underlying implementation
    *  can be changed later.
    */

		public HashMap getProductsInCart() {
			return productsInCart;
		}
		
		public synchronized void addToCart(String productID, List<String> products){
        	productsInCart.put(productID, products);
    	}

    	public synchronized void deleteFromCart(String productID){
        	productsInCart.remove(productID);
    	}
}