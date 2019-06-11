<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="comercializadora.modelos.Empleado"%>
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
        List<Empleado> listaEmpleados = null;
        if (request.getAttribute("empleados") != null) {
            listaEmpleados = (List<Empleado>) request.getAttribute("empleados");
        }

        String resultado = "";
        if (request.getSession().getAttribute("opEmp") != null) {
            resultado = (String) request.getSession().getAttribute("opEmp");

        }
    %>
    <body>
        <jsp:include page="../layouts/header.jsp"/>

        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp"/>
                <div class="col-md-10">
                    <% if (!resultado.isEmpty()) {%>

                    <div class="alert alert-success alert-dismissable fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <%=resultado%>   
                    </div> 
                    <% request.getSession().removeAttribute("opEmp"); %>
                    <% }%>
                    <div class="row">
                        <div class="col-md-12">

                            <div class="panel-heading">
                                <div class="panel-title">TAbla Empleados</div>

                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>

                                            <th>Id Empleado</th>
                                            <th>Nombre Empleado</th>
                                            <th>Apellido Empleado</th>
                                            <th>FEcha NAcimiento Empleado</th>
                                            <th>REporta a Empleado</th>
                                            <th>Extension Empleado</th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% for (Empleado emp : listaEmpleados) {%>
                                        <tr>
                                            <td><%= emp.getEmpleadoId()%></td>
                                            <td><%= emp.getNombreEmpleado()%></td>
                                            <td><%= emp.getApellidoEmpleado()%></td>
                                            <td><%= new SimpleDateFormat("dd-MM-yyyy").format(emp.getFechaNacimiento())%></td>
                                            <td><%= emp.getJefe()%></td>
                                            <td><%= emp.getExtension()%></td>
                                            <td>
                                                <span class="glyphicon glyphicon-refresh"></span>
                                                <a href="empleados?accion=editar&idEmp=<%= emp.getEmpleadoId()%>" class="btn btn-primary" >
                                                    Editar
                                                </a>
                                            </td>
                                            <td>
                                                <form action="empleados" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idEmp" value="<%= emp.getEmpleadoId()%>">
                                                    <span class="add-on"><i class="glyphicon glyphicon-remove-sign"></i></span>
                                                    <input type="submit" value="Borrar" class="btn btn-danger" >

                                                </form>


                                            </td>
                                        </tr>

                                        <%}%>



                                    </tbody>
                                </table>
                                <a href="empleados?accion=nuevo" class="btn btn-primary ">Nueva Empleado</a>
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