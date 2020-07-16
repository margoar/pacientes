
package datos;

import dominio.Usuario;
import java.sql.*;
public class UsuarioDaoJDBC {

    private static final String SQL_SELECT = "select * from usuario where username = ? and pass = ? ";
    
    //necesitamos saber si el usuario existe
    public boolean validaUsuario(Usuario usuario){
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existeUsuario = false;
     
        try {
        conn = Conexion.getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        stmt.setString(1, usuario.getNombreUsuario());
        stmt.setString(2, usuario.getContrasena());
        
        rs = stmt.executeQuery();
        existeUsuario = rs.next();
         
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }        
        return existeUsuario;
    }    
}
