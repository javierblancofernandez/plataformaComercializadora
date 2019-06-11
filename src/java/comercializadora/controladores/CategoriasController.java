/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.CategoriaJDBCDao;
import comercializadora.dao.IProductoDao;
import comercializadora.dao.ProductoJDBCDao;
import comercializadora.modelos.Categoria;
import comercializadora.modelos.Producto;
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
@WebServlet(name = "CategoriasController", urlPatterns = {"/categorias"})
public class CategoriasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "mostrar":
                    mostrarCategoria(request, response);
                    break;
                case "nuevo":
                    formNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request,response);
                    break;
            }
        } else {

            CategoriaJDBCDao daoCategoria = new CategoriaJDBCDao();
            List<Categoria> listaCategoria = daoCategoria.listAll();

            request.setAttribute("categorias", listaCategoria);
            request.getRequestDispatcher("/WEB-INF/categorias/index.jsp").
                    forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = (String)request.getParameter("accion");
            switch (accion) {
                case "crear":
                    insertarCategoria(request,response);
                    break;
                case "borrar":
                    borrarCategoria(request,response);
                    break;
                case "actualizar":
                    actualizarCategoria(request,response);
                    break;

            }
        }

    }

    private void formNuevo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/categorias/formulario.jsp").forward(request, response);

    }

    private void insertarCategoria(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String nombreCategoria=request.getParameter("nombreCategoria");
        long idCat =Long.parseLong(request.getParameter("idCat"));
        
        CategoriaJDBCDao daoCategoria = new CategoriaJDBCDao();
        
        String mensaje=daoCategoria.insert(new Categoria(idCat,nombreCategoria));
        request.getSession().setAttribute("operacionCategoria", mensaje);
        response.sendRedirect("/sistemacomercializadora/categorias");
    }

    private void formEditar(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        long idCat =Long.parseLong(request.getParameter("idCat"));
        CategoriaJDBCDao daoCategoria = new CategoriaJDBCDao();
        Categoria cat = daoCategoria.findByID(idCat);
        if (cat!=null) {
            request.setAttribute("categoria", cat);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/categorias/formulario.jsp").forward(request, response);
            
        }
        
        
        

    }

    private void actualizarCategoria(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException  {
        String nombreCategoria=request.getParameter("nombreCategoria");
        long idCat =Long.parseLong(request.getParameter("idCat"));
        
        CategoriaJDBCDao daoCategoria = new CategoriaJDBCDao();
        
        String mensaje=daoCategoria.update(new Categoria(idCat,nombreCategoria));
        request.getSession().setAttribute("operacionCategoria", mensaje);
        response.sendRedirect("/sistemacomercializadora/categorias");


    }

    private void borrarCategoria(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        long idCat =Long.parseLong(request.getParameter("idCat"));
        
        CategoriaJDBCDao daoCategoria = new CategoriaJDBCDao();
        String mensaje=daoCategoria.borrarByID(new Categoria(idCat));
        
        request.getSession().setAttribute("operacionCategoria", mensaje);
        response.sendRedirect("/sistemacomercializadora/categorias");
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaJDBCDao daoCat=new CategoriaJDBCDao();
        IProductoDao daoPro = new ProductoJDBCDao();
        
        long idCat = Long.parseLong(request.getParameter("idCat"));
        
        Categoria cat= daoCat.findByID(idCat);
        List<Producto> productos = daoPro.getProductoByCategoria(cat);
        cat.setProductos(productos);
        request.setAttribute("categoria", cat);
        
        
        request.getRequestDispatcher("/WEB-INF/categorias/show.jsp").forward(request, response);
        
    }
}
