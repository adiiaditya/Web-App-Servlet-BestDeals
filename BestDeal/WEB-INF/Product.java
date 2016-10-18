import java.util.ArrayList;
import java.util.List;

public class Product {
    String retailer;
    String name;
    String id;
    String type;
    String image;
    String brand;
    String description;
    int price;
    
	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}
}