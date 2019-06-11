
<%@page import="java.util.List"%>
<%@page import="comercializadora.modelos.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bootstrap Admin Theme v3</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <%
        List<Cliente> listaCliente = null;
        if (request.getAttribute("clientes") != null) {
            listaCliente = (List<Cliente>) request.getAttribute("clientes");
        }

        String resultado = "";

        if (request.getAttribute("opCli") != null) {
            resultado = (String) request.getAttribute("opCli");
        } else if (request.getSession().getAttribute("opCli") != null) {
            resultado = (String) request.getSession().getAttribute("opCli");
        }
    %>
    <body>
        <jsp:include page="../layouts/header.jsp"/>

        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <% if (!resultado.isEmpty()) {%>

                    <div class="alert alert-success alert-dismissable fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <%=resultado%>   
                    </div> 
                    <% request.getSession().removeAttribute("opCli"); %>
                    <% }%>
                    <div class="row">
                        <div class="col-md-12">

                            <div class="panel-heading">
                                <div class="panel-title">Tabla Clientes</div>

                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>

                                            <th>Id Cliente</th>
                                            <th>Cedula Cliente</th>
                                            <th>Nombre Compañia</th>
                                            <th>Contacto Cliente</th>
                                            <th>Direccion Cliente</th>
                                            <th>Fax Cliente</th>
                                            <th>Email Cliente</th>
                                            <th>Movil Cliente</th>
                                            <th>Fijo Cliente</th>


                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% for (Cliente cli : listaCliente) {%>
                                        <tr>
                                            <td><%= cli.getClienteId()%></td>
                                            <td><%= cli.getCedulaRuc()%></td>
                                            <td><%= cli.getNombrecia()%></td>
                                            <td><%= cli.getNombreContacto()%></td>
                                            <td><%= cli.getDireccionCli()%></td>
                                            <td><%= cli.getFax()%></td>
                                            <td><%= cli.getEmail()%></td>
                                            <td><%= cli.getCelular()%></td>
                                            <td><%= cli.getFijo()%></td>

                                            <td>
                                                <span class="glyphicon glyphicon-refresh"></span>
                                                <a href="cliente?accion=editar&idCli=<%= cli.getClienteId()%>" class="btn btn-primary" >
                                                    Editar
                                                </a>
                                            </td>
                                            <td>
                                                <form action="cliente" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idCli" value="<%= cli.getClienteId()%>">
                                                    <span class="add-on"><i class="glyphicon glyphicon-remove-sign"></i></span>
                                                    <input type="submit" value="Borrar" class="btn btn-danger" >

                                                </form>


                                            </td>
                                        </tr>

                                        <%}%>



                                    </tbody>
                                </table>
                                <a href="cliente?accion=nuevo" class="btn btn-primary ">Nuevo Cliente</a>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>

