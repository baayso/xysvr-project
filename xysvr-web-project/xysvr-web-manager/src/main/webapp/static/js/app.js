/* AmazeUI 全屏函数  Copy from Amaze */
(function($) {
    'use strict';
    $(function() {
        var $fullText = $('.admin-fullText');
        $('#admin-fullscreen').on('click', function() {
            $.AMUI.fullscreen.toggle();
            $.AMUI.fullscreen.isFullscreen ? $fullText.text('关闭全屏') : $fullText.text('开启全屏');
        });
    });
})(jQuery);

/* 后台时间毫秒数转换为YYYY-MM-dd hh:mm:ss Written by 曹仲生 */
function getLocalTime(nS) {
    if (!isNaN(nS)) {
        var now = new Date(nS);
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var date = now.getDate();
        if (date < 10) {
            date = "0" + date;
        }
        var hour = now.getHours();
        if (hour < 10) {
            hour = "0" + hour;
        }
        var minute = now.getMinutes();
        if (minute < 10) {
            minute = "0" + minute;
        }
        var second = now.getSeconds();
        if (second < 10) {
            second = "0" + second;
        }
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }
    else {
        return "";
    }
}

/* 将yyyyMMddHHmmss格式的时间字符串转换成YYYY-MM-dd hh:mm:ss Written by 曹仲生 */
function subStrDateTime(str) {
    if (dt != "") {
        var dt = new String(str);
        var year = dt.substring(0, 4);
        var month = dt.substring(4, 6);
        var day = dt.substring(6, 8);
        var hour = dt.substring(8, 10);
        var minute = dt.substring(10, 12);
        var second = dt.substring(12, 14);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }
    else {
        return "";
    }
}

/* 附件类型截取函数 Written by 曹仲生 */
function regAttachment(att) {
    var result = "";
    if (att != "") {
        var str = new String(att);
        result = str.substring(0, str.indexOf("/"));
    }
    return result;
}

/* 汉化 Datepicker 和 Timepicker。Written by 田志良 */
(function($) {
    // 汉化 Datepicker
    $.datepicker.regional['zh-CN'] = {
        clearText : '清除',
        clearStatus : '清除已选日期',
        closeText : '关闭',
        closeStatus : '不改变当前选择',
        prevText : '&lt;上月',
        prevStatus : '显示上月',
        nextText : '下月&gt;',
        nextStatus : '显示下月',
        currentText : '今天',
        currentStatus : '显示本月',
        monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
        monthNamesShort : [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
        monthStatus : '选择月份',
        yearStatus : '选择年份',
        weekHeader : '周',
        weekStatus : '年内周次',
        dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
        dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
        dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
        dayStatus : '设置 DD 为一周起始',
        dateStatus : '选择 m月 d日, DD',
        dateFormat : 'yy-mm-dd',
        firstDay : 1,
        initStatus : '请选择日期',
        isRTL : false
    };
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);

    // 汉化 Timepicker
    $.timepicker.regional['zh-CN'] = {
        timeOnlyTitle : '选择时间',
        timeText : '时间',
        hourText : '小时',
        minuteText : '分钟',
        secondText : '秒钟',
        millisecText : '微秒',
        timezoneText : '时区',
        currentText : '现在时间',
        closeText : '关闭',
        timeFormat : 'hh:mm',
        amNames : [ 'AM', 'A' ],
        pmNames : [ 'PM', 'P' ],
        ampm : false
    };
    $.timepicker.setDefaults($.timepicker.regional['zh-CN']);
})(jQuery);

/* 广告附件json数组检查组装函数 Written by 曹仲生 */
function adJsonCheck() {
    var check = false;
    if (!($("#thumbJson").val().length > 0)) {
        alert("请上传广告小图");
    }
    else if (!($("#descJson").val().length > 0)) {
        alert("请上传广告大图");
    }
    else {
        $("#attJsonArr").val('[' + $("#thumbJson").val() + ',' + $("#descJson").val() + ']');
        check = true;
    }
    return check;
}

/* 榜单附件json数组检查组装函数 Written by 谭蓉蓉 */
function rankingJsonCheck() {
    var check = false;
    if (!($("#thumbJson").val().length > 0)) {
        alert("请上传图片");
    }
    else {
        $("#attJsonArr").val('[' + $("#thumbJson").val() + ']');
        check = true;
    }
    return check;
}
