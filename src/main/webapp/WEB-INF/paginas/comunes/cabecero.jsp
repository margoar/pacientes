        
<!--cabecero-->
<header id="main-header" class="py-2 bg-secondary text-white">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <h1>
                    <i class="fas fa-cog"></i> Control de pacientes</h1>
            </div>
            <%
                session = request.getSession(false);
                if (session.getAttribute("name") != null) { %>
            <div class="col-md-3">
                Bienvenid@ ${userName}
                <a href="${pageContext.request.contextPath}/ServletLogout" class="btn btn-default btn-block" >
                    <i class="fas fa-sign-out-alt"></i> Salir</a>
            </div>

            <% }%> 
        </div>
    </div>
</header>