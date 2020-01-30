import java.util.ArrayList;

public class Magazzino {
	
private ArrayList <Articolo> ElencoArticoli;

public ArrayList <Articolo> getElencoArticoli() {
	return ElencoArticoli;
}

public void setElencoArticoli(ArrayList <Articolo> elencoArticoli) {
	ElencoArticoli = elencoArticoli;
}

public void addToElencoArticoli(Articolo a) {
	ElencoArticoli.add(a);
}

public void removeFromElencoArticoli(Articolo a) {
	ElencoArticoli.remove(a);
}

}
