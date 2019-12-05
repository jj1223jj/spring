/**
 * 
 */
function deleteToServer(bunho,root){
	//alert(bunho+","+root);
	alert(root);
	
	var params="bunho="+bunho;
	var url=root+"/reply/delete.do?"+params;
	
	alert(root+","+url+","+params);
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success: deleteFromServer,
		error:function(xhr,status,error){
			alert(xhr+","+status+","+error);
		}
	});
}

function deleteFromServer(data){
	/*if(xhr.readyState==4&&xhr.status==200){
		alert(xhr.responseText);
		
		var divBunho = document.getElementById(xhr.responseText);
		var listAllDiv=document.getElementById("listAllDiv");
		listAllDiv.removeChild(divBunho);
	}*/
	
	alert(data);
	$("#"+data).remove();
}