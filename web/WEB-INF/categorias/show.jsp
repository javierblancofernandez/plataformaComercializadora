<%@page import="java.util.List"%>
<%@page import="comercializadora.modelos.Categoria"%>
<%@page import="comercializadora.modelos.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Comercializadora</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

    </head>
    <%
        Categoria cat = (Categoria) request.getAttribute("categoria");


    %>


    <body>
        <jsp:include page="../layouts/header.jsp"/>

        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large" >
                                <div class="panel-heading">
                                    <div class="panel-title"> Categoria <%=cat.getNombreCat()%> </div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Clave</th>
                                                <th>Nombre</th>
                                                <th>Existencia</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Producto pro : cat.getProductos()) {%>
                                            <tr>
                                        <td><%= pro.getProductoId()%></td>
                                        <td><%= pro.getDescripcion()%></td>
                                        <td><%= pro.getExistencia()%></td>
                                        </tr>
                                        <% } %>

                                        </tbody>

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
