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


document.addEventListener("DOMContentLoaded", function(event) {
   
const showNavbar = (toggleId, navId, bodyId, headerId) =>{
const toggle = document.getElementById(toggleId),
nav = document.getElementById(navId),
bodypd = document.getElementById(bodyId),
headerpd = document.getElementById(headerId)

// Validate that all variables exist
if(toggle && nav && bodypd && headerpd){
toggle.addEventListener('click', ()=>{
// show navbar
nav.classList.toggle('show')
// change icon
toggle.classList.toggle('bx-x')
// add padding to body
bodypd.classList.toggle('body-pd')
// add padding to header
headerpd.classList.toggle('body-pd')


 
})
}
}

showNavbar('header-toggle','nav-bar','body-pd','header')

/*===== LINK ACTIVE =====*/
const linkColor = document.querySelectorAll('.nav_link')

function colorLink(){
if(linkColor){
linkColor.forEach(l=> l.classList.remove('active'))
this.classList.add('active')
}
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

 // Your code to run since DOM is loaded and ready
});
 