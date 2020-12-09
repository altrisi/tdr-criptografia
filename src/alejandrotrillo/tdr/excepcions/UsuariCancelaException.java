package alejandrotrillo.tdr.excepcions;

import alejandrotrillo.tdr.Utils;

/**
 * Es llença quan l'usuari escriu una de les paraules claus
 * per cancel·lar a la terminal.
 * @author altrisi
 */
@SuppressWarnings("serial")
public class UsuariCancelaException extends RuntimeException {
	
	public UsuariCancelaException() {
		super("L'usuari ha cancel·lat la operació");
		Utils.println("Operació cancel·lada");
	}

}
