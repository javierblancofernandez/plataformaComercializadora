<%@page import="java.text.SimpleDateFormat"%>
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
        <jsp:useBean class="comercializadora.modelos.Orden" id="orden" scope="request"></jsp:useBean>
            <div class="page-content">
                <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <div class="col-md-12 panel-primary">
                        <div class="content-box-header panel-heading">
                            <div class="panel-title">
                                Pedido ${orden.ordenId}
                            </div>
                        </div>


                        <div class="content-box-large box-with-header">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="col-sm-3">
                                        Fecha del pedido :
                                        <h4><%= new SimpleDateFormat("yyyy-MM-dd").format(orden.getFechaOrden())%></h4>
                                    </div>
                                    <div class="col-sm-3">
                                        Vendedor :
                                        <h4>${orden.empleado.nombreEmpleado}</h4>
                                    </div>
                                    <div class="col-sm-3">
                                        Cliente :
                                        <h4>${orden.cliente.nombrecia}</h4>
                                    </div>
                                </div>
                            </div>
<hr>
                    <br>
                    <table class="table">
                                <thead>
                                    <tr>
                                        <th>Producto</th>
                                        <th>Cantidad</th>
                                        <th>Precio Unitario</th>
                                        <th>Importe</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${orden.detalles}" var="detalle" >
                                        <tr>
                                            <td>${ detalle.producto.descripcion }</td>
                                            <td>${ detalle.cantidad }</td>
                                            <td>${ detalle.producto.precioUnit }</td>
                                            <td>${ detalle.importe }</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </div>
                    
                  
                </div>
            </div>


        </div>
    

    <jsp:include page="../layouts/footer.jsp"/>
</body>
