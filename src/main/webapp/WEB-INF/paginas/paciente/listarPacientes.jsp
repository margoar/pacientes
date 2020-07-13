<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="pacientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado Pacientes</h4>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-sm">
                            <thead class="thead-info">
                                <tr>
                                    <th>#</th>
                                    <th>Rut</th>
                                    <th>Nombre</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="paciente" items="${pacientes}" varStatus="status">
                                    <tr class="${paciente.estadoCovid ? "table-danger" : "table-success"}">
                                        <td>${status.count}</td>
                                        <td> ${paciente.rut}</td>
                                        <td> ${paciente.nombre} ${paciente.apellido}</td>
                                        <td >${paciente.estadoCovid ? "Contagiado" : "Sano"}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idPaciente=${paciente.idPaciente}"
                                               class="btn btn-secondary"> <i class="fa fa-pencil-square-o"></i> </a>

                                            <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idPaciente=${paciente.idPaciente}"
                                               class="btn btn-danger"> <i class="fa fa-trash"></i> </a>
                                        </td>
                                    </tr>
                                </c:forEach> 
                            </tbody>
                        </table>

                    </div>

                </div>

            </div>
            <div class="col-md-3">
                <div class="card text-center bg-info text-white mb-3">
                    <div class="card-body">
                        <h3>Total Pacientes </h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${cantPacientes}
                        </h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3> Sanos</h3>
                        <h4 class="display-4">
                            <i class="fas fa-heart"></i> ${cantSanos}
                        </h4>
                    </div>
                </div>   

                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3> Contagiados</h3>
                        <h4 class="display-4">
                            <i class="fas fa-virus"></i> ${cantContagiados}
                        </h4>
                    </div>
                </div> 

            </div>

        </div>
    </div>
</section>

<!--Agregar cliente modal -->
<jsp:include page="/WEB-INF/paginas/paciente/agregarPaciente.jsp"/>

