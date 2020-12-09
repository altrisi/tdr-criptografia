package alejandrotrillo.tdr.elgamal;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;

public class DesxifrarElGamal extends BaseElGamal {
	private ClausElGamal.Xifrat xifrat;
	private String resultat;
	
	DesxifrarElGamal() {
		obtenirClaus();
		desxifrar();
		imprimirResultat();
		Utils.pausa();
	}
	
	/**
	 * Fa un desxifrat programàtic de un {@link ClausElGamal}.
	 * El procés de desxifrat s'iniciarà automàticament, i
	 * es pot recollir via {@link #getResultat()}
	 * @param claus Un {@link ClausElGamal} amb tots els paràmetres
	 */
	public DesxifrarElGamal(ClausElGamal claus) {
		this.claus = claus;
		if (!claus.teXifrat()) throw new IllegalArgumentException("El Claus no té xifrat");
		xifrat = claus.getXifrat();
		desxifrar();
	}
	
	/**
	 * @return el resultat del desxifrat.
	 */
	public String getResultat() {
		return resultat;
	}
	
	@Override
	protected void obtenirClaus() {
		demanarPrivada = true;
		super.obtenirClaus();
		
		boolean utilitzarUltimMissatge = false;
		if (claus.teXifrat()) {
			utilitzarUltimMissatge = Utils.llegirBool("Utilitzar últim missatge xifrat?");
		}
		if (!utilitzarUltimMissatge) {
			BigInteger a = Utils.llegirBigInt("Xifra a del missatge a desxifrar:");
			BigInteger b = Utils.llegirBigInt("Xifra b del missatge a desxifrar:");
			xifrat = new ClausElGamal.Xifrat(a,b);
		} else {
			xifrat = claus.getXifrat();
		}
		claus.imprimirDades();
	}
	
	private void desxifrar() {
		BigInteger a, b, p;
		a = xifrat.a;
		b = xifrat.b;
		p = claus.getP();
		
		BigInteger resultat = a.modPow(claus.getClauPrivada(),p).modInverse(p).multiply(b).mod(p); // b/(a^x) mod p, via aritmetica modular per BigInts a ((a^x mod p)^-1 mod P)*b mod p
		this.resultat = Utils.bigIntAString(resultat);
	}
	
	private void imprimirResultat() {
		Utils.println("Missatge desxifrat: "+resultat);
	}
}
