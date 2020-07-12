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

    private static final String SQL_SELECT = "SELECT * FROM control_pacientes";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM control_pacientes";
    private static final String SQL_INSERT = "SELECT * FROM control_pacientes";
    private static final String SQL_UPDATE = "SELECT * FROM control_pacientes";
    private static final String SQL_DELETE = "DELETE FROM control_pacientes WHERE id_paciente = ?";

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
}
