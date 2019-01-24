/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal_ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author xp
 */
public class GestorConexion {

    /**
     * @param args the command line arguments
     */
    Connection conect;
    String cadena_resultado ="";
    
    public GestorConexion() {
        conect = null;
        
        try{
            String url1 = "jdbc:mysql://localhost:3306/discografica?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "root";
            conect = DriverManager.getConnection(url1, user, password);

            if (conect != null) {
                System.out.println("Conectado a base de datos");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
        }
      }
         public void annadirColumna(String columna) {
        try {
            conect.setAutoCommit(false);

            Statement sta = conect.createStatement();

            sta.executeUpdate("ALTER TABLE album ADD " + columna + " VARCHAR(30)");

            sta.close();

            conect.commit();

            System.out.println("columna añadida ");
        } catch (Exception e) {
            System.out.println("ERROR");
            try {
                if (conect != null) {
                    conect.rollback();
                }
            } catch (Exception se2) {
                se2.printStackTrace();
            }
            e.printStackTrace();
        }
    }
         public void cerrar_conexion() {
        try {
            conect.close();
            System.out.println("Conexion cerrada");

        } catch (SQLException ex) {
            System.err.println("Error al cerrar conexion");
            ex.printStackTrace();
        }
    }
         public String consultaStatement() {
        String fallo = "error";

        try {
            conect.setAutoCommit(false);

            Statement sta = conect.createStatement();
            String query = "SELECT * FROM album ";
            ResultSet rs = sta.executeQuery(query);
            ResultSetMetaData metaDatos = rs.getMetaData();

            int numColumnas = metaDatos.getColumnCount();

            return cadena_resultado;

        } catch (Exception e) {
            System.out.println("Error");
            try {
                if (conect != null) {
                    conect.rollback();
                }
            } catch (Exception se2) {
                se2.printStackTrace();
            }
            e.printStackTrace();
            return fallo;
        }
    }

    public void insertarAlbumNuevo(String id_Album, String titulo_Album, String publicacion, String artistaa) {
        try {
            conect.setAutoCommit(false);

            Statement sta = conect.createStatement();

            sta.executeUpdate("INSERT INTO album VALUES('" + id_Album + "', '" + titulo_Album + "', '" + publicacion + "', '" + artistaa + "')");

            System.out.println("insertado album correctamente");

            sta.close();

            conect.commit();
        } catch (Exception e) {
            System.out.println("Error");

            try {
                if (conect != null) {
                    conect.rollback();
                }
            } catch (Exception se2) {
                se2.printStackTrace();
                cadena_resultado = se2.toString();
            }
            e.printStackTrace();
            cadena_resultado = e.toString();
        }
    }

    public void cancionNueva(String id, String nombre, String duracion, String letra, String id_album) {
        try {
            conect.setAutoCommit(false);

            Statement sta = conect.createStatement();

            sta.executeUpdate("INSERT INTO cancion VALUES('" + id + "', '" + nombre + "', '" + duracion + "', '" + letra + "', '" + id_album + "')");

            System.out.println("insertado cancion correctamente");

            sta.close();

            conect.commit();
        } catch (Exception e) {
            System.out.println("Error");

            try {
                if (conect != null) {
                    conect.rollback();
                }
            } catch (Exception se2) {
                se2.printStackTrace();
                cadena_resultado = se2.toString();
            }

            e.printStackTrace();
            cadena_resultado = e.toString();
        }
    }
}
    
    

