<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
         <jsp:useBean class="comercializadora.modelos.Orden" id="ordenCreada" scope="session"></jsp:useBean>
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <% if (ordenCreada != null) {%>
                    <div class="alert alert-success alert-dismissable fade in">
                        <a href="#" clase="close" data-dismiss="alert" aria-label="close">&times;</a>
                        SE ha creado el pedido con folio <%= ordenCreada.getOrdenId()%>
                        
                    </div>
                        <% request.getSession().removeAttribute("ordenCreada"); %>
                        <%}%>
                  
                    <div class="row">
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">TABLA PEDIDOS</div>
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>FECHA PEDIDO</th>
                                            <th>CLIENTE</th>
                                            <th>IMPORTE</th>
                                            <th>ACCIONES</th>
                                        </tr>
                                        
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${ordenes}" var="orden">
                                            <tr>
                                                <td>${orden.ordenId}</td>
                                                <td>${orden.fechaOrden}</td>
                                                <td>${orden.cliente.nombrecia}</td>
                                                <td>${orden.importe}</td>
                                                <td><a href="pedidos?accion=verPedido&idPedido=${orden.ordenId}" class="btn btn-info btn-xs">Ver pedidos</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>   


                    </div>

                </div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </body>
