package hlc.ud04.appsec.seguridad.autenticacion;

public class AutenticadorImpl implements Autenticador {

	@Override
	public Desafio iniciaAutenticacion(String identificador) {
		return new DesafioImpl(identificador);
	}


	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		DesafioImpl a = (DesafioImpl) desafio;
		RespuestaDesafioImpl b = (RespuestaDesafioImpl) respuesta;
		if (a.idUsuario()==b.getId()) {
		      return new Usuario(b.getId());
		    } else {
		      return null;
		    }
	}

}
