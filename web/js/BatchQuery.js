$(function () {
    var totalPages = 1 ;
    var  totalMessages = 0 ;
    var nowPage = 1 ;
    var jsonValue  ;
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI) ;
    $("#queryButton").click(function () {
        var searchBox1 = $("#searchBox1").val()  ;
        if(searchBox1== null || searchBox1 == ""){
            alert("查询内容不能为空") ;
            return ;
        }
        $.ajax({
            url: '../emailSearch/uploadServlet',
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadForm')[0]),
            processData: false,
            contentType: false,
            success: function(data){
                var json = $.parseJSON(data); ;
                for(var js2 in json){
                    totalMessages++  ;
                }
                totalPages = Math.ceil(totalMessages/5) ;
                $("#span").text("共"+totalMessages+"条记录"+" "+"分"+totalPages+"页 ")  ;
                var jsonObj = JSON.stringify(json);
                jsonValue = $.parseJSON(jsonObj) ;
                if(totalMessages > 0){
                  $("#a").css("display","inline-block") ;
                }
                flip(1) ;
            }
        });

        return false ;
    }) ;

    $("#one").click(function () {
        nowPage = 1 ;
        flip(1) ;


    }) ;
    $("#a_up").click(function () {
        if(nowPage <= 1){
            nowPage = 1 ;
        }else {
            nowPage--;
        }
       flip(nowPage) ;

    }) ;
    $("#a_down").click(function () {

        if(nowPage >= totalPages){
            nowPage = totalPages;
        }else {
            nowPage++;
        }
        flip(nowPage) ;

    }) ;
   $("#over").click(function () {
       nowPage = totalPages ;
       flip(totalPages) ;

   }) ;
    function flip(nowPageNumber) {
            $("#nowPageNumber").text("当前第"+nowPageNumber+"页")  ;
            ergodic(nowPageNumber) ;
    }
    function  ergodic(num) {
        $("#result").empty() ;
        $("#result").append("<tr><td>邮箱信息</td><td>记录数</td><td>安全评估</td></tr>") ;
        for (var i = 0 ;i < 5 ; i++ ) {
            var  temp = (num-1)*5 ;
            $("#result").append(
                "<tr>"
                +"<td>"+jsonValue[i+temp].name+"</td>"
                +"<td>"+jsonValue[i+temp].number+"</td>"
                +"<td class='flag'>"+jsonValue[i+temp].safety+"</td>"+
                +"</tr>");
            judge(i) ;
        }





    }
    
    function  judge(i) {
       var k =  $("td[class='flag']") ;

      if(k[i].innerHTML == '异常'){
         k[i].style.color = 'red' ;
      }
        
    }
})
