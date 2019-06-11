/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.CategoriaJDBCDao;
import comercializadora.dao.ProductoJDBCDao;
import comercializadora.dao.ProveedorJDBCDAO;
import comercializadora.modelos.Categoria;
import comercializadora.modelos.Producto;
import comercializadora.modelos.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
@WebServlet(name = "ProductoController", urlPatterns = {"/productos"})
public class ProductoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoJDBCDao daoProducto = new ProductoJDBCDao();

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch (accion) {
                case "nuevo":
                    formNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;
            }
        } else {
            List<Producto> listaProductos = daoProducto.listAll();
            request.setAttribute("productos", listaProductos);
            request.getRequestDispatcher("/WEB-INF/productos/index.jsp").
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
                    insertarProducto(request, response);
                    break;
                case "borrar":
                    borrarProducto(request, response);
                    break;
                case "actualizar":
                    actualizarProducto(request, response);
                    break;

            }
        }
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoJDBCDao daoProducto = new ProductoJDBCDao();

        List<Categoria> categorias = new CategoriaJDBCDao().listAll();
        List<Proveedor> proveedores = new ProveedorJDBCDAO().listAll();
        List<Producto> productos = daoProducto.listAll();
        request.setAttribute("categorias", categorias);
        request.setAttribute("proveedores", proveedores);
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/productos/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProd = Long.parseLong(request.getParameter("idProd"));
        ProductoJDBCDao daoProducto = new ProductoJDBCDao();
        Producto producto = daoProducto.findById(idProd);
        List<Producto> listaProducto = daoProducto.listAll();

        if (producto != null) {
            List<Categoria> categorias = new CategoriaJDBCDao().listAll();
            List<Proveedor> proveedores = new ProveedorJDBCDAO().listAll();

            request.setAttribute("categorias", categorias);
            request.setAttribute("proveedores", proveedores);
                        
            request.setAttribute("tipoForm", "actualizar");
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/WEB-INF/productos/formulario.jsp").
                    forward(request, response);

        }
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long idProd = Long.parseLong(request.getParameter("idProd"));
        long provId = Long.parseLong(request.getParameter("idProve"));
        long categId = Long.parseLong(request.getParameter("idCateg"));
        String descripcion = request.getParameter("descrip");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));
        ProductoJDBCDao productoDAO = new ProductoJDBCDao();
        CategoriaJDBCDao categoriaDao = new CategoriaJDBCDao();
        Categoria categoria = categoriaDao.findByID(categId);
        Proveedor prov = new ProveedorJDBCDAO().findById(provId);
        String mensaje = productoDAO.insert(new Producto(idProd, prov, categoria, descripcion, precio, existencia));
        request.getSession().setAttribute("opProd", mensaje);
        response.sendRedirect("/sistemacomercializadora/productos");
    }

    private void borrarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long claveProd = Long.parseLong(request.getParameter("idProd"));

        ProductoJDBCDao productoDAO = new ProductoJDBCDao();
        String mensaje = productoDAO.delete(new Producto(claveProd));

        request.getSession().setAttribute("opProd", mensaje);

        response.sendRedirect("/sistemacomercializadora/productos");
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long idProd = Long.parseLong(request.getParameter("idProd"));
        long provId = Long.parseLong(request.getParameter("idProve"));
        long categId = Long.parseLong(request.getParameter("idCateg"));
        String descripcion = request.getParameter("descrip");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));
        
        ProductoJDBCDao productoDAO = new ProductoJDBCDao();
        CategoriaJDBCDao categoriaDao = new CategoriaJDBCDao();
        Categoria categoria = categoriaDao.findByID(categId);
        Proveedor prov = new ProveedorJDBCDAO().findById(provId);
        
         String mensaje = productoDAO.update(new Producto(idProd, prov, categoria, descripcion, precio, existencia));
        request.getSession().setAttribute("opProd", mensaje);
        
        response.sendRedirect("/sistemacomercializadora/productos");
    }

}
