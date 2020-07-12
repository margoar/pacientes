package web;

import datos.PacienteDaoJDBC;
import dominio.Paciente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //recuperamos listado de pacintes
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {

            this.accionDefault(request, response);
        }

        //url no cambia 
        //    request.getRequestDispatcher("pacientes.jsp").forward(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Paciente> pacientes = new PacienteDaoJDBC().listar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("pacientes", pacientes);
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

        Paciente paciente = new Paciente(rut, nombre, apellido, edad, false, null);
        int registroAgregado = new PacienteDaoJDBC().insertar(paciente);

        System.out.println("registro insertado:" +registroAgregado);
        this.accionDefault(request, response);
    }
}
