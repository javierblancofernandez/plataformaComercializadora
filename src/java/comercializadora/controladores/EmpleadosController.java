/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.EmpleadoDao;
import comercializadora.modelos.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FRANCISCO
 */
@WebServlet(name = "EmpleadosController", urlPatterns = {"/empleados"})
public class EmpleadosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpleadoDao daoEmpleado = new EmpleadoDao();
        
        if (request.getParameter("accion") != null) {
             String accion = (String) request.getParameter("accion");
            switch (accion) {
                case "nuevo":
                    formNuevo(request,response);
                    break;
                case "editar":
                    formEditar(request,response);
                    break;
            }
        } else {
            List<Empleado> listaEmpleado = daoEmpleado.listAll();
            request.setAttribute("empleados", listaEmpleado);
            request.getRequestDispatcher("/WEB-INF/empleados/index.jsp").
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
                    insertarEmpleado(request, response);
                    break;
                case "borrar":
                    borrarEmpleado(request, response);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response);
                    break;

            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpleadoDao daoEmpleado = new EmpleadoDao();
        List<Empleado> empleados = daoEmpleado.listAll();
        request.setAttribute("empleados", empleados);
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/empleados/formulario.jsp").forward(request, response);
    }



    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            long idEmp = Long.parseLong(request.getParameter("idEmp"));
            String nombreEmpleado = request.getParameter("nombreEmpleado");
            String apellidoEmpleado = request.getParameter("apellidoEmpleado");
            String fecha_nac = request.getParameter("fecha_nac");
            int reportaA = Integer.parseInt(request.getParameter("reporta"));
            int extension = Integer.parseInt(request.getParameter("extension"));

            java.util.Date fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nac);
            java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());
            EmpleadoDao empleadoDAO = new EmpleadoDao();
            String mensaje = empleadoDAO.insert(new Empleado(idEmp, nombreEmpleado, apellidoEmpleado, fechaSQL, reportaA, extension));

            request.getSession().setAttribute("opEmp", mensaje);

            response.sendRedirect("/sistemacomercializadora/empleados");
        } catch (ParseException ex) {
            ex.getStackTrace();
        }
    }

    private void borrarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long claveEmp = Long.parseLong(request.getParameter("idEmp"));

        EmpleadoDao empleadoDAO = new EmpleadoDao();
        String mensaje = empleadoDAO.delete(new Empleado(claveEmp));

        request.getSession().setAttribute("opEmp", mensaje);

        response.sendRedirect("/sistemacomercializadora/empleados");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long idEmp = Long.parseLong(request.getParameter("idEmp"));
            String nombreEmpleado = request.getParameter("nombreEmpleado");
            String apellidoEmpleado = request.getParameter("apellidoEmpleado");
            String fecha_nac = request.getParameter("fecha_nac");
            int reportaA = Integer.parseInt(request.getParameter("reporta"));
            int extension = Integer.parseInt(request.getParameter("extension"));

            java.util.Date fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nac);
            java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());
            EmpleadoDao empleadoDAO = new EmpleadoDao();
            String mensaje = empleadoDAO.update(new Empleado(idEmp, nombreEmpleado, apellidoEmpleado, fechaSQL, reportaA, extension));

            request.getSession().setAttribute("opEmp", mensaje);

            response.sendRedirect("/sistemacomercializadora/empleados");
        } catch (ParseException ex) {
            ex.getStackTrace();
        }
    }
       /* private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        long idEmp = Long.parseLong(request.getParameter("idEmp"));
        EmpleadoDao daoEmpleado = new EmpleadoDao();
        Empleado emp = daoEmpleado.findById(idEmp);
        //List<Empleado> empleados = daoEmpleado.listAll();
        if (emp != null) {

           // request.setAttribute("empleados", empleados);
            request.setAttribute("empleado", emp);
            request.setAttribute("tipoForm", "actualizar");
         
            try {
                request.getRequestDispatcher("/WEB-INF/empleados/formulario.jsp").forward(request, response);
            } catch (IOException ex) {
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
   
        }
    }*/

    private void formEditar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        long idEmp = Long.parseLong(request.getParameter("idEmp"));
        EmpleadoDao daoEmpleado = new EmpleadoDao();
        Empleado emp=daoEmpleado.findById(idEmp);
        List<Empleado> listaEmpleado = daoEmpleado.listAll();
        
        if (emp!=null) {
            request.setAttribute("empleado", emp);
            request.setAttribute("empleados", listaEmpleado);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/empleados/formulario.jsp").
                    forward(request, response);
            
            
            
            
        }
    }

}
