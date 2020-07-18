<div class="modal fade" id="agregarPacienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Paciente </h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletPaciente?accion=agregar" method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="col-sm p-2">
                        <div class="alert alert-danger alert-dismissable" style="display: ${validaciones != null ? 'block' : 'none'} ">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>¡Error!</strong> ${validaciones}
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" name="nombre" placeholder="Ingrese su nombre" value="${nombre}" required >
                        </div>
                        <div class="form-group col-md-6">
                            <label for="apellido">Apellido</label>
                            <input type="text" class="form-control" name="apellido" placeholder="Ingrese su apellido" value="${apellido}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rut">Rut</label>
                        <input type="text" class="form-control" name="rut" placeholder="XXXXXXXX-X" required value="${rut}">
                    </div>
                    <div class="form-group">
                        <label for="edad">Edad</label>
                        <input type="number" class="form-control" name="edad" placeholder="Ej: 32" value="${edad}" required>
                    </div>

                    <fieldset class="form-group">
                        <div class="row">
                            <legend class="col-form-label col-sm-2 pt-0">¿Contagiado?</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="estadoCovid" id="id_radio1" value="true" checked>
                                    <label class="form-check-label" for="covidTrue">
                                        Sí
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="estadoCovid" id="id_radio2" value="false">
                                    <label class="form-check-label" for="covidFalse">
                                        No
                                    </label>
                                </div>
                            </div>
                    </fieldset>
                    <!-- Date input -->
                    <div class="form-group" id="div_fecha">
                        <label class="control-label" for="date">Fecha Contagio</label>
                        <input class="form-control" id="fechaContagio" name="fechaContagio" placeholder="DD/MM/YYYY" type="text" value="${fecha}"/>
                    </div>
                </div>
                <input type="hidden" id="estado" value="${estado}"/>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>

            </form>
        </div>
    </div>
</div>

<script>

    $(document).ready(function ()
    {
        console.log("fecha");
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
        ///////////////////////////////////////////////////////////////////////////////////////////
        //radio button esCovid si es "No" no se debe mostrar input fecha
        $("#div_fecha").show();
        $("#fechaContagio").prop('required', true);

        $("#id_radio1").click(function () {
            $("#div_fecha").show();
            $("#fechaContagio").prop('required', true);

        });
        $("#id_radio2").click(function () {
            $("#fechaContagio").prop('required', false);
            $("#div_fecha").hide();

        });
        ///////////////////////////////////////////////////////////////////////////////////////////
        //validacion si el formulario tiene informacion erronea, se abre el modal otra vez con los datos.
        if ($("#estado").val() == 'false') {
            $('#foo').trigger('click');
        }
        //////////////////////////////////////////////////////////////////////////////////////////

    });


</script>
