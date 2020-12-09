package alejandrotrillo.tdr.rsa;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.excepcions.UsuariCancelaException;

public class RSAManager {

	public static ClausRSA ultimesClaus;
	
	public static void menuPrincipal() {
		Utils.println("");
		Utils.println("Xifrar RSA");
		Utils.println("1. Generar claus");
		Utils.println("2. Xifrar un missatge");
		Utils.println("3. Desxifrar un xifrat");
		Utils.println("4. Tornar al Menú");
		
		try {
			switch (Utils.llegirInt("Escull una opció:")) {
				case 1:
					ultimesClaus = (new GenerarClausRSA()).getClaus();
					break;
				case 2:
					ultimesClaus = (new XifrarRSA()).getClaus();
					break;
				case 3:
					new DesxifrarRSA();
					break;
				case 4:
					return;
				default:
					Utils.println("Opció invàlida");
			}
		} catch (UsuariCancelaException e) {
			// Si ha cancel·lat al menú, tornar al principal
			if (e.getStackTrace()[3].getMethodName() == "menuPrincipal" && e.getStackTrace()[2].getMethodName() == "llegirInt") {
				return;
			}
		}
		menuPrincipal();
	}

}
