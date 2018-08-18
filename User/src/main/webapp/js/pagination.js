// pagination
$(document).ready(function(){
	var url = new URL(document.location.href);
	var currentPage = url.searchParams.get("page");
	
	if(currentPage == null){
		currentPage = 1;
	}
	
	var page = "#page-" + currentPage;
	$(page).addClass("disabled").children("a").removeAttr("href").css("color", "#346cb0");
});