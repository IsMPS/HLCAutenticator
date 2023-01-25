package hlc.ud04.appsec.seguridad.autenticacion;

import hlc.ud04.appsec.persistencia.GestorPersistenciaBBDD;

public class DesafioImpl implements Desafio {

	private String nombre;
	private static Usuario usuario;
	private GestorPersistenciaBBDD gestor;
	
	

	public DesafioImpl(String nombre) {
		this.nombre = nombre;
		this.gestor = new GestorPersistenciaBBDD("db/base_original.db");
		this.usuario = gestor.obtenerPorNombre(nombre);
	}
	
	public static long idUsuario() {
		if(usuario!=null) {
			return usuario.getUid();
		}
		return 0L;
		
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
