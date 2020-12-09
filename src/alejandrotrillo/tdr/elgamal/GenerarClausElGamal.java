package alejandrotrillo.tdr.elgamal;

import java.math.BigInteger;
import alejandrotrillo.tdr.Utils;

import static java.math.BigInteger.ONE;

public class GenerarClausElGamal {
	private BigInteger p, g, x, y;
	private ClausElGamal claus;
	
	public ClausElGamal getClaus() {
		return claus;
	}
	
	public GenerarClausElGamal(BigInteger p, BigInteger g, BigInteger x) {
		this.p = p;
		this.g = g;
		this.x = x;
		produirClauY();
	}
	 
	
	GenerarClausElGamal() {
		triarPrimers();
		triarX();
		produirClauY();
		claus.imprimirDades();
		Utils.pausa();
	}
	
	public static BigInteger randomBigInt(BigInteger maxim) {
		maxim = maxim.subtract(ONE);
		int longitud = maxim.bitLength();
		BigInteger resultat =  (new BigInteger(longitud, Utils.random)).add(ONE);
		if (resultat.compareTo(maxim) >= 0)
			resultat = resultat.mod(maxim).add(ONE);
		return resultat;
	}
	
	private void triarPrimers() {
		boolean aleatoris = Utils.llegirBool("Utilitzar primer p i nre g menor que p aleatoris?");
		if (aleatoris) {
			int bits = Utils.llegirInt("Escull nombre de bits per p:");
			p = Utils.generarPrimer(bits);
			g = randomBigInt(p.subtract(ONE));
			if(bits < 256) 
				Utils.println("!! Tingues en compte que la llargada màxima del missatge a xifrar dependrà de la d'aquests primers, i el teu nombre és baix");
		} else {
			p = Utils.llegirBigInt("Escull un primer p:");
			g = Utils.llegirBigInt("Escull un nre g menor que p");
			if (g.compareTo(p) >= 0) {
				Utils.println("El nombre g ha de ser menor que p");
				triarPrimers();
			}
		}
	}
	
	private void triarX() {
		boolean aleatori;
		aleatori = Utils.llegirBool("Utilitzar x aleatori?");
		if (aleatori) {
			x = new BigInteger(512, Utils.random);
		} else {
			x = Utils.llegirBigInt("Escull un nombre x:");
		}
	}
	
	private void produirClauY() {
		y = g.modPow(x, p); // G^x mod P
		claus = new ClausElGamal(new ClausElGamal.ClauPublica(p, g, y), x);
	}
	
}
