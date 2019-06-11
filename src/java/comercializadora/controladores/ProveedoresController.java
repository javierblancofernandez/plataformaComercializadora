/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.CategoriaJDBCDao;
import comercializadora.dao.ProveedorJDBCDAO;
import comercializadora.modelos.Categoria;
import comercializadora.modelos.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FRANCISCO
 */
@WebServlet(name = "ProveedoresController", urlPatterns = {"/proveedores"})
public class ProveedoresController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "nuevo":
                    formNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;
            }
        } else {

            ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
            List<Proveedor> listaProveedor = daoProveedor.listAll();

            request.setAttribute("proveedores", listaProveedor);
            request.getRequestDispatcher("/WEB-INF/proveedores/index.jsp").
                    forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch (accion) {
                case "crear":
                    insertarProveedor(request, response);
                    break;
                case "borrar":
                    borrarProveedor(request, response);
                    break;
                case "actualizar":
                    actualizarProveedor(request, response);
                    break;

            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/proveedores/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long idProv = Long.parseLong(request.getParameter("idProv"));
        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
        Proveedor prov = daoProveedor.findById(idProv);
        if (prov != null) {
            request.setAttribute("proveedor", prov);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/proveedores/formulario.jsp").forward(request, response);

        }
    }

    private void insertarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        long idProv = Long.parseLong(request.getParameter("idProv"));
        String nombreProveedor = request.getParameter("nombProv");
        String contactoProveedor = request.getParameter("contProv");
        String celularProveedor = request.getParameter("celProv");
        String fijoProveedor = request.getParameter("fijoProv");

        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();

        String mensaje = daoProveedor.insert(new Proveedor(idProv, nombreProveedor, contactoProveedor, celularProveedor, fijoProveedor));
        request.getSession().setAttribute("opProv", mensaje);
        response.sendRedirect("/sistemacomercializadora/proveedores");

    }

    private void borrarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long idProv = Long.parseLong(request.getParameter("idProv"));

        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
        String mensaje = daoProveedor.delete(new Proveedor(idProv));

        request.getSession().setAttribute("operacionCategoria", mensaje);
        response.sendRedirect("/sistemacomercializadora/proveedores");
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
       
        long idProv = Long.parseLong(request.getParameter("idProv"));
        String nombreProveedor = request.getParameter("nombProv");
        String contactoProveedor = request.getParameter("contProv");
        String celularProveedor = request.getParameter("celProv");
        String fijoProveedor = request.getParameter("fijoProv");

        
        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
        
        String mensaje=daoProveedor.update(new Proveedor(idProv, nombreProveedor, contactoProveedor, celularProveedor, fijoProveedor));
        request.getSession().setAttribute("opProv", mensaje);
        response.sendRedirect("/sistemacomercializadora/proveedores");
    }

}





    

