<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		
	<title>Ricerca Documenti</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca Documenti</h5> 
			    </div>
			    <div class='card-body'>
	
						<form method="post" action="${pageContext.request.contextPath}/documento/list" class="row g-3">
						
							<div class="col-md-6">
								<label for="codice" class="form-label">Codice</label>
								<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" >
							</div>
							
							<div class="col-md-6">
								<label for="descrizione" class="form-label">Descrizione</label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire l'oggetto" >
							</div>
							
							<div class="col-md-6">
								<label for="dataCreazione" class="form-label">Data di creazione</label>
                        		<input class="form-control" id="dataCreazione" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataCreazione" >
							</div>
							
							<div class="col-md-6">
								<label for="dataUltimaModifica" class="form-label">Data Ultima modifica</label>
                        		<input class="form-control" id="dataUltimaModifica" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataUltimaModifica" >
							</div>
							
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath}/documento/insert">Add New</a>
							</div>
	
							
						</form>
			    
			    				
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>