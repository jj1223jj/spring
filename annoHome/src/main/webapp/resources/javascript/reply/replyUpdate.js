/**
 * 
 */
var root = null;

function updateToServer(bunho,requestRoot){
	//bunho=requestBunho;
	//var writeReply = document.getElementById("writeReply").value;
	root = requestRoot;
	alert(bunho +","+ root);
	
	 var url=root+"/reply/update.do";
	 var params="bunho="+bunho;
	 sendRequest("GET", url, updateFromServer, params);
}

function updateFromServer(){
	if(xhr.readyState==4 && xhr.status==200){
		//alert(xhr.responseText);
		
		var result = xhr.responseText.split(","); // split 반환형은 배열
		var bunho = result[0].trim();
		var reply = result[1].trim();
		
		//alert(bunho);
		//alert(reply);
		
		//var listAllDiv = document.getElementById("listAllDiv");
		var replyDiv = document.getElementById(bunho);
		var div = document.createElement("div");
		//div.className="replyDiv"; 	// class붙이기
		div.id="updateBun"+bunho;
		
		//alert(replyDiv.value);
		
		var inputReply = document.createElement("input");
		inputReply.type="text";
		inputReply.value = reply;
		
		
		
		var buttonNode=document.createElement("input");
		buttonNode.type="button";
		buttonNode.value="수정";
		//buttonNode.onclick="javascript:updateOkToServer("+bunho+",\'"+inputReply.value+"\')";
		
		buttonNode.onclick=function(){
			updateOkToServer(bunho,inputReply.value);
		}
		div.appendChild(inputReply);
		div.appendChild(buttonNode);
		replyDiv.appendChild(div);

	}
}

function updateOkToServer(bunho,value){
	//root = requestRoot;
	//alert(bunho +","+ root);
	
	 var url=root+"/reply/updateOk.do";
	 var params="bunho="+bunho+"&lineReply="+value;
	 sendRequest("POST", url, updateOkFromServer, params);
}

function updateOkFromServer(){
	if(xhr.readyState==4&&xhr.status==200){
		alert(xhr.responseText);
	
		var result = xhr.responseText.split(","); // split 반환형은 배열
		var bunho = result[0].trim();
		var reply = result[1].trim();
		
		var replyDiv = document.getElementById(bunho);
		var updateDiv = document.getElementById("updateBun"+bunho);
		
		var span = replyDiv.getElementsByTagName("span");
		span[1].innerHTML=reply;
		replyDiv.removeChild(updateDiv);
		
	}
}





















