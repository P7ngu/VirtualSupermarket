import java.util.Date;

public class Utente {
	private String nomeUtente;
	private String password;
	
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Utente(String nomeUtente, String Password) {
		this.setNomeUtente(nomeUtente);
		this.setPassword(Password);
	}
	
}
