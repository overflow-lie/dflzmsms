(function($){
	//15位 
	var imeiRegex=/^\d{15}$/;
	//MCC+MNC+MSIN 
	var imsiRegex=/^4600[0,1,2,3,5,6,7]\d{10}$/g;	
	
	var defaultMPhoneRegex = /^1\d{10}$/;
	
	jQuery.fn.validateLength = function () { 
		if($(this).val() == null || $(this).val()==""){
			alert("VIN为必填项");
			return false;
		};
		if($(this).val().length!=17){
			alert("VIN长度须为17");
			return false;
		}
		return true;
	}
	
	jQuery.fn.validateIMEI = function(){
		if($(this).val() == null || $(this).val()==""){
			alert("IMEI为必填项");
			return false;
		};
		if(imeiRegex.test($(this).val())!=true){
			alert("IMEI格式不正确");
			return false;
		}
		return true;
	}
	
	jQuery.fn.validateIMSI = function(){
		if($(this).val() == null || $(this).val()==""){
			alert("IMSI为必填项");
			return false;
		};
		if(imsiRegex.test($(this).val())!=true){
			alert("IMSI格式不正确");
			return false;
		}
		return true;
	}
	
	jQuery.fn.validateMPhone = function(){
		if($(this).val() == null || $(this).val()==""){
			alert("手机号为必填项");
			return false;
		};
		if(defaultMPhoneRegex.test($(this).val())!=true){
			alert("请输入正确的手机号码！");
			return false;
		}
		return true;
	}
	
})(jQuery);
