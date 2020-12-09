package alejandrotrillo.tdr.rsa;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.excepcions.UsuariCancelaException;

public class GenerarClausRSA {
	private BigInteger exponent, p, q, d, modul, euler;
	private ClausRSA claus;
	
	public ClausRSA getClaus() {
		return claus;
	}
	
	public GenerarClausRSA (BigInteger exponent, BigInteger p, BigInteger q) {
		this.exponent = exponent;
		this.p = p;
		this.q = q;
		produirModulEulerIClaus();
	}
	
	GenerarClausRSA() {
		triarExponent();
		triarPrimers();
		produirModulEulerIClaus();
		claus.imprimirDades();
		Utils.pausa();
	}
	
	private void triarExponent() {
		int exp = Utils.llegirInt("Escull exponent públic (3, 5, 17, 257 o 65537):");
		if (exp != 3 && exp != 5 && exp != 17 && exp != 257 && exp != 65537) {triarExponent(); return;};
		
		exponent = new BigInteger(String.valueOf(exp));
	}
	
	private void triarPrimers() {
		boolean aleatoris;
		aleatoris = Utils.llegirBool("Utilitzar primers p, q aleatoris?");
		if(aleatoris) {
			int bits = Utils.llegirInt("Escull nombre de bits:");
			p = Utils.generarPrimer(bits);
			q = Utils.generarPrimer(bits);
			if(bits < 256) 
				Utils.println("!! Tingues en compte que la llargada màxima del missatge a xifrar dependrà de la d'aquests primers, i el teu nombre és baix");
		}else {
			p = Utils.llegirBigInt("Escull un primer p:");
		
			q = Utils.llegirBigInt("Escull un primer q:");
		}
	}
	
	private void produirModulEulerIClaus() {
		try {
			modul = p.multiply(q); 
			euler = p.subtract(ONE).multiply(q.subtract(ONE)); // ϕ(n)=(p−1)*(q−1)
			d = exponent.modInverse(euler);
			claus = new ClausRSA(modul, exponent, d);
		} catch (ArithmeticException e) {
			Utils.println("Problema d'aritmètica produint claus:");
			Utils.println(e.toString().replace("BigInteger", "Enter").replace("not", "no"));
			Utils.println("És probable que els nombres escollits no siguin compatibles entre ells");
			Utils.pausa();
			throw new UsuariCancelaException();
		}
		//TODO Comprovar MCD -- Kind-of fet
	}
	
}
