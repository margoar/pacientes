package web;


import datos.UsuarioDaoJDBC;
import dominio.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String accion = request.getParameter("accion");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        
        String user = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(user);
        usuario.setContrasena(contrasenia);
        

        boolean validaUsuario = new UsuarioDaoJDBC().validaUsuario(usuario);

        if (validaUsuario) {
           HttpSession session=request.getSession();  
           session.setAttribute("name", user);  
           response.sendRedirect("ServletControlador");
           
        } else {
            HttpSession session = request.getSession();
            String mensaje = "Usuario y/o contraseña invalido.";
            session.setAttribute("mensaje", mensaje);
            response.sendRedirect("login.jsp");
        }

    }

}
