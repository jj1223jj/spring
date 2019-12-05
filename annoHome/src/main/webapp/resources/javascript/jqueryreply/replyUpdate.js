/**
 * 
 */
var root = null;

function updateToServer(bunho,requestRoot){
	//bunho=requestBunho;
	//var writeReply = document.getElementById("writeReply").value;
	root = requestRoot;
	var params="bunho="+bunho;
	var url=root+"/reply/update.do?"+params;
	
	$.ajax({
		url:url,
		type:"get",
		dataType: "text",
		success: updateFromServer,
		error:function(xhr, status, error){
			alert(xhr+","+status+","+error);
		}
	});
}

function updateFromServer(data){
		
		var result = data.split(","); // split 반환형은 배열
		var bunho = result[0].trim();
		var reply = result[1].trim();
		
		//alert(bunho);
		//alert(reply);
		
		//var listAllDiv = document.getElementById("listAllDiv");

		var replyDiv = document.getElementById(bunho);
		
		var div = "<div id=updateBun"+bunho+"></div>"
		//	document.createElement("div");
		//div.id="updateBun"+bunho;
		
		$("#"+bunho+"").append(div);
		
		var inputReply = document.createElement("input");
		inputReply.type="text";
		
		var buttonNode=document.createElement("input");
		buttonNode.type="button";
		
		$("#updateBun"+bunho).append(inputReply);
		$("#updateBun"+bunho).append(buttonNode);
		
		$(inputReply).val(reply);
		$(buttonNode).val("수정");
			
		$(buttonNode).click(function(){
			updateOkToServer(bunho,$(inputReply).val());
		});
}

function updateOkToServer(bunho,value){
	//root = requestRoot;
	//alert(bunho +","+ root);
	 var params="bunho="+bunho+"&lineReply="+value;
	 var url=root+"/reply/updateOk.do?";
	 
	 $.ajax({
		 url:url,
		 type: "post",
		 data:params,
		 contextType: "application/x-www-form-urlencoded:charset=utf-8",
		 dataType: "text",
		 success: updateOkFromServer
	 });
}

function updateOkFromServer(data){
	
	
	var result = data.split(","); // split 반환형은 배열
	var bunho = result[0].trim();
	var reply = result[1].trim();
	
	$("span:eq(1)").html(reply);
	$("#"+bunho+"").children($("#updateBun"+bunho+"")).remove();
	
	/*
	var replyDiv = document.getElementById(bunho);
	var updateDiv = document.getElementById("updateBun"+bunho);
		
	var span = replyDiv.getElementsByTagName("span");
	span[1].innerHTML=reply;
	replyDiv.removeChild(updateDiv);
	*/	
}





















