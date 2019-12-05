/**
 * 
 */

function registerForm(obj){
	//alert("OK");
	
	var check = false;
	for(var i=0; i<obj.mailing.length;i++){
		if(obj.mailing[i].checked==true) check=true;
	}
	
	if(check==false){
		alert("반드시 하나는 체크하세요.");
		return false;
	}
	
	var str="";
	var count=0;
	for(var i=0; i<obj.interestValue.length;i++){
		if(obj.interestValue[i].checked==true){
			str+=obj.interestValue[i].value+",";
			count++;
		}
	}
	alert(str);
	
	obj.interest.value=str;
	
	if(count==0){
		alert("반드시 하나는 체크하세요.");
		return false;
	}
	
	var option = false;
	for(var i=0; i<obj.job.length;i++){
		if(obj.job[i].selected==true) option=true;
	}
	
	if(option==false){
		alert("반드시 하나는 체크하세요.");
		return false;
	}
}

function idCheck(obj,root){
	//alert(obj.id.value);
	
	if(obj.id.value==""){
		alert("아이디를 반드시 입력하세요.");
		obj.id.focus();
		return false;
	}
	
	var url = root + "/member/idCheck.do?id="+obj.id.value;
	//alert(url);
	
	open(url,"","width=150, height=150, scrollbars=yes");
}

function zipcodeRead(root){
	var url = root +"/member/zipcode.do";
	//alert(url);
	
	open(url,"","width=500, height=600,scrollbars=yes");
	
}


function sendAddress(zipcode, sido, gugun, dong, ri, bunji){
	var address= sido + gugun+ dong+ ri+ bunji;
	//alert(zipcode + "," + address);
	
	opener.creatForm.zipcode.value = zipcode;
	opener.creatForm.address.value = address;
	self.close();
}