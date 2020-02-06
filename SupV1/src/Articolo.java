public class Articolo {
	
	private String name;
	private String id;
	private double price;
	private String pathFoto;
	private String Taglia;
	private String Colore;
	private int quantita;
	
	
	
	public String getTaglia() {
		return Taglia;
	}

	public void setTaglia(String taglia) {
		Taglia = taglia;
	}

	public String getColore() {
		return Colore;
	}

	public void setColore(String colore) {
		Colore = colore;
	}

	public Articolo(String name, String id, Double prezzo, String pathFoto, String taglia, String colore) {
		this.setName(name);
		this.setId(id);
		this.setPrice(prezzo);
		this.setPathFoto(pathFoto);
		this.setTaglia(taglia);
		this.setColore(colore);
	}
	
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

	private void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String nome = this.getName();
		String id = this.getId();
		double prezzo = this.getPrice();
		String taglia = this.getTaglia();
		String colore = this.getColore();
		
		return (nome+ " - ID: " +id+ " - " +prezzo+ "$ - " +taglia+ " - " +colore);
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String StampaPerMagazzino() {
		String nome = this.getName();
		String id = this.getId();
		double prezzo = this.getPrice();
		String taglia = this.getTaglia();
		String colore = this.getColore();
		int quantita = this.getQuantita();
		return (nome+ " - ID: " +id+ " - " +prezzo+ "$ - " +taglia+ " - " +colore + " - Quantità: " +quantita);
	
	}

}
