package hlc.ud04.appsec.seguridad.core;

import java.util.Scanner;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.DesafioImpl;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafioImpl;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;

public class SistemaSeguridadImpl implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso controlAcceso;

	public SistemaSeguridadImpl(Autenticador autenticador, ControlAcceso controlAcceso) {
		this.autenticador = autenticador;
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {

		Scanner sc = new Scanner(System.in);

		// Solicita el nombre de usuario
		System.out.print("Introduzca el usuario: ");
		String usuario = sc.nextLine();

		Desafio desafio = (DesafioImpl) autenticador.iniciaAutenticacion(usuario);
		System.out.print("Contraseña: ");
		String respuesta = sc.nextLine();
		// Termina la autenticacion
		return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioImpl(respuesta));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		return controlAcceso.estaPermitido(usuario, operacion, recurso);
	}

}

