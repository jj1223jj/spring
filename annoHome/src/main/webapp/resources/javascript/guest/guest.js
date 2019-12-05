
/**
 * guest.js
 */
function updateCheck(root, num, currentPage){
	var url = root +"/guest/update.do?num=" + num +"&pageNumber="+ currentPage;
	//alert(url);
	location.href = url;
}


function deleteCheck(root, num, currentPage){
	var url=root + "/guest/delete.do?num=" + num + "&pageNumber=" + currentPage;
	//alert(url);
	
	var value = confirm("정말로 삭제하시겠습니까?");
	if(value == true){
		location.href = url;  //삭제하려면 url 주소로 이동
	}
}