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
        String tipoForm = (String) request.getAttribute("tipoForm");
        Empleado emp = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            emp = (Empleado) request.getAttribute("empleado");

        }
        List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");

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
                                    <div class="panel-title"><%= tipoForm%>Empleado </div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="empleados" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="accion" value="<%=tipoForm%>">
                                        </div>

                                        <div class="form-group">

                                            <label for="idEmp" class="col-sm-2 control-label">empleadoID</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idEmp" placeholder="Clave del empleado ..." value=<% if (tipoForm.equals("actualizar")) {
                                                        out.print(emp.getEmpleadoId());
                                                    }%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombreEmpleado" class="col-sm-2 control-label">NombreEmpleado</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nombreEmpleado" placeholder="NombreEmpleado" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(emp.getNombreEmpleado());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="apellidoEmpleado" class="col-sm-2 control-label">ApellidoEmpleado</label>
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="apellidoEmpleado" placeholder="ApellidoEmpleado" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(emp.getApellidoEmpleado());
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="fecha_nac" class="col-sm-2 control-label">FechaNacimientoEmpleado</label>
                                            <div class="col-sm-12">
                                                <input type="date" class="form-control" name="fecha_nac" placeholder="FechaNacimientoEmpleado" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(new SimpleDateFormat("yyyy-MM-dd").format(emp.getFechaNacimiento()));
                                                    } %>">
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <select class="form-control" name="reporta">
                                                    <% for (Empleado empInt : empleados) {%>
                                                    <option value="<%= empInt.getEmpleadoId()%>" <% if (tipoForm.equalsIgnoreCase("actualizar") && emp.getReporta_a() == empInt.getEmpleadoId()) {
                                                        out.print("selected");
                                                    }%> ><%=empInt.getNombreCompleto()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="extension" class="col-sm-2 control-label">ExtensionEmpleado</label>
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="extension" placeholder="Extension" value="<%if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(emp.getExtension());
                                                    }%>">
                                            </div>
                                        </div>
                                </div>

                                <br>


                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary"><%= tipoForm%></button>
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