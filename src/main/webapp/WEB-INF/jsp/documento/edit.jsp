<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <style>
		    .error_field {
		        color: red; 
		    }
		</style>
		<title>Modifica Documento</title>
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
			<!-- Begin page content -->
			<main class="flex-shrink-0">
				<div class="container">
		
					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="edit_documento_attr">
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
					        <h5>Modifica Documento</h5> 
					    </div>
					    <div class='card-body'>
				    
				    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
							<form:form modelAttribute="edit_documento_attr"  method="post" action="${pageContext.request.contextPath }/documento/update" novalidate="novalidate" class="row g-3">
								<form:hidden path="id"/>
								
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice </label>
									<spring:bind path="codice">
										<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire codice" value="${edit_documento_attr.codice }"  >
									</spring:bind>
									<form:errors  path="codice" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione </label>
									<spring:bind path="descrizione">
										<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire descrizione" value="${edit_documento_attr.descrizione }"  >
									</spring:bind>
									<form:errors  path="descrizione" cssClass="error_field" />
								</div>
								
								<div class="col-md-3">
									<label for="riservato" class="form-label">Riservato <span class="text-danger">*</span></label>
									    <select class="form-select " id="riservato" name="riservato" required >
									      	<option value="${true }" ${edit_documento_attr.riservato?'checked':''}>Si</option>
									    	<option value="${false }" ${edit_documento_attr.riservato?'':'checked'}>No</option>
								    	</select>
								</div>
								
								
								<div class="col-md-6" id="fileAllegato">
									  <label for="fileAllegato" class="form-label">Allegato <span class="text-danger">*</span></label>
									  <input class="form-control" type="file" id="fileAllegato" name="fileAllegato" >
								</div>
								
								
								
								
									
								<div class="col-12">	
									<a href="${pageContext.request.contextPath}/documento" class='btn btn-outline-secondary' style='width:100px'>
					            		<i class='fa fa-chevron-left'></i> Indietro
					        		</a>
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									
								</div>
		
							</form:form>
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>	
			
			<!-- end container -->	
			</div>	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>