package alejandrotrillo.tdr.apitests;

import java.math.BigInteger;
import java.security.SecureRandom;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.elgamal.ClausElGamal;
import alejandrotrillo.tdr.elgamal.DesxifrarElGamal;
import alejandrotrillo.tdr.elgamal.GenerarClausElGamal;
import alejandrotrillo.tdr.elgamal.XifrarElGamal;

import static java.math.BigInteger.probablePrime;

public class ElGamalTest {
	SecureRandom random = new SecureRandom();
	final String missatge = "Això és una prova";
	ClausElGamal clausGenerades;
	ClausElGamal.Xifrat xifrat;
	String resultat;
	
	public static void main(String[] args) {
		new ElGamalTest().test();
	}
	
	public void test() {
		BigInteger p = probablePrime(512, random);
		GenerarClausElGamal clausElGamal = new GenerarClausElGamal(p, GenerarClausElGamal.randomBigInt(p), new BigInteger(512, random));
		clausGenerades = clausElGamal.getClaus();
		
		XifrarElGamal xifrarElGamal = new XifrarElGamal(clausGenerades, new BigInteger(512, random), Utils.stringABigInt(missatge));
		ClausElGamal clausAmbXifrat = xifrarElGamal.getClaus();
		
		xifrat = clausAmbXifrat.getXifrat();
		
		ClausElGamal clausPerDesxifrar = new ClausElGamal(clausGenerades.getClauPublica(), clausGenerades.getClauPrivada(), xifrat);
		
		DesxifrarElGamal desxifrarRSA = new DesxifrarElGamal(clausPerDesxifrar);
		
		resultat = desxifrarRSA.getResultat();
		
		if (resultat.equals(missatge)) {
			System.out.println("Funciona");
		} else { //Impossible
			System.out.println("No Funciona");
		}
		
	}
}
