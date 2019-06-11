<%@page import="java.util.List"%>
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
    <% List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("categorias");
    String resultado="";
    if (request.getSession().getAttribute("operacionCategoria")!= null) {
        resultado = (String)request.getSession().getAttribute("operacionCategoria");
            
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
                            <% request.getSession().removeAttribute("operacionCategoria"); %>
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

                                            <th>Id Categoria</th>
                                            <th>Nombre Categoria</th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% for (Categoria cat : listaCategoria) {%>
                                        <tr>
                                            <td><%= cat.getCategoriaId()%></td>
                                            <td><%= cat.getNombreCat()%></td>
                                            <td>
                                                <a href="categorias?accion=mostrar&idCat=<%= cat.getCategoriaId()%>" class="btn btn-default">
                                                Mostrar
                                                </a>
                                            </td>
                                            <td>
                                                <span class="glyphicon glyphicon-refresh"></span>
                                                <a href="categorias?accion=editar&idCat=<%= cat.getCategoriaId()%>" class="btn btn-primary" >
                                                     Editar
                                                </a>
                                            </td>
                                            <td>
                                                <form action="categorias" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idCat" value="<%= cat.getCategoriaId()%>">
                                                    <span class="add-on"><i class="glyphicon glyphicon-remove-sign"></i></span>
                                                    <input type="submit" value="Borrar" class="btn btn-danger" >
                                                    
                                                </form>
                                                

                                            </td>
                                        </tr>

                                        <%}%>



                                    </tbody>
                                </table>
                                <a href="categorias?accion=nuevo" class="btn btn-primary ">Nueva Categoria</a>
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