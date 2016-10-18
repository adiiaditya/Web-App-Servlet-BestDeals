import java.util.ArrayList;
import java.util.List;

public class Order {
    String orderid;
    String fname;
    String lname;
    String address;
    String pnumber;
    String orderDate;
    String delDate;
    double orderTotal;
    
	public void setOrderId(String orderid) {
		this.orderid = orderid;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
}