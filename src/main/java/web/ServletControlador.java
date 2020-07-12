package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException{
   
            //url no cambia 
            request.getRequestDispatcher("pacientes.jsp").forward(request, response);
    
    }
}
