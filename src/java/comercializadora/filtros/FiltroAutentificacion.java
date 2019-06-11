/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.filtros;

import comercializadora.excepciones.NologeadoException;
import comercializadora.modelos.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author FRANCISCO
 */
@WebFilter(filterName = "FiltroAutentificacion", urlPatterns = {"/*"})
public class FiltroAutentificacion implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public FiltroAutentificacion() {
    }   
    
    private boolean autorizarURL(String url){
        System.out.println("url solicitante :"+ url);
        
        return url.contains("index.jsp")||
                url.contains("registrarse.jsp")||
                url.contains("/sistemacomercializadora/")||
                url.contains("/bootstrap/")||
                url.contains("/css/")||
                url.contains("/js/")||
                url.startsWith("usuarios","/sistemacomercializadora/".length());
                
    
    }
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroAutentificacion:DoBeforeProcessing");
        }
        HttpServletRequest peticion = (HttpServletRequest)request;
        HttpServletResponse respuesta =(HttpServletResponse)response;
        
        Usuario usuario = (Usuario) peticion.getSession().getAttribute("user");
        boolean urlValida = autorizarURL(peticion.getRequestURI());
        
        if (usuario==null) {
            if (!urlValida) {
               throw new ServletException(new NologeadoException("Es necesario logearse antes de entrar en el sistema"));
            }
        }else{
            request.getRequestDispatcher("/WEB-INF/ordenes-de-compra/dashboard.jsp").forward(request, response);
        }

      
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroAutentificacion:DoAfterProcessing");
        }

     
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("FiltroAutentificacion:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
           
        }
    }


   
    
    public void destroy() {        
    }

  
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiltroAutentificacion:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroAutentificacion()");
        }
        StringBuffer sb = new StringBuffer("FiltroAutentificacion(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
 
    
  
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
