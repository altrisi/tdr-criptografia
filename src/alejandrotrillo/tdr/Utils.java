package alejandrotrillo.tdr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import alejandrotrillo.tdr.excepcions.UsuariCancelaException;

/**
 * Funcions est�tiques utilitzades a la majoria 
 * de les classes del projecte
 * 
 * @author Alejandro Trillo
 *
 */
public class Utils {
	private static BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	/**
	 * El random global utilitzat a tot el projecte
	 */
	public static SecureRandom random = new SecureRandom();
	
	private static List<String> canceladors = Arrays.asList("cancel","cancelar","cancel�lar","cancela","c");
	
	/**
	 * Llegeix un text desde la consola de la aplicaci�
	 * @param missatge Text per escriure davant de la petici�
	 * @return El missatge escrit
	 */
	public static String llegir(String missatge) {
		String linia = null;
		try {
			System.out.print(missatge+" ");
			linia = consola.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
			// Sortim: 
			// Si no es pot llegir l'entrada, el
			// programa no t� cap sentit
			System.exit(1);
		}
		if (canceladors.contains(linia))
			throw new UsuariCancelaException();
		return linia;
	}
	
	/**
	 * Llegeix un valor de S� o No. Repetir� fins trobar un valor v�lid
	 * @see #llegir(String)
	 * @param missatge Missatge a escriure (s'afegeix "(S/N)")
	 * @return El {@link boolean} obtingut
	 */
	public static boolean llegirBool(String missatge) {
		String lectura = llegir(missatge+" (S/N):");
		if (lectura.equalsIgnoreCase("S") || 
			lectura.equalsIgnoreCase("true") || 
			lectura.equalsIgnoreCase("Si")) {
			return true;
		} else if (lectura.equalsIgnoreCase("N") || 
					lectura.equalsIgnoreCase("false") || 
					lectura.equalsIgnoreCase("No")) {
			return false;
		} else {
			println("Valor inv�lid");
			return llegirBool(missatge);
		}
	}

	/**
	 * Llegeix un valor num�ric ({@link int}). Repetir� fins trobar un valor v�lid
	 * @see #llegir(String)
	 * @see #llegirBigInt(String)
	 * @param missatge Missatge a enviar
	 * @return El {@link int} obtingut
	 */
	public static int llegirInt(String missatge) {
		String lectura = llegir(missatge);
		try {
			return Integer.parseInt(lectura);
		} catch (NumberFormatException e) {
			println("Introdueix un enter v�lid");
			return llegirInt(missatge);
		}
	}
	
	/**
	 * Llegeix un valor num�ric ({@link BigInteger}). Repetir� fins trobar un valor v�lid
	 * @see #llegir(String)
	 * @see #llegirBigInt(String)
	 * @param missatge Missatge a enviar
	 * @return El {@link BigInteger} obtingut
	 */
	public static BigInteger llegirBigInt(String missatge) {
		String lectura = llegir(missatge);
		try {
			return new BigInteger(lectura);
		} catch (NumberFormatException e) {
			println("Introdueix un enter v�lid");
			return llegirBigInt(missatge);
		}
	}
	
	/**
	 * Imprimeix una l�nia de text a la consola 
	 * @param que La l�nia de text
	 */
	public static void println(String que) {
		System.out.println(que);
	}
	
	/**
	 * Genera un nombre probablement primer 
	 * donat un nombre de bits.
	 * Estableix un m�nim de 2 bits.
	 * @param bits Quants bits tindr� el primer
	 * @return El primer generat
	 */
	public static BigInteger generarPrimer(int bits) {
		return BigInteger.probablePrime(Math.max(2,bits), random);
	}
	
	/**
	 * Pausa el programa amb una l�nia de
	 * "Prem enter per continuar"
	 */
	public static void pausa() {
		llegir("Prem enter per continuar");
	}
	
	/**
	 * Converteix una cadena de text {@link String} a un 
	 * {@link BigInteger}.
	 * 
	 * Per fer aix�, converteix cada car�cter en la seva
	 * representaci� decimal (3 xifres) i afegeix zeros
	 * davant si el nombre �s menor, per mantenir-se consistent.
	 * Repeteix aquest proc�s per cada car�cter i despr�s
	 * passa el nombre resultant (com a {@link String} a un
	 * {@link BigInteger}.
	 * 
	 * @see #bigIntAString(BigInteger)
	 * 
	 * @param missatge El missatge a convertir
	 * @return El missatge com a {@link BigInteger}
	 */
	public static BigInteger stringABigInt(String missatge) {
		int llarg = missatge.length();
		if (llarg == 0) {
			return BigInteger.valueOf(32); // Un espai
		}
		StringBuilder stringNumero = new StringBuilder(missatge.length()*3);
		int punt = 0;
		while (punt < llarg) {
			int nombre = (int) missatge.charAt(punt);
			if (nombre < 100) stringNumero.append('0');
			if (nombre < 10) stringNumero.append('0');
			stringNumero.append(nombre);
			punt++;
		}
		return new BigInteger(stringNumero.toString());
	}
	
	/**
	 * Funci� que converteix un {@link BigInteger} a
	 * una {@link String}.
	 * Fa el proc�s contrari a {@link #stringABigInt(String)}
	 * Primer afegeix zeros al inici en cas de que no estiguessin
	 * presents, ja que al haver-se convertit a {@link BigInteger}
	 * els zeros inicials no es conserven.
	 * 
	 * @param missatge El missatge com a {@link BigInteger}
	 * @return El missatge com a {@link String}
	 */
	public static String bigIntAString(BigInteger missatge) {
		String missatgeString = missatge.toString();
		while (!(missatgeString.length() % 3 == 0))
			missatgeString = "0" + missatgeString;
		int llarg = missatgeString.length();
		StringBuilder stringFinal = new StringBuilder(llarg/3);
		int punt = 0;
		while (punt < llarg) {
			String submissatge = missatgeString.substring(punt,punt+3);
			char lletra = (char) Integer.parseInt(submissatge);
			stringFinal = stringFinal.append(lletra);
			punt += 3;
		}
		return stringFinal.toString();
	}

}
