package alejandrotrillo.tdr.rsa;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;

public class DesxifrarRSA extends BaseRSA {
	private BigInteger xifrat;
	private String resultat;
	
	DesxifrarRSA() {
		obtenirClaus();
		desxifrar();
		imprimirResultat();
		Utils.pausa();
	}
	
	public DesxifrarRSA(ClausRSA claus){
		this.claus = claus;
		if (!claus.teXifrat()) throw new IllegalArgumentException("El Claus no té xifrat");
		xifrat = claus.getXifrat();
		desxifrar();
	}
	
	public String getResultat() {
		return resultat;
	}
	
	@Override
	protected void obtenirClaus() {
		super.obtenirClaus();
		
		boolean utilitzarUltimMissatge = false;
		if (claus.teXifrat()) {
			utilitzarUltimMissatge = Utils.llegirBool("Utilitzar últim missatge xifrat?");
		}
		if (!utilitzarUltimMissatge) {
			xifrat = Utils.llegirBigInt("Xifrat a desxifrar:");
			//claus = new Claus(claus.getXifrat(), claus.getClauPublica(), claus.getClauPrivada());
			
		} else {
			xifrat = claus.getXifrat();
		}
		claus.imprimirDades();
	}
	
	private void desxifrar() {
		resultat = Utils.bigIntAString(xifrat.modPow(claus.getClauPrivada(), claus.getModul())); //m^d mod n
	}
	
	private void imprimirResultat() {
		Utils.println("Missatge desxifrat: "+resultat);
	}
}
