package alejandrotrillo.tdr.elgamal;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.interficies.ClausImprimible;

public final class ClausElGamal implements ClausImprimible {
	private final BigInteger x;
	private boolean teXifrat = true;
	private final ClauPublica clauPublica;
	private final Xifrat xifrat;
	
	public BigInteger getClauPrivada() {
		return x;
	}
	
	public BigInteger getP() {
		return clauPublica.p;
	}
	
	public ClauPublica getClauPublica() {
		return clauPublica;
	}
	
	public Xifrat getXifrat() {
		if (!teXifrat) throw new NullPointerException("Aquesta Claus no té xifrat vinculat");
		return xifrat;
	}
	
	public boolean teXifrat() {
		return teXifrat;
	}
	
	public void imprimirDades() {
		Utils.println("Clau pública: "+getClauPublica());
		Utils.println("Clau privada: "+getClauPrivada());
		if (teXifrat) {
			Utils.println("Xifrat:       "+getXifrat());
		}
	}
	
	public ClausElGamal(ClauPublica clauPublica, BigInteger clauPrivada, Xifrat xifrat) {
		this.x = clauPrivada;
		this.clauPublica = clauPublica;
		this.xifrat = xifrat;
	}
	
	public ClausElGamal(ClauPublica clauPublica, BigInteger clauPrivada) {
		this(clauPublica, clauPrivada, null);
		teXifrat = false;
	}
	
	public static class ClauPublica {
		public final BigInteger p,g,y;
		
		public ClauPublica(BigInteger p, BigInteger g, BigInteger y) {
			this.p = p;
			this.g = g;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "{p = "+p+", g = "+g+", y = "+y+"}";
		}
	}
	
	public static class Xifrat {
		public final BigInteger a,b;
		
		public Xifrat(BigInteger a, BigInteger b){
			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return "{a = "+a+", b = "+b+"}";
		}
	}
}
