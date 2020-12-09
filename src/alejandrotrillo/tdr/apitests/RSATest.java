package alejandrotrillo.tdr.apitests;

import java.math.BigInteger;
import java.security.SecureRandom;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.rsa.ClausRSA;
import alejandrotrillo.tdr.rsa.DesxifrarRSA;
import alejandrotrillo.tdr.rsa.GenerarClausRSA;
import alejandrotrillo.tdr.rsa.XifrarRSA;

import static java.math.BigInteger.*;

public class RSATest {
	SecureRandom random = new SecureRandom();
	final String missatge = "Això és una prova";
	ClausRSA clausGenerades;
	BigInteger xifrat;
	String resultat;
	
	public static void main(String[] args) {
		new RSATest().test();
	}
	
	public void test() {
		BigInteger exponent = valueOf(65537);
		BigInteger p = probablePrime(512, random);
		BigInteger q = probablePrime(512, random);
		
		GenerarClausRSA clausRSA = new GenerarClausRSA(exponent, p, q);
		clausGenerades = clausRSA.getClaus();
		
		BigInteger missatgeNumeric = Utils.stringABigInt(missatge);
		XifrarRSA xifrarRSA = new XifrarRSA(clausGenerades, missatgeNumeric);
		ClausRSA clausAmbXifrat = xifrarRSA.getClaus();
		
		xifrat = clausAmbXifrat.getXifrat();
		
		ClausRSA clausPerDesxifrar = new ClausRSA(
				clausGenerades.getModul(), 
				clausGenerades.getClauPublica(), 
				clausGenerades.getClauPrivada(), 
				xifrat);
		
		DesxifrarRSA desxifrarRSA = new DesxifrarRSA(clausPerDesxifrar);
		
		resultat = desxifrarRSA.getResultat();
		
		if (resultat.equals(missatge)) {
			System.out.println("Funciona");
		} else { //Impossible
			System.out.println("No Funciona");
		}
	}
}
