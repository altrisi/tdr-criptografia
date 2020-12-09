package alejandrotrillo.tdr.excepcions;

import alejandrotrillo.tdr.Utils;

/**
 * Es llen�a quan l'usuari escriu una de les paraules claus
 * per cancel�lar a la terminal.
 * @author altrisi
 */
@SuppressWarnings("serial")
public class UsuariCancelaException extends RuntimeException {
	
	public UsuariCancelaException() {
		super("L'usuari ha cancel�lat la operaci�");
		Utils.println("Operaci� cancel�lada");
	}

}
