
<div class="modal fade" id="agregarPacienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Paciente </h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="POST" class="was-validated">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" name="apellido" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Rut</label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="telefono">Edad</label>
                        <input type="tel" class="form-control" name="telefono" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Esta Contagiado</label>
                        <input type="number" class="form-control" name="saldo" required step="any">
                    </div>
                       <div class="form-group">
                        <label for="saldo">Fecha Contagio: </label>
                        <input type="number" class="form-control" name="saldo" required step="any">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>

            </form>
        </div>
    </div>
</div>