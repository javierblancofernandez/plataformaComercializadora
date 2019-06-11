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
    <% String tipoForm = (String)request.getAttribute("tipoForm");
    Categoria cat=null;
    if (tipoForm.equalsIgnoreCase("actualizar")) {
        cat=(Categoria)request.getAttribute("categoria");
            
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
                                    <div class="panel-title">Crear Categoria </div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="categorias" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="accion" value="<%=tipoForm %>">
                                        </div>

                                        <div class="form-group">

                                            <label for="idCat" class="col-sm-2 control-label">categoriaid</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idCat" placeholder="idCat" value="<%if (tipoForm.equalsIgnoreCase("actualizar")){ out.print(cat.getCategoriaId());} %>" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombreCat" class="col-sm-2 control-label">NombreaCategoria</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nombreCategoria" placeholder="NombreCategoria" value="<%if (tipoForm.equalsIgnoreCase("actualizar")){ out.print(cat.getNombreCat());} %>">
                                            </div>
                                        </div>
                                        <br>


                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <button type="submit" class="btn btn-primary"><%= tipoForm %></button>
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