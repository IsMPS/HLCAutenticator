package hlc.ud04.appsec.seguridad.autenticacion;

import hlc.ud04.appsec.persistencia.GestorPersistenciaBBDD;

public class RespuestaDesafioImpl implements RespuestaDesafio {
	private String contra;
	private GestorPersistenciaBBDD gestor;
	private static Usuario usuario;
	
	public RespuestaDesafioImpl(String i) {
		this.gestor = new GestorPersistenciaBBDD("db/base_original.db");
		this.contra = i;
		this.usuario = gestor.obtenerPorClave(contra);
	}
	
	public String getContra() {
		return contra;
	}
	
	public static long getId() {
		if(usuario!=null) {
			return usuario.getUid();
		}
		return 0L;
		
	}

}
