<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<i class="bi bi-download"></i>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			
			    <div class='card-body'>
			    
			    	<dl class="row">
						<dt class="col-sm-3 text-right">ID:</dt>
						<dd class="col-sm-9">${show_documento_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
						<dt class="col-sm-3 text-right">Codice:</dt>
						<dd class="col-sm-9">${show_documento_attr.codice}</dd>
			    	</dl>
			    	<dl class="row">
						<dt class="col-sm-3 text-right">Descrizione:</dt>
						<dd class="col-sm-9">${show_documento_attr.descrizione}</dd>
			    	</dl>
			

			    	<dl class="row">
						<dt class="col-sm-3 text-right">Data  Creazione:</dt>
						<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_documento_attr.dataCreazione}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
						<dt class="col-sm-3 text-right">Data ultima Modifica:</dt>
						<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_documento_attr.dataUltimaModifica}" /></dd>
			    	</dl>
	
			    	<dl class="row">
						<dt class="col-sm-3 text-right">Stato:</dt>
						<dd class="col-sm-9">${ show_documento_attr.getRiservato()? 'Riservato' : 'Non riservato'}</dd>
					</dl>
					
					<dl
						class="row ${show_documento_attr.fileAllegato==null? 'd-none' : ''}">
						<dt class="col-sm-3 text-right">Allegato:</dt>
						<dd class="col-sm-9">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
  								<path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
  								<path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
							</svg>
							<a href="${pageContext.request.contextPath}/documento/showAttachment/${show_documento_attr.id}"> ${show_documento_attr.codice}</a>
						</dd>
					</dl>
	
			    	<!-- info Documento -->
			    	<p>
					  <a class="btn btn-outline-success btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Documento
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Codice:</dt>
						  <dd class="col-sm-9">${show_documento_attr.fascicolo.codice}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Descrizione:</dt>
						  <dd class="col-sm-9">${show_documento_attr.fascicolo.descrizione}</dd>
					   	</dl>
					    
					  </div>
					<!-- end info Documento -->
					</div>
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/documento/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
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