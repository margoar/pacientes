package datos;

import dominio.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteDaoJDBC {

    private static final String SQL_SELECT = "SELECT id_paciente,rut,nombre,apellido,edad,estadoCovid,fechaContagio FROM paciente";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM paciente";
    private static final String SQL_INSERT = "INSERT INTO paciente (rut,nombre,apellido,edad,estadoCovid,fechaContagio) " + 
                                            " VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "SELECT * FROM paciente";
    private static final String SQL_DELETE = "DELETE FROM paciente WHERE id_paciente = ?";

    public List<Paciente> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Paciente paciente = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            //iteramos los elementos del resultset
            while (rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                String rut = rs.getString("rut");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                boolean estadoCovid = rs.getBoolean("estadoCovid");
                Date fechaContagio = rs.getDate("fechaContagio");

                paciente = new Paciente(idPaciente, rut, nombre, apellido, edad, estadoCovid, fechaContagio);
                pacientes.add(paciente);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

        }
        return pacientes;
    }
    
    public int insertar(Paciente paciente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, paciente.getRut());
            stmt.setString(2, paciente.getNombre());
            stmt.setString(3, paciente.getApellido());
            stmt.setInt(4, paciente.getEdad());
            stmt.setBoolean(5, paciente.isEstadoCovid());
            stmt.setDate(6, (java.sql.Date) paciente.getFechaContagio());
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{}
        
        return rows;
    
    
    } 
}
