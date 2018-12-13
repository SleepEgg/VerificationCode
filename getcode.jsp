<%--
  Created by IntelliJ IDEA.
  User: cdlx
  Date: 2018/12/12
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码测试</title>
    <script type="text/javascript" src="jquery-3.3.1.js"></script>
</head>
<body>
    <form action="checkCode" method="post">
        请输入验证码：<input type="text" name="code" style="width:80px"/>
        <img id="imgObj" alt="验证码" src="getCode"><<a href="#" onclick="changeImg()"></a>
        <input type="submit" value="提交"/>
    </form>
</body>
    <script type="text/javascript">
        $(function(){

        });

        function changeImg(){
            var imgSrc = $("#imgObj");
            var src = imgSrc.attr("src");
            imgSrc.attr("src",changeUrl(src));
        }
    //   生成不同验证码，设置时间，不让浏览器读取缓存
        function changeUrl(url) {
            var timeStamp = (new Date()).valueOf();
            url = url.substring(0,20);
            if((url.indexOf("&")>=0)){
                url = url + "xtamp" + timeStamp;
            }else{
                url = url + "?timeStamp=" + timeStamp;
            }
            return url;
        }
    </script>
</html>
