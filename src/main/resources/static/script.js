function onBtnMapViewClick(){
	var divmapview=document.getElementById("divMapView");
	var btnmapview=document.getElementById("btnMapView");
	divmapview.setAttribute("style", "display: block;");
	btnmapview.classList.remove("btn-secondary");
	btnmapview.classList.add("btn-primary");
	
	let cardimages=document.querySelectorAll("[data-card]");
		 cardimages.forEach((element)=>{ 
				element.classList.remove("col-md-4");
				 element.classList.add("class","col-md-6");
			 
		 })
}

function onBtnDetailViewClick(){
	var divmapview=document.getElementById("divMapView");
	var btnmapview=document.getElementById("btnMapView");
	divmapview.setAttribute("style", "display: none;");

	btnmapview.classList.remove("btn-primary");
		btnmapview.classList.add("btn-secondary");
		
		let cardimages=document.querySelectorAll("[data-card]");
		 cardimages.forEach((element)=>{ 
				element.classList.remove("col-md-6");
				 element.classList.add("class","col-md-4");
			 
		 })
}

function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

