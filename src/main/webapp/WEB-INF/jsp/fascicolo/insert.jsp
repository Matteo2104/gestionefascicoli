<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<style>
		    .error_field {
		        color: red; 
		    }
	</style>
	<jsp:include page="../header.jsp" />
	<title>Inserisci Fascicolo</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="insert_fascicolo_attr">
						<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">
				Attenzione!! Sono presenti errori di validazione
			</div>
		</spring:hasBindErrors>

			
		
			<div class='card'>
			    <div class='card-header'>
			        <h5>Inserisci nuova fascicolo</h5> 
			    </div>
			    
			    <div class='card-body'>
					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
						<form:form modelAttribute="insert_fascicolo_attr" method="post" action="save" novalidate="novalidate" class="row g-3" enctype="multipart/form-data">
						  
									
							<div class="col-md-6">
									<label for="codice" class="form-label">Codice<span class="text-danger">*</span></label>
									<spring:bind path="codice">
									<input type="text" name="codice" id="codice" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice" value="${insert_fascicolo_attr.codice }" required>
									</spring:bind>
									<form:errors  path="codice" cssClass="error_field" />
								</div>
							
							<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione<span class="text-danger">*</span></label>
									<spring:bind path="descrizione">
									<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${insert_fascicolo_attr.descrizione }" required>
									</spring:bind>
									<form:errors  path="descrizione" cssClass="error_field" />
								</div>
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
  
				    
				    
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