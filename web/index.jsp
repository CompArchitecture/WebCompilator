<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="" lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zaza Angels</title>
    <link rel="stylesheet" href="http://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.min.css">
    <link href="http://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css" rel="stylesheet"
          type="text/css">

    <style type="text/css"></style>
    <meta class="foundation-mq">
</head>
<body>

<div class="top-bar">
    <div class="top-bar-left">
        <ul class="menu">
        </ul>
    </div>
</div>

<div class="callout large primary">
    <div class="row column text-center">
        <!--<h1>ტესტ</h1>-->
        <h2 class="subheader">online კომპილატორი</h2>
    </div>
</div>

<%
    boolean uploaded = response != null && request.getAttribute("uploaded") != null && (Boolean) request.getAttribute("uploaded");
    boolean uploadTried = response != null && request.getAttribute("uploaded") != null;
%>

<div class="row medium-8 large-7 columns">
    <div class="blog-post">
        <!--<img class="thumbnail" src="http://placehold.it/850x350">-->
        <p>ატვირთეთ ფაილი:</p>
        <div class="callout">
            <div class="roundbox sidebox" style="">
                <div class="roundbox-rt">&nbsp;</div>
                <div class="caption titled">
                    <div class="top-links">
                    </div>
                </div>

                <div
<% if (uploaded) { %>
                        style="pointer-events: none; opacity: 0.5;"
<%}%>
                >

                    <form method="post" action="UploadServlet"
                          enctype="multipart/form-data" class="submitForm">
                        <table class="table-form" style="width:100%;">
                            <tbody>
                            <tr>
                                <td class="field">კომპილატორი:</td>
                                <td>
                                    <label style="padding-top: 8px; padding-bottom: 0;">
                                        GNU GCC 5.1.0
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td class="field">აირჩიეთ ფაილი:</td>
                                <td>
                                    <input name="file" type="file" value="" style="padding-top: 10px">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div style="text-align: center;">
                                        <input style="width:10em;" class="submit" type="submit" value="Submit">
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>

                </div>
<%  if (uploadTried) {
        if (uploaded) { %>
                <div>
                    <h3 style="float: left;">გთხოვთ დაელოდოთ, მიმდინარეობს ტესტირება!</h3>
                    <img src="images/loading.gif" style="width: 60px; height: 40px; padding-left: 20px;">
                </div>

<%      } else {%>
                <h3>გთხოვთ ატვირთოთ .cpp გაფართოების ფაილი</h3>
<%      }
    }%>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
</body>
</html>
<%
    if (response != null && request.getAttribute("zaza") != null && (Boolean) request.getAttribute("zaza")) {
%>
<script>
    $.ajax({
        url: "TestServlet",
        type: 'POST',
        data: formData,
        success: function (data) {
            if (data == "usedusername") {
                buyerUserNameValidation(data);
            }
            else {
                $('#login-form').replaceWith(data);
                $('#myModal').css("display", "none");
            }
        },
        cache: false,
        contentType: false,
        processData: false
    })();
    var addEventsAndStuff;
    (addEventsAndStuff = function tmp(event) {
//        var formData = new FormData($(this)[0]);
        window.alert("zazaaa");
        $.ajax({
            url: "/TestServlet",
            type: 'POST',
            data: null,
            success: function (data) {
                window.alert("zazaaa");
            },
            cache: false,
            contentType: false,
            processData: false

        });
        return true;
    })();
</script>
<%}%>