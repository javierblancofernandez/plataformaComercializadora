/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.CategoriaJDBCDao;
import comercializadora.dao.ICategoriaDao;
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
@WebServlet(name = "BuscadorController", urlPatterns = {"/buscar"})
public class BuscadorController extends HttpServlet {

 
  

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String parametro = request.getParameter("param");
        
        ICategoriaDao daoCategoria= new CategoriaJDBCDao();
        IProductoDao daoProducto=new ProductoJDBCDao();
        
        List<Categoria> categorias = daoCategoria.busquedaPorCriterio(parametro);
        List<Producto> productos = daoProducto.busquedaPorCriterio(parametro);
        
        request.setAttribute("categorias", categorias);
       request.setAttribute("productos", productos);
       
       request.getRequestDispatcher("/WEB-INF/buscador/buscador.jsp").forward(request, response);
     
     
    }



}
