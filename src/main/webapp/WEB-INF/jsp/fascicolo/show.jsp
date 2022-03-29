<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza Dettaglio Fascicolo</title>
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio Fascicolo 
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_fascicolo_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome:</dt>
					  <dd class="col-sm-9">${show_fascicolo_attr.codice}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${show_fascicolo_attr.descrizione}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Creazione:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_fascicolo_attr.dataCreazione}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Ultima Modifica:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_fascicolo_attr.dataUltimaModifica}" /></dd>
			    	</dl>
			    	
			    	
			    	
			    	
			    	
			    	
			    	
			    	<!-- info Richieste Permesso -->
			    	<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Documenti
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  <c:forEach items="${show_fascicolo_attr.documenti}" var="documentoItem">
						  	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice:</dt>
							  <dd class="col-sm-9">${documentoItem.codice}</dd>
						   	</dl>
						   	<dl class="row">
							  <dt class="col-sm-3 text-right">Descrizione:</dt>
							  <dd class="col-sm-9">${documentoItem.descrizione}</dd>
						   	</dl>
						   	<dl class="row">
					  			<dt class="col-sm-3 text-right">Data Creazione:</dt>
					  			<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${documentoItem.dataCreazione}" /></dd>
			    				</dl>
			    			<dl class="row">
					  			<dt class="col-sm-3 text-right">Data Ultima Modifica:</dt>
					  			<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${documentoItem.dataUltimaModifica}" /></dd>
			    			</dl>
			    			
						   	<dl class="row">
							  <dt class="col-sm-3 text-right">Riservato:</dt>
							  <dd class="col-sm-9">${documentoItem.riservato?'Si':'No'}</dd>
						   	</dl>
						   	
						   	<a class="btn  btn-sm btn-outline-secondary col-md-3" href="${pageContext.request.contextPath}/documento/showAttachment/${documentoItem.id }">Scarica Allegato</a>
						   	
						   
					   </c:forEach>
					  </div>
					<!-- end info Ruoli -->
					</div>
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>

			        <a href="${pageContext.request.contextPath }/fascicolo" class='btn btn-outline-secondary' style='width:100px'>
			            <i class='fa fa-chevron-left'></i> Indietro
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>