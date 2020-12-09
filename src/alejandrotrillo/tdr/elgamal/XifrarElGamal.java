package alejandrotrillo.tdr.elgamal;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;

public class XifrarElGamal extends BaseElGamal {
	private BigInteger missatge, k;
	private ClausElGamal.Xifrat resultat;
	
	XifrarElGamal() {
		obtenirClaus();
		obtenirK();
		xifrar();
		imprimirResultat();
		Utils.pausa();
	}
	
	/**
	 * Xifra program�ticament un {@link ClausElGamal}.
	 * El proc�s de xifrat s'iniciar� autom�ticament, i
	 * es pot recollir via {@link #getClaus()}
	 * @param claus Un {@link ClausElGamal} amb els par�metres necessaris
	 */
	public XifrarElGamal(ClausElGamal claus, BigInteger k, BigInteger missatge) {
		this.claus = claus;
		this.k = k;
		this.missatge = missatge;
		xifrar();
	}
	
	/**
	 * @return el resultat d'un xifrat program�tic com a {@link ClausElGamal.Xifrat}.
	 */
	public ClausElGamal.Xifrat getResultat() {
		return (ClausElGamal.Xifrat)resultat;
	}
	
	@Override
	protected void obtenirClaus() {
		super.obtenirClaus();
		
		claus.imprimirDades();
		missatge = Utils.stringABigInt(Utils.llegir("Missatge a xifrar:"));
	}
	
	private void obtenirK() {
		boolean aleatori;
		aleatori = Utils.llegirBool("Utilitzar nombre k aleatori?");
		if(aleatori) {
			k = new BigInteger(512, Utils.random);
			Utils.println("Nombre k utilitzat: "+k);
		}else {
			k = Utils.llegirBigInt("Nombre (k):");
		}
	}
	
	public void xifrar() {
		BigInteger a, b, p, g, y;
		p = claus.getClauPublica().p;
		g = claus.getClauPublica().g;
		y = claus.getClauPublica().y;
		
		a = g.modPow(k, p); // G^k mod P
		b = missatge.multiply(y.modPow(k, p)).mod(p); //(y^k)�M mod P (modificat via aritm modular per utilitzar m�todes disponibles  M*(y^k mod P) mod P +/-)
		ClausElGamal.Xifrat resultat = new ClausElGamal.Xifrat(a, b);
		this.resultat = resultat;
		
		claus = new ClausElGamal(claus.getClauPublica(), claus.getClauPrivada(), resultat);
	}
	
	public void imprimirResultat() {
		Utils.println("Missatge Xifrat: "+resultat);
	}
	
}
