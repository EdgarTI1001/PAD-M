/**
 * Funções auxiliares
 */



function verificaDados(){
	var numero = $("#numeroUrna").val();
	var nome = $("#nome").val();
	var nomeUrna = $("#nomeUrna").val();
	var titulo = $("#titulo").val();
	var foto = $("#foto").val();
	var idCand =  $("#idCand").val();	
	
	if(nome.length == 0){
		swal("Erro", "Nome não pode ficar vazio", "error");
		$("#nome").css({"border-color": "#FF0000", 
             "border-width":"1px", 
             "border-style":"solid"});
             
		return false	
	}
	
	if(nomeUrna.length == 0){
		swal("Erro", "Nome Urna não pode ficar vazio", "error");
		$("#nomeUrna").css({"border-color": "#FF0000", 
             "border-width":"1px", 
             "border-style":"solid"});
             
		return false	
	}
	
	if(numero.length == 0){
		swal("Erro", "Numero Urna não pode ficar vazio", "error");
		$("#numeroUrna").css({"border-color": "#FF0000", 
             "border-width":"1px", 
             "border-style":"solid"});
             
		return false	
	}
	
	if(titulo.length == 0){
		swal("Erro", "Titulo Eleitoral  Urna não pode ficar vazio", "error");
		$("#titulo").css({"border-color": "#FF0000", 
             "border-width":"1px", 
             "border-style":"solid"});
             
		return false	
	}	
		
	if(numero >= 95 && numero <= 99){
		swal("Erro", "Numero do candidato não pode estar dentro do seguinte intervalo: de 95 a 99 ", "error");
		return false	
	}	
	 
	if(!(foto)){	
		    
		if(idCand == ""){		    		
		    	swal("Erro", " Foto nao pode ficar vazia ", "error"); 
			    return false;
		   }else{
		    	return true; 
		    }
		    	
		
	} 
	

		    
		      
	
	return true;	
	
}
 