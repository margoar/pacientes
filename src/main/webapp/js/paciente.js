$(document).ready(function (){
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
    $("input[name=rut]").keydown(function (e) {
        var key = e.charCode || e.keyCode || 0;
        return (key == 8 ||
                key == 9 ||
                key == 13 ||
                key == 46 ||
                key == 110 ||
                (key >= 35 && key <= 40) ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105)) ||
                key == 189 ||
                key == 190 ||
                key == 46 ||
                key == 75 ||
                key == 107;
    });
});