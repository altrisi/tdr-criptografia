package alejandrotrillo.tdr.rsa;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.interficies.ClausImprimible;

public final class ClausRSA implements ClausImprimible {
	private final BigInteger modul, clauPublica, clauPrivada, xifrat;
	private boolean teXifrat = true;
	
	public BigInteger getModul() {
		return modul;
	}
	
	public BigInteger getClauPrivada() {
		return clauPrivada;
	}
	
	public BigInteger getClauPublica() {
		return clauPublica;
	}
	
	public BigInteger getXifrat() {
		if (!teXifrat) throw new NullPointerException("Aquesta Claus no té xifrat vinculat");
		return xifrat;
	}
	
	public boolean teXifrat() {
		return teXifrat;
	}
	
	public void imprimirDades() {
		Utils.println("Clau pública: "+getClauPublica());
		Utils.println("Clau privada: "+getClauPrivada());
		Utils.println("Mòdul:        "+getModul());
		if (teXifrat) {
			Utils.println("Xifrat:       "+getXifrat());
		}
	}
	
	public ClausRSA(BigInteger modul, BigInteger clauPublica, BigInteger clauPrivada, BigInteger xifrat) {
		this.modul = modul;
		this.clauPublica = clauPublica;
		this.clauPrivada = clauPrivada;
		this.xifrat = xifrat;
	}
	
	public ClausRSA(BigInteger modul, BigInteger clauPublica, BigInteger clauPrivada) {
		this(modul,clauPublica,clauPrivada,null);
		teXifrat = false;
	}
}
