<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/documento/insert">Aggiungi nuovo</a>
			    	<a href="${pageContext.request.contextPath}/documento/search" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Codice</th>
			                        <th>Data Creazione</th>
			                        <th>Data Ultima modifica</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${documento_list_attribute }" var="documentoItem">
									<tr>
										<td>${documentoItem.codice }</td>
										<td><fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${documentoItem.dataCreazione}' /></td>
										<td><fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${documentoItem.dataUltimaModifica}' /></td>
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/documento/show/${documentoItem.id }">Visualizza</a>
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/documento/edit/${documentoItem.id }">Edit</a>
<<<<<<< HEAD
											<a class="btn  btn-sm btn-outline-danger ml-2 mr-2" href="${pageContext.request.contextPath}/documento/delete/${documentoItem.id }">Delete</a>
=======
											<a id="changeStatoLink_#_${documentoItem.id }" class="btn btn-outline-danger btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModal"  >Elimina</a>
>>>>>>> 8e6f6ae191b3ee1f2675d1527b8935d01f573c36
										</td>
										
										
										
									</tr>
								</c:forEach>
			                </tbody>
			            </table>
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	
	
	
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Continuare con l'operazione?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/documento/delete" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idDocumentoForDelete" id="idDocumentoForDelete">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function() {
			<!-- mi prendo il numero che poi sar?? l'id. Il 18 ?? perch?? 'changeStatoLink_#_' ?? appunto lungo 18  -->
			var callerId = $(this).attr('id').substring(18);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idDocumentoForDelete').val(callerId);
		});
	</script>
	
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal2" tabindex="-1"  aria-labelledby="confirmOperationModalLabel2"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel2">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Sei sicuro di voler Resettare la password a questo utente?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/utente/adminReset" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idUtenteForResetPassword" id="idUtenteForResetPassword">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function(){
			<!-- mi prendo il numero che poi sar?? l'id. Il 20 ?? perch?? 'resetPasswordLink_#_' ?? appunto lungo 20  -->
			var callerId = $(this).attr('id').substring(20);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idUtenteForResetPassword').val(callerId);
		});
	</script>
	
</body>
</html>