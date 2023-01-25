package hlc.ud04.appsec.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import hlc.ud04.appsec.core.Cliente;
import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.core.GestorPersistenciaException;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

/**
 * Gestor de persistencia que emplea una base de datos SQLite
 * @author mmontoro
 *
 */
public class GestorPersistenciaBBDD {
  
  // Prefijo para la conexión a la base de datos (falta al final el nombre del archivo)
  private static final String JDBC_PREFIX = "jdbc:sqlite:";
  
  // Nombre del archivo de base de datos
  private String database;
  
  /**
   * Constructor
   * @param database Ruta al archivo de base de datos
   */
  public GestorPersistenciaBBDD(String database) {
    this.database = database;
  }



  
  public Usuario obtenerPorNumero(String nombre) {
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = getConnection();
      Statement statement = conn.createStatement();
      rs = statement.executeQuery("SELECT id FROM User WHERE nombre = "
          + statement.enquoteLiteral(nombre) + ";");
      Usuario usuario = null;
      if (rs.next()) {
        usuario = new Usuario(rs.getLong("id"));
      }
      return usuario;
    } catch (SQLException e) {
      throw new GestorPersistenciaException(e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {}
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {}
      }
    }
  }

  public List<Usuario> obtenerTodos() {
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = getConnection();
      Statement statement = conn.createStatement();
      rs = statement.executeQuery("SELECT id FROM cliente;");
      List<Usuario> usuarios = new ArrayList<>();
      while (rs.next()) {
        usuarios.add(new Usuario(rs.getInt("id")));
      }
      return usuarios;
    } catch (SQLException e) {
      throw new GestorPersistenciaException(e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {}
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {}
      }
    }
  }
  
  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_PREFIX + database);
  }

}
