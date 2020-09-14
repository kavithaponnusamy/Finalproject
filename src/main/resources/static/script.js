function onBtnMapViewClick(){
	var divmapview=document.getElementById("divMapView");
	var btnmapview=document.getElementById("btnMapView");
	divmapview.setAttribute("style", "display: block;");
	btnmapview.classList.remove("btn-secondary");
	btnmapview.classList.add("btn-primary");
}

function onBtnDetailViewClick(){
	var divmapview=document.getElementById("divMapView");
	var btnmapview=document.getElementById("btnMapView");
	divmapview.setAttribute("style", "display: none;");

	btnmapview.classList.remove("btn-primary");
		btnmapview.classList.add("btn-secondary");
}