<%-- 
    Document   : index
    Created on : 16-feb-2019, 17:27:22
    Author     : FRANCISCO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sistema comercializador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="css/styles.css" rel="stylesheet">

        

    </head>
    <body class="login-bg">
        <div class="container-fluid">

            <div class="page-content container">
                <div class="row center-block">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="login-wrapper">
                            <div class="box">
                                <div class="alert-danger">
                                    <strong>¡ERROR!</strong>${ pageContext.exception.message }
                                </div>
                                <div class="content-wrap">
                                    <h6>Sign Up</h6>
                                    <form action="/sistemacomercializadora/usuarios" method="post">
                                        <input type="hidden" name="accion" value="crearUsuario">
                                        <input class="form-control"  name="user" type="email" placeholder="Escriba un E-mail address">
                                        <br>
                                        <input class="form-control" name="password" type="password" placeholder="Password">
                                        <input class="form-control" name="password2" type="password" placeholder="Confirm Password">
                                        <div class="action">
                                            <input type="submit" value="registrarse" class="btn btn-primary signup">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="listo">
                                <p>¿ya tienes una cuenta?</p>
                                <a href="index.jsp">Login</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
    <style> 
    html.body{
    height: 100%;
    width: 100%;
}

.listo{
    color: white;
}
.login-bg{
    background-image: url(https://www.setaswall.com/wp-content/uploads/2017/03/Hd-Eiffel-Tower-Night-Wallpaper-1920x1080.jpg);
    height: 100%;
    
    background-position: center;
    background-repeat: no-repeat;
    background-size: auto;
    position: relative;
    overflow: hidden;
        
}
.fondo-blur{
    position: relative;
    overflow:hidden;
}
.error{
    color: red;
}
    
    </style>
</html>
