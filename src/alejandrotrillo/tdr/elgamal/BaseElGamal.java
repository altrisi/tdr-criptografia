package alejandrotrillo.tdr.elgamal;

import java.math.BigInteger;

import alejandrotrillo.tdr.Utils;

public abstract class BaseElGamal {
	public boolean demanarPrivada;
	protected ClausElGamal claus;
	
	public ClausElGamal getClaus() {
		return claus;
	}
	
	protected void obtenirClaus() {
		boolean utilitzarUltimes;
		if (ElGamalManager.ultimesClaus != null) {
			utilitzarUltimes = Utils.llegirBool("Utilitzar últimes claus generades?");
		} else {
			utilitzarUltimes = false;
			Utils.println("No hi ha claus recents, introdueix unes pròpies");
		}
		
		if (utilitzarUltimes) {
			claus = ElGamalManager.ultimesClaus;
		} else {
			BigInteger p = Utils.llegirBigInt("Introduir un primer p:");
			BigInteger g = Utils.llegirBigInt("Introduir un nre menor que p (g):");
			BigInteger y = Utils.llegirBigInt("Introduir una clau pública (y):");
			BigInteger x = null;
			if (demanarPrivada) x = Utils.llegirBigInt("Introduir una clau privada (x):");
			claus = new ClausElGamal(new ClausElGamal.ClauPublica(p, g, y), x);
		}
	}
}
