
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
                        <div class="col-sm-12">
                            <div class="content-box-large" >
                                <div class="panel-heading" >
                                    <div class="panel-title">
                                        DASHBOARD PEDIDOS
                                    </div>
                                    <div class="panel-body">
                                        <div class="col-sm-6">
                                            
                                            <a href="pedidos?accion=verPedidos" class="btn btn-large btn-primary" >
                                               <span class="glyphicon glyphicon-eye-open"></span> Ver Pedidos
                                            </a>
                                        </div>
                                        <div class="col-sm-6">
                                            
                                            <a href="pedidos?accion=hacerPedidos" class="btn btn-primary" >
                                                <span class="glyphicon glyphicon-pencil"></span> Hacer Pedidos
                                            </a>
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

