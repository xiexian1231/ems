DJMask = {
	msg : function(message) {// 消息提示
		if (message == undefined || message == "")
			return false;
		var msgDom = '<div class="dj-msg-number-'
				+ Math.floor(Math.random() * 1000000) + '"></div>';
		$(msgDom).css({
			"overflow" : "hidden",
			"background" : "rgba(0,0,0,.5)",
			"border-radius" : "4px",
			"position" : "fixed",
			"top" : $(window).height() / 2.5 + "px",
			"left" : $(window).width() / 2 + "px",
			"padding" : "15px",
			"color" : "#fff",
			"z-index" : "999999",
			"display" : "none",
			"max-width" : "200px",
			"word-break" : "break-all"
		}).appendTo("body").html(message);
		var msgDomLoaded = $("." + $(msgDom).attr("class"));// 获取设置样式后的msg元素
		msgDomLoaded.css({// 消息居中
			"margin-left" : "-" + msgDomLoaded.width() / 2 + "px",
			"margin-top" : "-" + msgDomLoaded.height() / 2 + "px",
		}).fadeIn();
		setTimeout(function() {
			$("." + $(msgDom).attr("class")).remove();
		}, 1500);
	},
};