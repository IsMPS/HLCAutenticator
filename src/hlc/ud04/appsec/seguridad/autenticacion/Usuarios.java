package hlc.ud04.appsec.seguridad.autenticacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hlc.ud04.appsec.persistencia.GestorPersistenciaBBDD;

/**
 * Conjunto de los clientes
 * @author mmontoro
 *
 */
public class Usuarios {

  // Datos de los clientes, indexados por número de cuenta
  // Es protegido de paquete porque debe poder accederse por IteradorClientes
  Map<Long, Usuario> usuarios;
  // Gestor de persistencia. Controla el almacenamiento de los datos de clientes
  private GestorPersistenciaBBDD gestorPersistencia;
  
  /**
   * Constructor
   * @param gestorPersistencia Gestor de persistencia que gestiona el
   * almacenamiento permanente de los datos de usuario
   */
  public Usuarios(GestorPersistenciaBBDD gestorPersistencia) {
    // Almacenamos el gestor de persistencia
    this.gestorPersistencia = gestorPersistencia;
    // Se inicia el mapa
    this.usuarios = new HashMap<>();
    // Se leen todos los clientes desde almacenamiento
    leeUsuarios();
  }
  

  public Usuario buscarPorID(long i) {
    return usuarios.get(i);
  }
  
  public Usuario buscarPorNombre(String nombre) {
	  long i = gestorPersistencia.obtenerPorNombre(nombre).getUid();
	    return usuarios.get(i);
	  }
  
  public IteradorUsuarios iterador() {
    return new IteradorUsuarios(this);
  }
  
  /**
   * Lee los clientes desde almacenamiento
   */
  private void leeUsuarios() {
    // Limpia el mapa y lo deja vacio
    usuarios.clear();
    // Obtiene una lista con todos los clientes
    List<Usuario> listaUsuarios = gestorPersistencia.obtenerTodos();
    // Recorre la lista e inserta todos los clientes en el mapa
    for(Usuario usuario: listaUsuarios) {
      usuarios.put(usuario.getUid(), usuario);
    }
  }
  
}
