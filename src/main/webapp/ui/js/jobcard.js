/*提示框点击事件开始*/
$("#b_main").hide();
$("#h_main").hide();
$("#f_main").hide();
$("#l_main").hide();
$("#tb1 tr td").eq(2).children("button").click(function() {
	$("#b_main").toggle();
	if ($('#h_main').is(':visible')) {
		$('#h_main').hide("slow");
	}
	if ($('#f_main').is(':visible')) {
		$('#f_main').hide("slow");
	}
	if ($('#l_main').is(':visible')) {
		$('#l_main').hide("slow");
	}
});
$("#tb1 tr td").eq(3).children("button").click(function() {
	$("#h_main").toggle();
	if ($('#b_main').is(':visible')) {
		$('#b_main').hide("slow");
	}
	if ($('#f_main').is(':visible')) {
		$('#f_main').hide("slow");
	}
	if ($('#l_main').is(':visible')) {
		$('#l_main').hide("slow");
	}
});
$("#tb1 tr td").eq(4).children("button").click(function() {
	$("#f_main").toggle();
	if ($('#b_main').is(':visible')) {
		$('#b_main').hide("slow");
	}
	if ($('#h_main').is(':visible')) {
		$('#h_main').hide("slow");
	}
	if ($('#l_main').is(':visible')) {
		$('#l_main').hide("slow");
	}
});
$("#tb1 tr td").eq(7).children("button").click(function() {
	$("#l_main").toggle();
	if ($('#b_main').is(':visible')) {
		$('#b_main').hide("slow");
	}
	if ($('#h_main').is(':visible')) {
		$('#h_main').hide("slow");
	}
	if ($('#f_main').is(':visible')) {
		$('#f_main').hide("slow");
	}
});
/* 提示点击事件结束 */

/* 点击关闭提示框开始 */
$("#Delc").click(function() {
	$("#b_main").hide();
});

$("#Deld").click(function() {
	$("#h_main").hide();
});

$("#Delp").click(function() {
	$("#f_main").hide();
});

$("#Delw").click(function() {
	$("#l_main").hide();
});
/* 点击关闭结束 */

$("#d1").css({
	"width" : "10%",
	"float" : "left",
	"vertical-align" : "middle",
	"text-align" : "center",
	"border" : "1px #36D7B7 solid"
});

$("#th1 tr th").css({
	"vertical-align" : "middle",
	"text-align" : "center",
	"font-size" : "20px"
});

/*
 * $("#tb1 tr td").each(function(){
 * $(this).children("button").css({"width":"96px","height":"96px"}); });
 */

$("tbody tr td").find("div").each(function() {
	$(this).css({
		"margin-left" : "50px",
		"position" : "relative"
	});
});

$("tbody tr td").find("div").children("span").each(function() {
	$(this).css({
		"position" : "absolute",
		"margin-top" : "16px"
	})
});

/*
 * $("tbody tr td").find("div").children("button").each(function(){
 * $(this).click(function(){ $(this).parent().html("张三"); }); });
 */

$("tbody tr td").css({
	"vertical-align" : "middle"
});

$("tbody tr td").find("div").children("button").each(function() {
	$(this).css({
		"width" : "55px",
		"height" : "55px"
	});
});

$("#ds").find("button").each(function() {
	$(this).css({
		"width" : "70px",
		"height" : "40px",
		"margin-left" : "28px"
	});
});

$("#pagiDiv span").each(function() {
	$(this).css({
		"margin-left" : "28px",
		"font-size" : "20px"
	});
});

$("#pagiDiv label").each(function() {
	$(this).css({
		"font-size" : "20px"
	});
});

/* 参考资料，航材资料，日期样式 */
$("#References").css({
	"overflow-y" : "auto",
	"height" : "330px",
	"display" : "block",
	"position" : "relative"
});

$("#Equipment").css({
	"overflow-y" : "auto",
	"height" : "330px",
	"display" : "block",
	"position" : "relative"
});

