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
								<label for="dataChiusura" class="form-label">Data Chiusura</label>
                        		<input class="form-control" id="dataChiusura" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataChiusura" >
							</div>
							
							<div class="col-md-6">
								<label for="fascicoloSearchInput" class="form-label">Fascicolo:</label>
									<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="fascicoloSearchInput"
										name="fascicoloInput" value="${insert_documento_attr.fascicolo.codice}${empty insert_documento_attr.fascicolo.codice?'':' '}">
									<input type="hidden" name="fascicolo.id" id="fascicoloId" value="${insert_documento_attr.fascicolo.id}">
							</div>
						
							<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
								<script>
									$("#fascicoloSearchInput").autocomplete({
										 source: function(request, response) {
										        $.ajax({
										            url: "../fascicolo/searchFascicoliAjax",
										            datatype: "json",
										            data: {
										                term: request.term,   
										            },
										            success: function(data) {
										                response($.map(data, function(item) {
										                    return {
											                    label: item.label,
											                    value: item.value
										                    }
										                }))
										            }
										        })
										    },
										//quando seleziono la voce nel campo deve valorizzarsi la descrizione
									    focus: function(event, ui) {
									        $("#fascicoloSearchInput").val(ui.item.label)
									        return false
									    },
									    minLength: 2,
									    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
									    select: function( event, ui ) {
									    	$('#fascicoloId').val(ui.item.value);
									    	//console.log($('#registaId').val())
									        return false;
									    }
									});
								</script>
								
							<div class="col-md-6">
								<p> Stato fascicolo </p>
 								<div class="form-check">
  									<input class="form-check-input" type="radio" value = "true" name="riservato" id="flexRadioDefault1">
  										<label class="form-check-label" for="flexRadioDefault1">
    										Riservato
  										</label>
								</div>
								<div class="form-check">
  									<input class="form-check-input" type="radio" value = "false" name="riservato" id="flexRadioDefault2">
  										<label class="form-check-label" for="flexRadioDefault2">
    										Non riservato
  										</label>
								</div>
								
								<div class="form-check">
  									<input class="form-check-input" type="radio" value ="" name="riservato" id="flexRadioDefault2">
  										<label class="form-check-label" for="flexRadioDefault3">
    										Entrambe
  										</label>
								</div>
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