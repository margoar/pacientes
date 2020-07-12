package web;

import datos.PacienteDaoJDBC;
import dominio.Paciente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws  ServletException,IOException{
   
        //recuperamos listado de pacintes
        String accion = request.getParameter("accion");
        
        if(accion != null){
            switch(accion){
                case "editar" :
                    break;
                default:
                    this.accionDefault(request, response);
            }
        }else{
        
         this.accionDefault(request, response);
        }
        
            //url no cambia 
        //    request.getRequestDispatcher("pacientes.jsp").forward(request, response);
    
    }
    
    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
        List<Paciente> pacientes = new PacienteDaoJDBC().listar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("pacientes", pacientes);
        response.sendRedirect("pacientes.jsp");
    
    
    }
}
