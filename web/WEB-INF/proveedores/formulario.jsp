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

    </head>
    <%
        String tipoForm = (String) request.getAttribute("tipoForm");//tipo form crear o actualizar
        Proveedor prov = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            prov = (Proveedor) request.getAttribute("proveedor");

        }
        List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");

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
                                    <div class="panel-title"><%= tipoForm %>Proveedor</div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="proveedores" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="accion" value="<%= tipoForm %>">
                                        </div>

                                        <div class="form-group">

                                            <label for="idProv" class="col-sm-2 control-label">Proveedor ID</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idProv" placeholder="Clave del proveedor ..." value=<% if (tipoForm.equals("actualizar")) {
                                                        out.print(prov.getProveedorId());
                                                    }%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombProv" class="col-sm-2 control-label">Nombre Proveedor</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nombProv" placeholder="NombreProveedor" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prov.getNombreProv());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contProv" class="col-sm-2 control-label">Contacto Proveedor</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="contProv" placeholder="ContactoProveedor" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prov.getContacto());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="celProv" class="col-sm-2 control-label">Móvil Proveedor</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="celProv" placeholder="CelularProveedor" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prov.getCeluProv());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="fijoProv" class="col-sm-2 control-label">Fijo Proveedor</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="fijoProv" placeholder="fijoProveedor" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prov.getFijoProv());
                                                    } %>">
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
