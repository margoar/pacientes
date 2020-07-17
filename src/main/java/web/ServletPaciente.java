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

@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //recuperamos listado de pacintes
        String accion = request.getParameter("accion");

        if (session != null) {
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

        } else {
            response.sendRedirect("login.jsp");
            session.invalidate();
        }
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
        HttpSession sesion = request.getSession();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String rut = request.getParameter("rut");
        rut = rut.replaceAll("\\.", "");
        rut = rut.replaceAll("-", "");
                
        int edad = Integer.parseInt(request.getParameter("edad"));
        boolean estadoCovid = Boolean.parseBoolean(request.getParameter("estadoCovid"));
        Date fechaContagio = null;
        if (estadoCovid) {
            String formatFecha = request.getParameter("fechaContagio");

            try {
                fechaContagio = new SimpleDateFormat("dd/MM/yyyy").parse(formatFecha);
            } catch (ParseException ex) {
                ex.printStackTrace(System.out);
            }
        }

        if (validaPaciente(rut, nombre, apellido, edad, request)) {

            Paciente paciente = new Paciente(rut, nombre, apellido, edad, estadoCovid, fechaContagio != null ? fechaContagio : null);
            int registroAgregado = new PacienteDaoJDBC().insertar(paciente);

            System.out.println("registro insertado:" + registroAgregado);
            this.limpiarCampos(request, response);
            this.accionDefault(request, response);

        } else {

            request.getRequestDispatcher("pacientes.jsp").forward(request, response);
        }

    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        Paciente paciente = new Paciente(idPaciente);

        int registroEliminado = new PacienteDaoJDBC().eliminar(paciente);
        System.out.println("registros eliminados" + registroEliminado);
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
        }
        System.out.println("pacientes sanos: " + cantSanos);
        return cantSanos;
    }

    public boolean validaPaciente(String rut, String nombre, String apellido, int edad, HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        boolean validacion = false;
        String validaciones = "";
        if (validarRut(rut)) {
            boolean existe = new PacienteDaoJDBC().buscarPacientePorRut(rut);
            if (existe) {
                validaciones = "El rut ingresado ya se encuentra registrado.";
                sesion.setAttribute("rut", rut);
                sesion.setAttribute("nombre", nombre);
                sesion.setAttribute("apellido", apellido);
                sesion.setAttribute("edad", edad);
                // sesion.setAttribute("fecha", fechaContagio);
                sesion.setAttribute("validaciones", validaciones);
                sesion.setAttribute("estado", false);
                validacion = false;

            } else {
                sesion.setAttribute("estado", "");
                validacion = true;
            }

        } else {

            validaciones = "El rut ingresado no es valido.";
            sesion.setAttribute("rut", rut);
            sesion.setAttribute("nombre", nombre);
            sesion.setAttribute("apellido", apellido);
            sesion.setAttribute("edad", edad);
            // sesion.setAttribute("fecha", fechaContagio);
            sesion.setAttribute("validaciones", validaciones);
            sesion.setAttribute("estado", false);
            validacion = false;

        }

        return validacion;
    }

    public static boolean validarRut(String rut) {

        boolean esRutValido = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                esRutValido = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return esRutValido;
    }

    private void limpiarCampos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        sesion.setAttribute("rut", "");
        sesion.setAttribute("nombre", "");
        sesion.setAttribute("apellido", "");
        sesion.setAttribute("edad", "");
        sesion.setAttribute("fecha", "");
        sesion.setAttribute("validaciones", null);

    }

}
