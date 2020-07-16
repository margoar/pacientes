package web;

import datos.PacienteDaoJDBC;
import dominio.Paciente;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //recuperamos listado de pacintes
        String accion = request.getParameter("accion");

//hay que ver si funciona xd    if (session != null) {
        String userName = (String) session.getAttribute("name");
        session.setAttribute("userName", userName);

        if (accion != null) {
            switch (accion) {
                case "editar":
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {

            this.accionDefault(request, response);
        }

        // } else {
        //   response.sendRedirect("login.jsp");
        // session.invalidate();
        //}
        //url no cambia 
        //    request.getRequestDispatcher("pacientes.jsp").forward(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Paciente> pacientes = new PacienteDaoJDBC().listar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("pacientes", pacientes);
        //pacientes sanos

        int cantSanos = cantPacientesSano(pacientes);
        sesion.setAttribute("cantPacientes", pacientes.size());
        sesion.setAttribute("cantSanos", cantSanos);
        sesion.setAttribute("cantContagiados", (int) (pacientes.size() - cantSanos));
        response.sendRedirect("pacientes.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {

            switch (accion) {
                case "agregar":
                    this.agregarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);

            }

        } else {

            this.accionDefault(request, response);
        }

    }

    private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //recuperar datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String rut = request.getParameter("rut");
        int edad = Integer.parseInt(request.getParameter("edad"));
        boolean estadoCovid = Boolean.parseBoolean(request.getParameter("estadoCovid"));
        Date fechaContagio = null;
        if(estadoCovid){
          String formatFecha = request.getParameter("fechaContagio");

        try {
            fechaContagio = new SimpleDateFormat("dd/MM/yyyy").parse(formatFecha);
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }        
        }
        

        Paciente paciente = new Paciente(rut, nombre, apellido, edad, estadoCovid, fechaContagio);
        int registroAgregado = new PacienteDaoJDBC().insertar(paciente);

        System.out.println("registro insertado:" + registroAgregado);
        this.accionDefault(request, response);
    }

    private int cantPacientesSano(List<Paciente> pacientes) {
        int cantSanos = 0;
        inicio:
        for (Paciente pac : pacientes) {
            if (!pac.isEstadoCovid()) {
                cantSanos++;
                continue inicio;

            }
            System.out.println("pacientes sanos: " + cantSanos);
        }

        return cantSanos;
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));

        Paciente paciente = new Paciente(idPaciente);

        int registroEliminado = new PacienteDaoJDBC().eliminar(paciente);
        System.out.println("registros eliminados" + registroEliminado);
        this.accionDefault(request, response);

    }
}
