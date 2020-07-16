<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/b7f99ecc57.js" crossorigin="anonymous"></script>

        <!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
        <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
        <link rel="stylesheet" href="css/login.css" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>

        <div class="row mt-5">
            <div class="col-5"></div>
            <div class="col-2">
                <button class="btn btn-success btn-block" data-toggle="modal" data-target="#login_itech"> <i class="fas fa-sign-in-alt"> </i> Login</button>

            </div>
            <div class="col-5"></div>
        </div>

        <div class="modal animated fadeIn" id="login_itech" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Login</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="${pageContext.request.contextPath}/ServletLogin?accion=login"
                          method="POST" class="was-validated">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="usuario">Usuario</label>
                                <input type="text"  name="usuario" class="form-control" a placeholder="Ingrese nombre de usuario" required>
                            </div>
                            <div class="form-group">
                                <label for="contrasenia">Contraseña</label>
                                <input type="password" name="contrasenia" class="form-control"  placeholder="Ingrese contraseña" required>
                            </div>
                            <div class="text-center" id="loader_itech_no">
                                <button type="submit" class="btn btn-primary"> Entrar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <script type="text/javascript" src="js/Login.js"></script>
    </body>
</html>
