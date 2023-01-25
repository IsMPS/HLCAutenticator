package hlc.ud04.appsec.seguridad.autenticacion;

import java.util.Iterator;

/**
 * Permite iterar (recorrer) todos los usuarios
 * @author mmontoro
 *
 */
public class IteradorUsuarios {
  
  // Clientes
  Usuarios usuarios;
  // Iterador de sistema sobre las claves
  Iterator<Long> iterador;
  
  /**
   * Constructor
   * @param usuarios Usuarios sobre los que se va a iterar
   */
  public IteradorUsuarios(Usuarios usuarios) {
    // Almacena el sistema de clientes
    this.usuarios = usuarios;
    // Crea el iterador sobre las claves del mapa
    this.iterador = usuarios.usuarios.keySet().iterator();
  }
  
  /**
   * Comprueba si hay más elementos a iterar
   * @return true si hay más elementos o false en caso contrario
   */
  public boolean hasNext() {
    return iterador.hasNext();
  }
  
  /**
   * Devuelve el siguiente usuario en el iterador y avanza una posición
   * @return Siguiente usuario
   */
  public Usuario next() {
    // Obtenemos la siguiente clave
    Long clave = iterador.next();
    // Y a partir de ella, el cliente (accedemos directamente al atributo)
    return usuarios.usuarios.get(clave);
  }

}
