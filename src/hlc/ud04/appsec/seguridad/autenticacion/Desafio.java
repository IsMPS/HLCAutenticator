package hlc.ud04.appsec.seguridad.autenticacion;

import hlc.ud04.appsec.persistencia.GestorPersistenciaBBDD;

/**
 * Desafío que lanza un sistema de autenticación al usuario. Contiene información
 * que, combinada con lo que conoce el usuario, proporcionará una respuesta que le
 * proporcione (o no) accesoº
 * @author mmontoro
 *
 */
public interface Desafio {
	
	public static long idUsuario() {
		return 0;
	}

	public static String getNombre() {
		return null;
	}

	public static long getId() {
		return 0;
	}

}
