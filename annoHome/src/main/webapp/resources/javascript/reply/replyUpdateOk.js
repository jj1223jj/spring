/**
 * 
 */
function updateOkToServer(bunho,root){
	 var url=root+"/reply/updateOk.do";
	 var params="bunho="+bunho;
	 sendRequest("GET", url, updateOkFromServer, params);
}

function updateOkFromServer(){
	
}