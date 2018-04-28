var _ctxPath="/Joanna";/*网站路径*/
var _mediaPath="http://192.168.2.196:8080/Joanna";/*媒体路径*/
//var _defaultRegionPath="2.3621.3635.";/*默认城市*/


jQuery(function($){   


/**
 * 字符串以开始
 */
String.prototype.startWith=function(str){
	var reg=new RegExp("^"+str);
	return reg.test(this);
};
/**
 * 字符串以结尾
 */
String.prototype.endWith=function(str){
	var reg=new RegExp(str+"$");
	return reg.test(this);
};

/**
*点击全选框
*/
function selectAll(o){
	var ch_id=document.getElementsByName("ch_id");
	for(var i=0;i<ch_id.length;i++){
		ch_id[i].checked=o.checked;
	}
}

/**
 * 判断是否登录
 * @param id
 */
function isLoggedA(path,appUserId,ctxPath){
    //利用对话框返回的值 (true 或者 false)
	//HttpSession session=request.getSession();
	//session.setAttribute("path",path);
    var appUserId =appUserId;
    if( appUserId !=null && appUserId !="" ){
    	 document.location.href=path;
    }else{
    	if (confirm("您未登录,是否登录？")){
    		var strUrl=path.replace(/\//g, "__");
    		document.location.href=_ctxPath+"/login?strUrl="+strUrl;
    	}
    }
}
/**
 *	a标签 关键词 查询 --招聘
 * @param zpJobKey
 */
function seletByZpJobKey(zpJobKey){
	var $zpJobKey=zpJobKey;
	$("#zpJobKey").val($zpJobKey);
	$("#current").val("");
	$("#listForm").submit();
}
/**
 * a标签 关键词 查询 --团购--招聘 合用
 * @param searchKey
 */
function seletBySearchKey(searchKey){
	var $searchKey=searchKey;
	$("#searchKey").val(searchKey);
	$("#current").val("");
	$("#listForm").submit();
}

/**
 * input file 马上预览图片 适用于页面多个图片上传 styleRegEdit
 */
function file_uploadSy(preview,file_upload){
	jQuery("#"+file_upload).change(function() {
		var $file = $(this);
		var fileObj = $file[0];
		var windowURL = window.URL || window.webkitURL;
		var dataURL;
		var jQueryimg = $("#"+preview);
		 
		if(fileObj && fileObj.files && fileObj.files[0]){
		dataURL = windowURL.createObjectURL(fileObj.files[0]);
		jQueryimg.attr('src',dataURL);
	}else{
		dataURL = $file.val();
		var imgObj = document.getElementById(preview);
		// 两个坑:
		// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
		// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
		imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
		imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		 
		}
	});
}
/**
 * input file 马上预览图片
 */
jQuery(function() {
	jQuery("#file_upload").change(function() {
		var $file = $(this);
		var fileObj = $file[0];
		var windowURL = window.URL || window.webkitURL;
		var dataURL;
		var jQueryimg = $("#preview");
		 
		if(fileObj && fileObj.files && fileObj.files[0]){
		dataURL = windowURL.createObjectURL(fileObj.files[0]);
		jQueryimg.attr('src',dataURL);
	}else{
		dataURL = $file.val();
		var imgObj = document.getElementById("preview");
		// 两个坑:
		// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
		// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
		imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
		imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		 
		}
	});
});


/**       
 * 对Date的扩展，将 Date 转化为指定格式的String       
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
 * eg:       
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18  
 * 其他格式 MM/dd/yyyy HH  MM/dd/yyyy 等等     
 */          
Date.prototype.pattern=function(fmt) { 
    var o = {
    "M+" : this.getMonth()+1, //月份
    "d+" : this.getDate(), //日
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时 
    "H+" : this.getHours(), //小时 
    "m+" : this.getMinutes(), //分
    "s+" : this.getSeconds(), //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S" : this.getMilliseconds() //毫秒
    };
    var week = {
    "0" : "/u65e5",
    "1" : "/u4e00",
    "2" : "/u4e8c",
    "3" : "/u4e09",
    "4" : "/u56db",
    "5" : "/u4e94",
    "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}


/* 增加部分=============================== */
function mouseOutPic(bigPicID) //当鼠标移出时，隐藏原图
{
	document.getElementById(bigPicID).style.visibility = "hidden";
}
function mouseOverPic(bigPicID) //当鼠标移上小图时，显示原图
{
	document.getElementById(bigPicID).style.visibility = "visible";
}
/* =============================== */

/**
 * 时间控件 设定最小日期
 */
function setMinDate(time){
    var start = {
        dateCell: time,
        format: 'YYYY-MM-DD',
        minDate: jeDate.now(0), //设定最小日期为当前日期
        festival:true,
        maxDate: '2099-06-16 23:59:59', //最大日期
    };
    jeDate(start);
};
/**
 * 时间控件 日期无限制
 */
function setDate(time){
	 var start = {
	    dateCell: time,
	    format: 'YYYY-MM-DD',
	    festival:true,
	    maxDate: '2099-06-16 23:59:59', //最大日期
	};
	jeDate(start);
};
/**
 * 时间控件 日期无限制hh:mm:ss
 */
function setDateHHMMSS(time){
	var start = {
	    dateCell: time,
	    format: 'YYYY-MM-DD hh:mm:ss',
	    festival:true,
	    maxDate: '2099-06-16 23:59:59', //最大日期
	};
	jeDate(start);
};
/**
 * 时间控件 日期无限制hh:mm
 */
function setDateHHMM(time){
	var start = {
	    dateCell: time,
	    format: 'YYYY-MM-DD hh:mm',
	    festival:true,
	    maxDate: '2099-06-16 23:59:59', //最大日期
	};
	jeDate(start);
};
});