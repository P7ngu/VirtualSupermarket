public class Articolo {
	private String name;
	private String id;
	private double price;
	
	public Articolo(String name, String id, double price) {
		this.setName(name);
		this.setId(id);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}
