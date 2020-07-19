<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/b7f99ecc57.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
        <title>Editar Paciente</title>
    </head>
    <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
    <form action="${pageContext.request.contextPath}/ServletPaciente?accion=modificar&idPaciente=${paciente.idPaciente}" method="POST" class="was-validated" id="formEdit">
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
                                    <label for="rut">Rut</label>
                                    <input type="text" class="form-control" name="rut" required value="${paciente.rut}" readonly>

                                </div>
                                <div class="form-group">
                                    <label for="edad">Edad</label>
                                    <input type="text" class="form-control" name="edad" required value="${paciente.edad}" >
                                </div>



                                <fieldset class="form-group">
                                    <div class="row">
                                        <legend class="col-form-label col-sm-2 pt-0">¿Contagiado?</legend>
                                        <div class="col-sm-10">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="estadoCovid" id="id_radio1" value="true" ${paciente.estadoCovid ? "checked": ""}>
                                                <label class="form-check-label" for="covidTrue">
                                                    Sí
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="estadoCovid" id="id_radio2" value="false" ${paciente.estadoCovid ? "": "checked"}>
                                                <label class="form-check-label" for="covidFalse">
                                                    No
                                                </label>
                                            </div>
                                        </div>
                                </fieldset>
                                <!-- Date input -->
                                <div class="form-group" id="div_fecha">
                                    <label class="control-label" for="date">Fecha Contagio</label>
                                    <fmt:formatDate value="${paciente.fechaContagio}" pattern="dd/MM/yyyy" var="fechaCont" />
                                    <input type="text" class="form-control" name="fechaContagio" value="${fechaCont}">
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

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

    <script>
        $(document).ready(function () {
            console.log("fechaedicion");
            //input fecha
            var date_input = $('input[name="fechaContagio"]'); //our date input has the name "date"
            var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
            var options = {
                format: 'dd/mm/yyyy',
                container: container,
                todayHighlight: true,
                autoclose: true,
                language: 'es'
            };
            date_input.datepicker(options);

            console.log($('input[name=estadoCovid]:checked', '#formEdit').val());
            if ($('input[name=estadoCovid]:checked', '#formEdit').val() === 'false') {
                $("#div_fecha").hide();
            }

            $("#id_radio1").click(function () {
                $("#div_fecha").show();
                $("#fechaContagio").prop('required', true);

            });
            $("#id_radio2").click(function () {
                $("#fechaContagio").prop('required', false);
                $("#div_fecha").hide();

            });
        });
    </script>
</body>
</html>

