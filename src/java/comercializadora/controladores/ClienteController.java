/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.ClienteDao;
import comercializadora.modelos.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
@WebServlet(name = "ClienteController", urlPatterns = {"/cliente"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClienteDao daoCliente = new ClienteDao();

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
            List<Cliente> listaCliente = daoCliente.listaClientes();
            request.setAttribute("clientes", listaCliente);
            request.getRequestDispatcher("/WEB-INF/clientes/index.jsp").
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
                    insertarCliente(request, response);
                    break;
                case "borrar":
                    borrarCliente(request, response);
                    break;
                case "actualizar":
                    actualizarCliente(request, response);
                    break;

            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDao daoCliente = new ClienteDao();
        List<Cliente> clientes = daoCliente.listaClientes();
        request.setAttribute("clientes", clientes);
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/clientes/formulario.jsp").forward(request, response);

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idCli = Long.parseLong(request.getParameter("idCli"));
        ClienteDao daoCliente = new ClienteDao();
        Cliente cli = daoCliente.findById(idCli);
        List<Cliente> listaCliente = daoCliente.listaClientes();

        if (cli != null) {
            request.setAttribute("cliente", cli);
            request.setAttribute("clientes", listaCliente);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/clientes/formulario.jsp").
                    forward(request, response);

        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long idCli = Long.parseLong(request.getParameter("idCli"));
        String cedulaRuc = request.getParameter("cedulaRuc");
        String nombreCia = request.getParameter("nombreCia");
        String nombreCliente = request.getParameter("nombreCli");
        String direccCliente = request.getParameter("direccCli");
        String faxCliente = request.getParameter("faxCli");
        String emailCliente = request.getParameter("emailCli");
        String celularCliente = request.getParameter("celularCli");
        String fijoCliente = request.getParameter("fijoCli");
        ClienteDao daoCliente = new ClienteDao();
        String mensaje = daoCliente.insert(new Cliente(idCli, cedulaRuc, nombreCia, nombreCliente, direccCliente, faxCliente, emailCliente, cedulaRuc, fijoCliente));
        request.getSession().setAttribute("opCli", mensaje);
        response.sendRedirect("/sistemacomercializadora/cliente");

    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long claveCli = Long.parseLong(request.getParameter("idCli"));

        ClienteDao daoCliente = new ClienteDao();
        String mensaje = daoCliente.delete(new Cliente(claveCli));

        request.getSession().setAttribute("opCli", mensaje);

        response.sendRedirect("/sistemacomercializadora/cliente");

    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long idCli = Long.parseLong(request.getParameter("idCli"));
        String cedulaRuc = request.getParameter("cedulaRuc");
        String nombreCia = request.getParameter("nombreCia");
        String nombreCliente = request.getParameter("nombreCli");
        String direccCliente = request.getParameter("direccCli");
        String faxCliente = request.getParameter("faxCli");
        String emailCliente = request.getParameter("emailCli");
        String celularCliente = request.getParameter("celularCli");
        String fijoCliente = request.getParameter("fijoCli");
        ClienteDao daoCliente = new ClienteDao();
        String mensaje = daoCliente.update(new Cliente(idCli, cedulaRuc, nombreCia, nombreCliente, direccCliente, faxCliente, emailCliente, cedulaRuc, fijoCliente));
        request.getSession().setAttribute("opCli", mensaje);
        response.sendRedirect("/sistemacomercializadora/cliente");
    }

}
