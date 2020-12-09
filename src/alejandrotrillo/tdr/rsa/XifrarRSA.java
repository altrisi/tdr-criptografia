package alejandrotrillo.tdr.rsa;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;

public class XifrarRSA extends BaseRSA {
	private BigInteger missatge, resultat;
	
	XifrarRSA() {
		obtenirClaus();
		xifrar();
		imprimirResultat();
		Utils.pausa();
	}

	public XifrarRSA(ClausRSA claus, BigInteger missatge){
		this.claus = claus;
		this.missatge = missatge;
		xifrar();
	}
	
	@Override
	protected void obtenirClaus() {
		super.obtenirClaus();
		
		claus.imprimirDades();
		missatge = Utils.stringABigInt(Utils.llegir("Missatge a xifrar:"));
	}
	
	public void xifrar() {
		//TODO alertar si missatge < claus?
		BigInteger resultat = missatge.modPow(claus.getClauPublica(), claus.getModul()); // m^e mod n
		this.resultat = resultat;
		claus = new ClausRSA(claus.getModul(), claus.getClauPublica(), claus.getClauPrivada(), resultat);
	}

	private void imprimirResultat() {
		Utils.println("Missatge xifrat: "+resultat);	
	}
	
}
