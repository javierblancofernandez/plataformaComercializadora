<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="comercializadora.modelos.Proveedor"%>
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
        List<Proveedor> listaProveedores = null;
        if (request.getAttribute("proveedores") != null) {
            listaProveedores = (List<Proveedor>) request.getAttribute("proveedores");
        }

        String resultado = "";

        if (request.getAttribute("opProv") != null) {
            resultado = (String) request.getAttribute("opProv");
        } else if (request.getSession().getAttribute("opProv") != null) {
            resultado = (String) request.getSession().getAttribute("opProv");
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
                    <% request.getSession().removeAttribute("opProv"); %>
                    <% }%>
                    <div class="row">
                        <div class="col-md-12">

                            <div class="panel-heading">
                                <div class="panel-title">Tabla Proveedores</div>

                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>

                                            <th>Id Proveedor</th>
                                            <th>Nombre Proveedor</th>
                                            <th>Contacto Proveedor</th>
                                            <th>Movil Proveedor</th>
                                            <th>Fijo Proveedor</th>


                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% for (Proveedor prov : listaProveedores) {%>
                                        <tr>
                                            <td><%= prov.getProveedorId()%></td>
                                            <td><%= prov.getNombreProv()%></td>
                                            <td><%= prov.getContacto()%></td>
                                            <td><%= prov.getCeluProv()%></td>
                                            <td><%= prov.getFijoProv()%></td>

                                            <td>
                                                <span class="glyphicon glyphicon-refresh"></span>
                                                <a href="proveedores?accion=editar&idProv=<%= prov.getProveedorId()%>" class="btn btn-primary" >
                                                    Editar
                                                </a>
                                            </td>
                                            <td>
                                                <form action="proveedores" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idProv" value="<%= prov.getProveedorId()%>">
                                                    <span class="add-on"><i class="glyphicon glyphicon-remove-sign"></i></span>
                                                    <input type="submit" value="Borrar" class="btn btn-danger" >

                                                </form>


                                            </td>
                                        </tr>

                                        <%}%>



                                    </tbody>
                                </table>
                                <a href="proveedores?accion=nuevo" class="btn btn-primary ">Nuevo Proveedor</a>
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
