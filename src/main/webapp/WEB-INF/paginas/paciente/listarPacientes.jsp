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
                    <table class="table table-striped">
                        <thead class="thead-info">
                            <tr>
                                <th>#</th>
                                <th>Rut</th>
                                <th>Nombre</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="paciente" items="${pacientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td> ${paciente.rut}</td>
                                    <td> ${paciente.nombre} ${paciente.apellido}</td>
                                    <td>

                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary"> <i class="fa fa-pencil-square-o"></i> </a>

                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}"
                                           class="btn btn-danger"> <i class="fa fa-trash"></i> </a>
                                    </td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>

            </div>
            <div class="col-md-3">
                <div class="card text-center bg-info text-white mb-3">
                    <div class="card-body">
                        <h3>Total Pacientes </h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> 4
                        </h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3> Sanos</h3>
                        <h4 class="display-4">
                            <i class="fas fa-heart"></i>  3
                        </h4>
                    </div>
                </div>   

                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3> Contagiados</h3>
                        <h4 class="display-4">
                            <i class="fas fa-virus"></i> 4
                        </h4>
                    </div>
                </div> 

            </div>

        </div>
    </div>


</section>