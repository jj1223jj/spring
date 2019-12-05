/**
 * replyWrite.js
 */
var root = null;

function writeToServer(requestRoot){
	//alert("ok");
	root = requestRoot;
	//alert(root);
	var writeReply=$("#writeReply").val();
	var param = "writeReply="+writeReply;
	var url = root+"/reply/replyWrite.do?"+param;
	
	//alert(root+","+url+","+param);
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success: writeFromServer,
		error:function(xhr,status,error){
			alert(xhr+","+status+","+error);
		}
	});
}

//콜백함수
function writeFromServer(data){
	alert(root);
	var result = data.split(","); // split 반환형은 배열
	var bunho = result[0].trim();	//문자열일 경우 공백이 들어갈 수 있기 떄문에 trim을 사용해준다.
	var reply = result[1].trim();
	//alert(bunho+","+reply);

	$("#writeReply").val("");
	
	var div = "<div class ='replyDiv' id="+bunho+"></div>";
	$("#listAllDiv").prepend(div);
	
	var aDelete = "<a href='javascript:deleteToServer("+bunho+",\'"+root+"\')'>삭제</a>";
	var aUpdate = "<a href='javascript:updateToServer("+bunho+",\'"+root+"\')'>수정</a>";
	
	var spanBunho = "<span class='cssBunho'>"+bunho+"</span>";
	var spanReply = "<span class='cssReply'>"+reply+"</span>";
	var spanUpDel = "<span class='cssUpDel'>"+aDelete+ "&nbsp&nbsp" +aUpdate+"</span>";
	
	$("#"+bunho+"").append(spanBunho);
	$("#"+bunho+"").append(spanReply);
	$("#"+bunho+"").append(spanUpDel);
	
	

}