$(function () {

    $('#calendar').calendar({
        ifSwitch: true, // 是否切换月份
        hoverDate: true, // hover是否显示当天信息
        backToday: true // 是否返回当天
    });

    $('#calendar1').calendar({
        ifSwitch: true, // 是否切换月份
        hoverDate: true, // hover是否显示当天信息
        backToday: true // 是否返回当天
    });


   //获取我的待办
    var userId = 1;
    $.ajax({
        url:"/cOrganAgenda/myOrganAgenda",
        type:"post",
        data:{userId:userId},
        dataType:"json",
        success:function(json){
            console.log(json.data.length);
        }

    });
});


