$("#t1").css({"border-collapse":"separate","border-spacing":"0px 15px"});
$("#t2").css({"border-collapse":"separate","border-spacing":"0px 15px"});
$("th").css("color","grey");
$(":button").css({"color":"white","width":"130px","height":"55px","font-size":"20px"});
$(".caption").css("margin-top","4px");
$(".portlet-title").css("height","50px");
$("#loadinfo").click(function(){
	$.blockUI({ message: "<img src='/ems/ui/img/loading.gif' style='vertical-align:middle;' /> 加载中...", css: {color:'#fff',border:'3px solid #aaa',backgroundColor:'#CC3300'},overlayCSS: { opacity:'0.0' }});   
	var planenumber = $("#planenumber").text();
	var entrydate = $("#entrydate").text();
	var username = $("#username").text();
	var cardlist =  {
			"planenumber":planenumber,
			"entrydate":entrydate,
			"username":username
	};
	$.ajax({
   	    type : "post",
   		url : "/ems/jobcard/queryJobcards",
   		data : {
			"cardlist":JSON.stringify(cardlist)
		},
   		async : true,
   		success : function(data) {
	    	$.unblockUI();
	    	$("#info").css("display","block");
	    	var jsonArr = JSON.parse(data);
	    	$('#t2').find("tr:not(:first)").remove();
	    	$.each(jsonArr, function (index,value) {
	    		var taskTime = value.workingHours;
	    		if(taskTime == null){
	    			taskTime = "0";
	    		}
	    		//使用正则表达式获取文件名：replace(/(.*\/)*([^.]+).*/ig,"$2")
				$('#t2').append("<tr><td nowrap='nowrap'>" + "<a target='_blank' href='/ems/jobcard/transform/" + value.id + "/" + taskTime + "/"+ value.filePath.replace(/(.*\/)*([^.]+).*/ig,"$2") + "'>打开</a>" + "</td> <td nowrap='nowrap'>" + value.number + "</td> <td nowrap='nowrap'>" + value.version + "</td> <td nowrap='nowrap'>" + value.chapter + "</td> <td nowrap='nowrap'>" + value.category + "</td> <td nowrap='nowrap'>" + value.completeState + "</td></tr>"); 
           	});
	    	$("#tb  tr").each(function(){
	    		var a =  $.trim($(this).children("td").eq(5).text());
	    		var b = $.trim("未完成");
	    		if(a==b){
	    			$(this).children("td").eq(5).css("color","red");
	    		}
	    	});
	    	$("#tb  tr").each(function(){
	    		$(this).children("td").eq(0).children("a").css({"font-weight":"bolder"});
	    	});
   		}
   	});
});

$('#changePassword').on('click', function () {
    $.confirm({
    	title: false,
    	keyboardEnabled: true,
        content: '*旧密码<input id="oldpwd" type="password" class="form-control" placeholder="请输入旧密码"/>*新密码<input id="newpwd" type="password" class="form-control" placeholder="请输入新密码"/>*确认新密码<input id="confirm" type="password" class="form-control" placeholder="请再次输入新密码"/>',
        confirmButton: "确认",
    	cancelButton: "取消",
    	confirm: function () {
    		var patrn = /^(\w){3,20}$/;
    		var username = $("#primaryName").text();
    		var oldpwd = $("#oldpwd").val();
    		var newpwd = $("#newpwd").val();
    		var confirm = $("#confirm").val();
    		var user = {
    				"username":username,
    				"oldpwd":oldpwd,
    				"newpwd":newpwd,
    				"confirm":confirm
    			};
            if(!patrn.exec(oldpwd) || !patrn.exec(newpwd) || !patrn.exec(confirm)){
            	$.alert({
            		title: false,
            		keyboardEnabled: true,
            		content:'只能输入3-20个字母、数字、下划线的密码！',
            		confirmButton: "确认",
            	});
            }else{
            	$.ajax({
		    	    type : "post",
		    		url : "/ems/user/changePassword",
		    		data : {
		    			"user":JSON.stringify(user)
					},
		    		async : true,
		    		success : function(data) {
		    			var msg = JSON.parse(data);
		    			if(msg=="密码修改成功"){
		    				$.alert({
	                    		title: false,
	                    		keyboardEnabled: true,
	                    		content:'密码修改成功',
	                    		confirmButton: "确认",
	                    	});
		    			}else{
		    				$.alert({
	                    		title: false,
	                    		keyboardEnabled: true,
	                    		content:''+msg,
	                    		confirmButton: "确认",
	                    	});
		    			}
		    		}
		    	});
            }
        },
        cancel: function () {
        }
    });
});

$('#logout').on('click', function () {
	$.confirm({
	    title: '退出?',
	    keyboardEnabled: true,
	    content: '系统将在5秒后自动退出.',
	    autoClose: 'confirm|5000',
	    confirmButton: "确认",
    	cancelButton: "取消",
	    confirm: function(){
	    	window.location.href="/ems/user/logout";
	    },
	    cancel:function(){
	    }
	});
});
   
