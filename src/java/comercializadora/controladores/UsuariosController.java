/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.IUsuarioDao;
import comercializadora.dao.UsuarioJDBCDao;
import comercializadora.excepciones.ErrorLoginException;
import comercializadora.excepciones.ErrorRegistroException;
import comercializadora.modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author FRANCISCO
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/usuarios"})
public class UsuariosController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            
            switch (accion) {
                
                case "cerrarSesion":
                    cerrarSesion(request, response);
                    break;
            }
            
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            
            switch (accion) {
                case "crearUsuario":
                    crearUsuario(request, response);
                    break;
                case "validarUsuario":
                    validarUsuario(request, response);
                    break;
                
            }
            
        }
        
    }
    
    private void crearUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password2");
        
        if (pass.equals(pass2)) {
            String passEncriptado = encriptarPassword(pass);
            IUsuarioDao usuarioDao = new UsuarioJDBCDao();
            Usuario usuario = new Usuario();
            usuario.setUsuario(user);
            usuario.setPassword(passEncriptado);
            
            boolean validarEmail = usuario.validarEmail();
            if (!validarEmail) {
                throw new ServletException(new ErrorRegistroException("Proporcione una direccion de email válida"));
            }
            Usuario usuarioSesion;
            usuarioSesion = usuarioDao.crearUsuario(usuario);
            request.getSession().setAttribute("user", usuarioSesion);
            response.sendRedirect(request.getContextPath() + "/productos");
            
        } else {
            throw new ServletException(new ErrorRegistroException("los password proporcionados no coinciden"));
        }
    }
    
    private void validarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String passwordEncriptado= encriptarPassword(pass);
        
        Usuario usuarioAux = new Usuario();
        usuarioAux.setUsuario(user);
        usuarioAux.setPassword(passwordEncriptado);
        boolean validarDireccionEmail = usuarioAux.validarEmail();
        
        if (!validarDireccionEmail) {
            throw new ServletException(new ErrorLoginException("Proporcione un email valido"));
        }
        
        IUsuarioDao usuarioDao = new UsuarioJDBCDao();
        Usuario usuarioSesion = usuarioDao.validarUsuario(user, passwordEncriptado);
        
        if (usuarioSesion != null) {
            request.getSession().setAttribute("user", usuarioSesion);
            
            response.sendRedirect("/sistemacomercializadora/productos");
            
        } else {
            throw new ServletException(new ErrorLoginException("No existe ningun usuario con esas credenciales"));
        }
    }
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private String encriptarPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
    
}
