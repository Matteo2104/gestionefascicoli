<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
.btn-custom:hover{
	outline: none;
	background: none;
	color: #c6dbff;
}

.btn-custom:focus{
	outline: none;
	background: none;
	box-shadow: none;
}
.btn-custom{
	border: none;
}

.badge-danger{
	background-color: #dd3444;
}

</style>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link ${path == 'home'?'active':''}" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>


          
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle ${path == 'gestioneFascicoli'?'active':''}" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Fascicoli</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/fascicolo/search">Ricerca Fascicoli</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/fascicolo/insert">Inserisci Fascicolo</a>
		        </div>
		      </li>
		      
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle ${path == 'gestioneDipendenti'?'active':''}" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Documenti</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/documento/search">Ricerca Documenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/documento/insert">Inserisci Documento</a>
		        </div>
		      </li>
          
		   
        </ul>
      </div>
      

    </div>
  </nav>
  
  
</header>
