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
        <jsp:useBean class="comercializadora.modelos.Orden" id="orden" scope="session"></jsp:useBean>
              <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12 panel-primary">
                            <div class="content-box-header panel-heading">
                                <div class="panel-title">Realizar Pedido</div>
                            </div>
                            <div class="content-box-large box-with-header">
                                <form action="pedidos" method="post">
                                    <input type="hidden" name="accion" value="terminarPedido">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    Fecha del Pedido
                                                    <input type="text" name="fechaPedido" class="form-control" placeholder="Fecha" value="<%= new SimpleDateFormat("dd-MM-yyyy").format(orden.getFechaOrden()) %>" >
                                                </div>      
                                            </div>
                                            <div class="row" style="margin-top: 15px;">
                                                <div class="col-sm-6">
                                                    Elija un empleado:
                                                    <select class="form-control" name="idEmpleado">
                                                        <c:forEach items="${empleados}" var="empleado">
                                                            <option value="${empleado.empleadoId}">${empleado.nombreCompleto}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>  
                                                <div class="col-sm-6">

                                                    Elija un cliente
                                                    <select class="form-control" name="idCliente">
                                                        <c:forEach items="${clientes}" var="cliente">
                                                            <option value="${cliente.clienteId }">${cliente.nombrecia}</option>
                                                        </c:forEach>
                                                        
                                                    </select>
                                                </div>    
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-12" style="text-align: center;">
                                                <input type="submit" class="btn btn-primary" value="Terminar Pedidos">
                                            </div>
                                            <div class="col-sm-12" style="text-align: center;">
                                                <p style="font-size: 60px; color: #99B3FF;vertical-align: baseline;">
                                                    ${orden.importeRedondeado}€
                                                </p> 
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <br><hr>
                                <div class="row">
                                    <form action="pedidos" method="post">
                                        <input type="hidden" name="accion" value="addProducto">
                                        <div class="col-sm-2">
                                            Teclee una clave:
                                            <input type="text" class="form-control" placeholder="Clave de producto">
                                        </div>
                                        <div class="col-sm-2">
                                            o seleccione un producto :
                                            <select name="idProd" id="" class="form-control">
                                                <option value="" selected="true" disabled="true">Seleccione un producto</option>
                                                <c:forEach items="${productos}" var="producto">
                                                    <option value="${producto.productoId}">${producto.descripcion}</option>
                                                </c:forEach>
                                            </select>
                                        </div> 
                                        <div class="col-sm-2">
                                            Cantidad :
                                            <input type="number" class="form-control" name="cantProd" placeholder="Cantidad"> 
                                        </div> 
                                        <div class="col-sm-2">
                                            <br>
                                            <input type="submit" value="Agregar Producto" class="btn btn-primary">
                                        </div> 
                                    </form>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-12" >
                                        <table class="table">
                                            <thead >
                                                <tr>
                                                    <th>Producto</th>
                                                    <th>Cantidad</th>
                                                    <th>Precio Unitario</th>
                                                    <th>ProductoID</th>
                                                    <th>Importe</th>
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${orden.detalles}" var="detalle">
                                                    <tr>
                                                        <td>${detalle.producto.descripcion}</td>
                                                        <td>${detalle.cantidad}</td>
                                                        <td>${detalle.producto.precioUnit}</td>
                                                        <td>${detalle.producto.productoId }</td>
                                                        <td>${detalle.importeRedondeado}</td>
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
            </div>
        </div>


        <jsp:include page="../layouts/footer.jsp"/>

