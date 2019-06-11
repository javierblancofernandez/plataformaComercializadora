<%@page import="java.text.SimpleDateFormat"%>
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

    </head>
    <%
        String tipoForm = (String) request.getAttribute("tipoForm");//tipo form crear o actualizar
        Cliente cli = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            cli = (Cliente) request.getAttribute("cliente");

        }
        List<Cliente> Clientes = (List<Cliente>) request.getAttribute("clientes");

    %>


    <body>
        <jsp:include page="../layouts/header.jsp"/>

        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="content-box-large" >
                                <div class="panel-heading">
                                    <div class="panel-title"><%= tipoForm%>Cliente</div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="cliente" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="accion" value="<%= tipoForm%>">
                                        </div>

                                        <div class="form-group">

                                            <label for="idCli" class="col-sm-2 control-label">Cliente ID</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idCli" placeholder="Clave del Cliente ..." value=<% if (tipoForm.equals("actualizar")) {
                                                        out.print(cli.getClienteId());
                                                    }%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="cedulaRuc" class="col-sm-2 control-label">Cedula Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="cedulaRuc" placeholder="CedulaCliente" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getCedulaRuc());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombreCia" class="col-sm-2 control-label">Compañia Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nombreCia" placeholder="Compañia" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getNombrecia());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombreCli" class="col-sm-2 control-label">Compañia Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nombreCli" placeholder="Nombre Cliente ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getNombreContacto());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="direccCli" class="col-sm-2 control-label"> Direccion Compañia Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="direccCli" placeholder="Direccion Cliente ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getDireccionCli());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="faxCli" class="col-sm-2 control-label">Fax Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="faxCli" placeholder="FAX Cliente ... " value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getFax());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="emailCli" class="col-sm-2 control-label">Email Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="emailCli" placeholder="Email Cliente ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getEmail());
                                                    }%>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="celularCli" class="col-sm-2 control-label">Teléfono Móvil Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="celularCli" placeholder="Móvil Cliente ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getCelular());
                                                    }%>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="fijoCli" class="col-sm-2 control-label">Teléfono Fijo Cliente</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="fijoCli" placeholder="Móvil Cliente ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(cli.getFijo());
                                                    }%>">
                                            </div>
                                        </div>

                                </div>

                                <br>


                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary"><%= tipoForm%></button>
                                    </div>
                                </div>


                            </div>


                        </div>

                        </form>


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