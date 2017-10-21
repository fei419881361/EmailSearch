$(function () {
    $("#queryButton").click(function () {
       // alert("1") ;
        var url = "../emailSearch/manualQueryServlet" ;
        var searchBox = $("#searchBox").val()  ;
        if(searchBox == null || searchBox == ""){
            alert("查询内容不能为空") ;
            return ;
        }

        var reg=/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
        if(!reg.test(searchBox)){
            alert("邮箱格式不正确") ;
            searchBox.text("") ;
            return  ;
        }
        var args = {"Date": new Date(),"searchBox":searchBox} ;
        $.getJSON(url,args,function (data) {

            $("#result").empty().append(
                "<tr><td>邮箱信息</td><td>记录数</td><td>安全评估</td></tr>"
                +"<tr>"
                +"<td>"+data.name+"</td>"
                +"<td>"+data.number+"</td>"
                +"<td>"+data.safety+"</td>"+
                +"</tr>");
        })

        return false ;
      }) ;
})

