<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <body>
        <jsp:include page="../layouts/header.jsp"/>

        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">

                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Resultado Categorias</div>

                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <c:forEach items="${categorias}" var="categ">
                                            <tr>
                                                <td><a href="categorias?accion=mostrar&idCat=${categ.categoriaId}" class="btn btn-link">${categ.nombreCat}</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Resultado Productos</div>

                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <c:forEach items="${productos}" var="prod">
                                        <tr>
                                            <td><a href="productos?accion=editar&idProd=${prod.productoId }" class="btn btn-link">${prod.descripcion}</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
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

