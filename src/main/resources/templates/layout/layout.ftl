<#macro layout>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle!'Find House Rent Easily'}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="robots" content="index, follow"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content="findeasily team">
    <!--=============== css  ===============-->
    <link type="text/css" rel="stylesheet" href="/css/reset.css">
    <link type="text/css" rel="stylesheet" href="/css/plugins.css">
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <link type="text/css" rel="stylesheet" href="/css/color.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css"/>
    <!--=============== favicons ===============-->
    <link rel="shortcut icon" href="/images/favicon.png">
    ${inlineCss!""}
</head>
<body>
<!--loader-->
<div class="loader-wrap">
    <div class="pin"></div>
    <div class="pulse"></div>
</div>
<!--loader end-->
<!-- Main  -->
<div id="main">
    <#include "header.ftl">
    <!-- wrapper -->
    <div id="wrapper">
        <!-- Content-->
        <div class="content">
            <#nested>
        </div>
        <!-- Content end -->
    </div>
    <!-- wrapper end -->
    <#include "footer.ftl">
    <a class="to-top"><i class="fa fa-angle-up"></i></a>
</div>
<!-- Main end -->
<!--=============== scripts  ===============-->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="/js/plugins.js"></script>
<script type="text/javascript" src="/js/scripts.js"></script>
<#if includeMap!false>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8lHMqT3f4RbfwD9aoQrGOCjAqeR4mwCc&libraries=places&callback=initAutocomplete"></script>
<script type="text/javascript" src="/js/map_infobox.js"></script>
<script type="text/javascript" src="/js/markerclusterer.js"></script>
<script type="text/javascript" src="/js/maps.js"></script>
</#if>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script>${toastr!''}</script>
${inlineJs!""}
</body>
</html>
</#macro>