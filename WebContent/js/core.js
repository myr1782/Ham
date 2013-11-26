function validateForm(formId) {
	var result = true;
	// 清空消息
	$(".validate-error").remove();
	$("#" + formId + " input").each(function(){
		var curCtrl = $(this);
		if(curCtrl.hasClass("ck-required")) {
			if(curCtrl.val() == null || curCtrl.val() == "") {
				curCtrl.addClass("ui-state-error");
				result = false;
				curCtrl.after("<span class='validate-error'>此输入项不能为空!</span>");
			}
		}
	});
	return result;
}