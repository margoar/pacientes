/* global Paciente */

if (typeof (Paciente) == "undefined") {
    Paciente = {};
}

//FORMULARIO AGREGAR PACICENTE

$(document).ready(function () {
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
});