$("#References thead tr").children("th").css({
	"vertical-align" : "middle",
	"text-align" : "center"
});

$("#References thead tr").css({
	"background-color" : "#BFCAD1"
});

$("#Equipment thead tr").children("th").css({
	"vertical-align" : "middle",
	"text-align" : "center"
});

$("#Equipment thead tr").css({
	"background-color" : "#BFCAD1"
});

/* 工卡信息的保存操作 */
$("#save-info").click(function() {
	$("#save-info").attr('disabled', true);
	/* start截取url中的id参数 */
	var url = window.location.href;
	var urlArr = url.split("/");
	var id = urlArr[urlArr.length - 3];
	/* end截取 */
	/* start截取url中的fileName参数 */
	var fileName = url.replace(/(.*\/)*([^.]+).*/ig, "$2");
	/* end截取 */
	/* start获取工时数据,同时把时间戳转为秒 */
	var time = $("#txt").val();
	var hour = time.split(':')[0];
	var min = time.split(':')[1];
	var sec = time.split(':')[2];
	var taskTime = Number(hour * 3600) + Number(min * 60) + Number(sec);
	/* end */
	var inputs = $("#iframe").contents().find("input");
	var jsonArr = [];
	inputs.each(function() {
		var obj = $(this); // 遍历得到的每一个jquery对象
		var xpath = obj.attr("xpath");
		var value = obj.val();
		var jsonObj = {
			"xpath" : xpath,
			"value" : value
		};
		jsonArr.push(jsonObj);
	});
	$.ajax({
		type : "post",
		url : "/ems/jobcard/store",
		data : {
			"jsonArr" : JSON.stringify(jsonArr),
			"taskTime" : taskTime,
			"id" : id,
			"fileName" : fileName
		},
		async : true,
		success : function(data) {
			$("#save-info").attr('disabled', false);
			DJMask.msg(data);
		}
	});

});
/* end */

/* datepicker开始 */
$(function() {
    $("#datepicker").datepicker({// 添加日期选择功能
    	showAnim:'blind',// 特效
    	changeMonth: true,
        changeYear: true,
    	dateFormat: 'yy-mm-dd',// 日期格式
        minDate:'2010-01-01',// 最小日期
        maxDate:'2050-01-01',// 最大日期
        dayNamesMin: ['日','一','二','三','四','五','六'],  
    });
});
/* 结束 */

//js中工时计时器 start
var timer;
var h;
var m;
var s;  
var t;  
var flag=0;  
  
function setflag(obj,para){ 
	if(!timer) {
		timer = para;
		h = timer.split(":")[0].replace(/\b(0+)/gi,"")==""?"0":timer.split(":")[0].replace(/\b(0+)/gi,"");  
		m = timer.split(":")[1].replace(/\b(0+)/gi,"")==""?"0":timer.split(":")[1].replace(/\b(0+)/gi,"");  
		s = timer.split(":")[2].replace(/\b(0+)/gi,"")==""?"0":timer.split(":")[2].replace(/\b(0+)/gi,"");  
	}
    if(flag == 0){
    	obj.innerText="停止";
        flag=1;  
        timedCount(flag);  
    }else {  
    	obj.innerText="启动";
        flag=0;  
        timedCount(flag);  
    }  
}  

function settime(a){  
    if(a<10)  
        a = "0"+a;  
    return a;  
}

function timedCount(flag){ 
	var showh = settime(h);  
    var showm = settime(m);  
    var shows = settime(s);
    if(flag==1){  
        document.getElementById('txt').value=showh+":"+showm+":"+shows;  
        s++;  
        if(s == 60){  
            s = 0;  
            m++;  
        }  
        if(m == 60){  
            m = 0;  
            h++;  
        }  
        t=setTimeout("timedCount(flag)",1000);  
    }else {   
    	s-=1;
    	clearTimeout(t);  
    }  
}
// end

