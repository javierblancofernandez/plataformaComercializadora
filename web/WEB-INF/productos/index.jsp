<%@page import="java.util.List"%>
<%@page import="comercializadora.modelos.Producto"%>
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
    <% List<Producto> listaProducto = null;
    if (request.getAttribute("productos")!=null) {
            listaProducto=(List<Producto>) request.getAttribute("productos");
        }
    String resultado="";
    if (request.getAttribute("opProd")!= null) {
        resultado = (String)request.getAttribute("opProd");
            
        }else if(request.getSession().getAttribute("opProd")!= null){
        resultado = (String)request.getSession().getAttribute("opProd");
        }
    %>
    <body>
        <jsp:include page="../layouts/header.jsp"/>

        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <% if (!resultado.isEmpty() ) { %>
                    
                        <div class="alert alert-success alert-dismissable fade in">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <%=resultado%>   
                        </div> 
                            <% request.getSession().removeAttribute("opProd"); %>
                    <% }%>
                    <div class="row">
                        <div class="col-md-12">

                            <div class="panel-heading">
                                <div class="panel-title">Basic Table</div>

                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>

                                            <th>Id Producto</th>
                                            <th>Proveedor</th>
                                            <th>categoria</th>
                                            <th>Descripcion</th>
                                            <th>Precio Unitario</th>
                                            <th>Existencia</th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% for (Producto prod : listaProducto) {%>
                                        <tr>
                                            <td><%= prod.getProductoId() %></td>
                                            <td><%= prod.getProveedorId().getNombreProv() %></td>
                                            <td><%= prod.getCategoriaId().getNombreCat() %></td>
                                            <td><%= prod.getDescripcion() %></td>
                                            <td><%= prod.getPrecioUnit() %></td>
                                            <td><%= prod.getExistencia() %></td>
                                            <td>
                                                <span class="glyphicon glyphicon-refresh"></span>
                                                <a href="productos?accion=editar&idProd=<%= prod.getProductoId() %>" class="btn btn-primary" >
                                                     Editar
                                                </a>
                                            </td>
                                            <td>
                                                <form action="productos" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idProd" value="<%= prod.getProductoId()%>">
                                                    <span class="add-on"><i class="glyphicon glyphicon-remove-sign"></i></span>
                                                    <input type="submit" value="Borrar" class="btn btn-danger" >
                                                    
                                                </form>
                                                

                                            </td>
                                        </tr>

                                        <%}%>



                                    </tbody>
                                </table>
                                <a href="productos?accion=nuevo" class="btn btn-primary ">Nuevo Producto</a>
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
