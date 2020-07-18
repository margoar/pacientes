<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/b7f99ecc57.js" crossorigin="anonymous"></script>
        <title>Editar Paciente</title>
    </head>
    <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
    <form action="${pageContext.request.contextPath}/ServletPaciente?accion=modificar&idPaciente=${paciente.idPaciente}" method="POST" class="was-validated">
        <section id="actions" class="py-4 mb-4 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">

                        <a href="pacientes.jsp" class="btn btn-ligth btn-block">
                            <i class="fas fa-arrow-left"></i> Regresar al inicio
                        </a>
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-success btn-block">
                            <i class="fas fa-check"></i> Guardar Paciente  <i class="fas fa-notes-medical"></i> 
                        </button>
                    </div>
                </div>
            </div>    
        </section>
        <seccion id="details">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header">
                                <h4>Editar Paciente</h4>
                            </div>
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>

                                    <input type="text" class="form-control" name="nombre" required value="${paciente.nombre}">
                                </div>
                                <div class="form-group">
                                    <label for="apellido">Apellido</label>
                                    <input type="text" class="form-control" name="apellido" required value="${paciente.apellido}">
                                </div>
                                <div class="form-group">
                                    <label for="apellido">Rut</label>
                                    <input type="text" class="form-control" name="rut" required value="${paciente.rut}" disabled>
                                </div>
                                <div class="form-group">
                                    <label for="apellido">Edad</label>
                                    <input type="text" class="form-control" name="edad" required value="${paciente.edad}" >
                                </div>
                                <div class="form-group">
                                    <label for="estadoCovid">Estado Covid</label>
                                    <input type="text" class="form-control" name="estadoCovid" required value="${paciente.estadoCovid ? "Contagiado" : "Sano"}" disabled>
                                </div>
                                <div class="form-group">
                                    <label for="fechaContagio">Fecha Contagio</label>
                                    <fmt:formatDate value="${paciente.fechaContagio}" pattern="dd/MM/yyyy" var="fechaCont" />
                                    <input type="text" class="form-control" name="fechaContagio" required value="${fechaCont}" disabled>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </seccion>
    </form>   
    <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>
