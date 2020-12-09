package alejandrotrillo.tdr.rsa;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;

public abstract class BaseRSA {
	protected ClausRSA claus;
	
	public ClausRSA getClaus() {
		return claus;
	}
	
	protected void obtenirClaus() {
		boolean utilitzarUltimes;
		if (RSAManager.ultimesClaus != null) {
			utilitzarUltimes = Utils.llegirBool("Utilitzar �ltimes claus generades?");
		} else {
			utilitzarUltimes = false;
			Utils.println("No hi ha claus recents, introdueix unes pr�pies");
		}
		
		if (utilitzarUltimes) {
			claus = RSAManager.ultimesClaus;
		} else {
			BigInteger modul = Utils.llegirBigInt("Introdueix un m�dul:");
			BigInteger clauPrivada = Utils.llegirBigInt("Introduir clau privada (d):");
			BigInteger clauPublica = Utils.llegirBigInt("Introduir clau p�blica (exponent):");
			claus = new ClausRSA(modul, clauPublica, clauPrivada);
		}
	}
}
