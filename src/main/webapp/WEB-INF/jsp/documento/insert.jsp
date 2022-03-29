<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red; 
		    }

		</style>
	   <title>Inserisci Nuovo Documento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="insert_documento_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci nuovo documento</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="insert_documento_attr" method="post" action="save" novalidate="novalidate" class="row g-3">
					
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice <span class="text-danger">*</span></label>
									<spring:bind path="codice">
										<input type="text" name="codice" id="codice" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice" value="${insert_documento_attr.codice }" required>
									</spring:bind>
									<form:errors  path="codice" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
									<spring:bind path="descrizione">
										<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${insert_documento_attr.descrizione }" required>
									</spring:bind>
									<form:errors  path="descrizione" cssClass="error_field" />
								</div>
								

								<!--  
								<div class="col-md-3">
									<div class="form-check">
										<input class="form-check-input" name="privato" type="checkbox" id="privato" >
										<label class="form-check-label" for="privato" >
											Privato
										</label>
									</div>
								</div>
								-->
								
								<div class="col-md-3" >
										<label for="riservato" class="form-label">Riservato <span class="text-danger">*</span></label>
										<spring:bind path="riservato">
								    	<select class="form-select" id="riservato" name="riservato">
								    		<option value="${false }" selected> - Selezionare - </option>
								      		<option value="${true }" >Si</option>
								      		<option value="${false }">No</option>
								    	</select>
								    	</spring:bind>
									<form:errors  path="riservato" cssClass="error_field" />
								</div>

					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>