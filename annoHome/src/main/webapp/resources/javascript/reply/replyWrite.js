/**
 * replyWrite.js
 */
var root = null;

function writeToServer(requestRoot){
	//입력한 값 뽑아오기
	var writeReply = document.getElementById("writeReply").value;
	//alert(writeReply);
	
	root = requestRoot;
	var url = root  + "/reply/replyWrite.do";
	var param = "writeReply="+writeReply;
	sendRequest("get", url, writeFromServer, param);
	
}

//콜백함수
function writeFromServer(){
	if(xhr.readyState == 4 && xhr.status == 200){
		alert(xhr.responseText);
		var result = xhr.responseText.split(","); // split 반환형은 배열
		var bunho = result[0].trim();	//문자열일 경우 공백이 들어갈 수 있기 떄문에 trim을 사용해준다.
		var reply = result[1].trim();
		
		document.getElementById("writeReply").value="";		//내용 쓰면 input창 지워주기
		
		var listAllDiv = document.getElementById("listAllDiv");
		
		var div = document.createElement("div");
		div.className="replyDiv"; 	// class붙이기
		div.id=bunho;				// div의 아이디를 번호로 설정하여 삭제할때 번호로 삭제되도록 한다.
		
		var spanBunho = document.createElement("span");
		spanBunho.className="cssBunho";
		spanBunho.innerHTML = bunho;
		
		var spanReply = document.createElement("span");
		spanReply.className="cssReply";
		spanReply.innerHTML = reply;
		
		var spanUpDel = document.createElement("span");
		spanUpDel.className="cssUpDel";
		
		var aDelete = document.createElement("a");
		aDelete.href="javascript:deleteToServer("+bunho+",\'"+root+"\')";	// \' (특수문자가 들어간 문자열) \'
		aDelete.innerHTML = "삭제 &nbsp";
		
		var aUpdate = document.createElement("a");
		aUpdate.href="javascript:updateToServer("+bunho+",\'"+root+"\')";
		aUpdate.innerHTML = "수정";
		
		
		spanUpDel.appendChild(aDelete);
		spanUpDel.appendChild(aUpdate);
		
		div.appendChild(spanBunho);
		div.appendChild(spanReply);
		div.appendChild(spanUpDel);
		
		listAllDiv.insertBefore(div, listAllDiv.childNodes[0]);	//insertBefore(붙일 태그, 있던 태그) : 앞에 붙이기
	}
	
}