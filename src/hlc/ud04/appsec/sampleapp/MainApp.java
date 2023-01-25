package hlc.ud04.appsec.sampleapp;

import hlc.ud04.appsec.core.Clientes;
import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.interfaz.Interfaz;
import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaBBDD;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.seguridad.autenticacion.AutenticadorImpl;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.autenticacion.Usuarios;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridadImpl;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridadNulo;

public class MainApp {

	// Ruta a la base de datos en disco
	private static final String DATABASE_PATH = "db/base_original.db";

	public static void main(String[] args) {
		GestorPersistenciaBBDD gestorLogin = new GestorPersistenciaBBDD(DATABASE_PATH);

		Usuarios usuarios = new Usuarios(gestorLogin);
		SistemaSeguridad sistemaSeguridadUsu = new SistemaSeguridadImpl(new AutenticadorImpl(),null);
		Usuario usuario = sistemaSeguridadUsu.autentica();
		if(usuario.getUid()>0) {
		// Creamos el gestor de persistencia SQLite
		GestorPersistencia gestor = new GestorPersistenciaSqlite(DATABASE_PATH);
		// Y lo inyectamos en el core
		Clientes clientes = new Clientes(gestor);
		// Creamos interfaz de usuario de tipo consola
		SistemaSeguridad sistemaSeguridad = new SistemaSeguridadNulo();
		Interfaz interfaz = new InterfazConsola(clientes, sistemaSeguridad);
		// Lanzamos la interfaz de usuario
		interfaz.run();
		} else {
			System.err.println("Error al conectar");
		}
	}

}
