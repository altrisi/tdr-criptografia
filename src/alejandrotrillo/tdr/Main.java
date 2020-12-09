package alejandrotrillo.tdr;

import static alejandrotrillo.tdr.Utils.*;

import alejandrotrillo.tdr.elgamal.ElGamalManager;
import alejandrotrillo.tdr.excepcions.UsuariCancelaException;
import alejandrotrillo.tdr.interficies.ClausImprimible;
import alejandrotrillo.tdr.rsa.RSAManager;

public class Main {

	public static void main(String[] args) {
		println("Projecte TDR Criptografia");
		println("Alejandro Trillo Sierra");
		println("IES Torre Del Palau");
		try {
			while (true) menuPrincipal();
		} catch (RuntimeException e) {
			// Evitar tancament instantani en l'ús a terminal, per entendre que passa
			println("S'ha produit un error no control·lat i el programa sortirà.");
			e.printStackTrace();
			pausa();
		}
	}

	private static void menuPrincipal() {
		println("");
		println("Menú Principal");
		println("1. Menú RSA");
		println("2. Menú ElGamal");
		println("3. Imprimir últimes Claus");
		println("4. Sortir");
		
		try {
			switch(llegirInt("Escull una opció:")) {
				case 1:
					RSAManager.menuPrincipal();
					break;
				case 2:
					ElGamalManager.menuPrincipal();
					break;
				case 3:
					imprimirUltimesClaus();
					break;
				case 4:
					if (llegirBool("Segur? Es perderan les Claus no apuntades"))
						System.exit(0);
				default:
					println("Opció invàlida");
			}
		} catch (UsuariCancelaException e) { }
	}
	
	private static void imprimirUltimesClaus() {
		ClausImprimible claus;
		switch (llegirInt("RSA (1) o ElGamal (2)?")) {
			case 1:
				claus = RSAManager.ultimesClaus;
				break;
			case 2:
				claus = ElGamalManager.ultimesClaus;
				break;
			default:
				imprimirUltimesClaus();
				return;
		}
		if (claus != null)
			claus.imprimirDades();
		else
			println("No hi ha Claus disponibles per aquesta sessió i algoritme");
		pausa();
	}
}
