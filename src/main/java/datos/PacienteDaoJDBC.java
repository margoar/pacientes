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
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM paciente where id_paciente = ?";
    private static final String SQL_SELECT_BY_RUT = "SELECT * FROM paciente WHERE rut = ?";
    private static final String SQL_INSERT = "INSERT INTO paciente (rut,nombre,apellido,edad,estadoCovid,fechaContagio) "
            + " VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE paciente "
            + " SET  nombre = ?, apellido = ?,edad= ?, estadoCovid= ?,fechaContagio=? WHERE id_paciente = ?";
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

    public int insertar(Paciente paciente) {
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
            java.util.Date utilDate = paciente.getFechaContagio();
            stmt.setDate(6, paciente.isEstadoCovid() ? new java.sql.Date(utilDate.getTime()) : null);
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;

    }

    public int eliminar(Paciente paciente) {

        Connection conn = null;
        PreparedStatement stmt = null;

        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, paciente.getIdPaciente());
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public boolean buscarPacientePorRut(String rut) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean esEncontrado = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_RUT, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
            stmt.setString(1, rut);
            rs = stmt.executeQuery();
            rs.absolute(1);

            if (rs.absolute(1)) {

                esEncontrado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return esEncontrado;

    }

    public Paciente encontrar(Paciente paciente) {

        //connecion
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
            stmt.setInt(1, paciente.getIdPaciente());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos enel primer registro

            String rut = rs.getString("rut");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            boolean estadoCovid = rs.getBoolean("estadoCovid");
            Date fecha = estadoCovid ? rs.getDate("fechaContagio") : null;

            paciente.setRut(rut);
            paciente.setNombre(nombre);
            paciente.setApellido(apellido);
            paciente.setEdad(edad);
            paciente.setEstadoCovid(estadoCovid);
            paciente.setFechaContagio(fecha);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);

        }

        return paciente;

    }
    
    
    public int actualizar(Paciente paciente) {
        //connecion
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setInt(3, paciente.getEdad());
            stmt.setBoolean(4, paciente.isEstadoCovid());
            java.util.Date utilDate = paciente.getFechaContagio();
            stmt.setDate(5, paciente.getFechaContagio() != null ? new java.sql.Date(utilDate.getTime()): null);
            stmt.setInt(6, paciente.getIdPaciente());
            rows = stmt.executeUpdate();
          
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);

        }

        return rows;
    }
    
}
