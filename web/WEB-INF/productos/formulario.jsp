<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="comercializadora.modelos.Producto"%>
<%@page import="comercializadora.modelos.Proveedor"%>
<%@page import="comercializadora.modelos.Categoria"%>
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
        String tipoForm = (String) request.getAttribute("tipoForm");
        Producto prod = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            prod = (Producto) request.getAttribute("producto");

        }
        
        List<Proveedor> proveedores = null;
        List<Categoria> categorias = null;
        if (request.getAttribute("categorias")!=null) {
                categorias=(List<Categoria>)request.getAttribute("categorias");
            }
        if (request.getAttribute("proveedores")!=null) {
                proveedores=(List<Proveedor>)request.getAttribute("proveedores");
            }

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
                                    <div class="panel-title"><%= tipoForm%>Productos </div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="productos" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="accion" value="<%=tipoForm%>">
                                            <div class="form-group">
                                            <label for="idProd" class="col-sm-2 control-label">ID Producto</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idProd" placeholder="Id de producto" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prod.getProductoId());
                                                    } %>">
                                            </div>
                                        </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <select class="form-control" name="idProve">
                                                    <% if (!tipoForm.equals("actualizar")) {%>
                                                    <option value="" selected="true" disabled="true"> Seleccione un proveedor</option>
                                                    <% } %>
                                                    <% for (Proveedor prov : proveedores) { %>
                                                    <option value="<%= prov.getProveedorId() %>" selected="<% if (tipoForm.equals("actualizar")) {
                                                        out.print("true");
                                                    }%>"><%= prov.getNombreProv() %> </option>
                                                    <% } %>
}                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <select class="form-control" name="idCateg">
                                                    <% if (!tipoForm.equals("actualizar")) {%>
                                                    <option value="" selected="true" disabled="true"> Seleccione una categoria</option>
                                                    <% } %>
                                                    <% for (Categoria cat : categorias) { %>
                                                    <option value="<%= cat.getCategoriaId() %>" selected="<% if (tipoForm.equals("actualizar")) {
                                                        out.print("true");
                                                    }%>"><%= cat.getNombreCat() %> </option>
                                                    <% } %>
}                                                </select>
                                            </div>
                                        </div>

                                            <label for="descrip" class="col-sm-2 control-label">Descripcion</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="descrip" placeholder="Descripcion ..." value="<% if (tipoForm.equals("actualizar")) {
                                                        out.print(prod.getDescripcion());
                                                    } %>">
                                            </div>
                                        
                                        <div class="form-group">
                                            <label for="precio" class="col-sm-2 control-label">Precio</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="precio" placeholder="Precio ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prod.getPrecioUnit());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="existencia" class="col-sm-2 control-label">Existencias</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="existencia" placeholder="Existencias ..." value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(prod.getExistencia());
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
