package alejandrotrillo.tdr.elgamal;

import alejandrotrillo.tdr.Utils;
import alejandrotrillo.tdr.excepcions.UsuariCancelaException;

public class ElGamalManager {

	public static ClausElGamal ultimesClaus;
	
	public static void menuPrincipal() {
		Utils.println("");
		Utils.println("Xifrar ElGamal");
		Utils.println("1. Generar claus");
		Utils.println("2. Xifrar un missatge");
		Utils.println("3. Desxifrar un xifrat");
		Utils.println("4. Tornar al Men�");
		
		try {
			switch (Utils.llegirInt("Escull una opci�:")) {
				case 1:
					ultimesClaus = (new GenerarClausElGamal()).getClaus();
					break;
				case 2:
					ultimesClaus = (new XifrarElGamal()).getClaus();
					break;
				case 3:
					new DesxifrarElGamal();
					break;
				case 4:
					return;
				default:
					Utils.println("Opci� inv�lida");
			}
		} catch (UsuariCancelaException e) {
			// Si ha cancel�lat al men�, tornar al principal
			if (e.getStackTrace()[3].getMethodName() == "menuPrincipal")
				return;
		}
		menuPrincipal();
	}

}
