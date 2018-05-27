<#macro layout css=[] js=[] other=[]>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <!--=============== basic  ===============-->
    <meta charset="UTF-8">
    <title>${pageTitle!'Find House Rent Easily'}</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="robots" content="index, follow"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content="Hao Zhou">
    <!--=============== css  ===============-->
    <link type="text/css" rel="stylesheet" href="css/reset.css">
    <link type="text/css" rel="stylesheet" href="css/plugins.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/color.css">
    <#list css as path>
    <link rel="stylesheet" type="text/css" href="${path}"/>
    </#list>
    <!--=============== favicons ===============-->
    <link rel="shortcut icon" href="images/favicon.ico">
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
    <!--register form -->
    <div class="main-register-wrap modal">
        <div class="main-overlay"></div>
        <div class="main-register-holder">
            <div class="main-register fl-wrap">
                <div class="close-reg"><i class="fa fa-times"></i></div>
                <h3>Sign In <span>City<strong>Book</strong></span></h3>
                <div id="tabs-container">
                    <ul class="tabs-menu">
                        <li class="current"><a href="#tab-1">Login</a></li>
                        <li><a href="#tab-2">Register</a></li>
                    </ul>
                    <div class="tab">
                        <div id="tab-1" class="tab-content">
                            <div class="custom-form">
                                <form method="post" name="registerform" action="/login">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <label>Email Address * </label>
                                    <input name="email" type="text" onClick="this.select()" required autofocus value="">
                                    <label>Password * </label>
                                    <input name="password" type="password" onClick="this.select()" required value="">
                                    <button type="submit" class="log-submit-btn"><span>Log In</span></button>
                                    <div class="clearfix"></div>
                                    <div class="filter-tags">
                                        <input type="checkbox" name="remember-me" id="remember-me">
                                        <label for="check-a">Remember me</label>
                                    </div>
                                </form>
                                <div class="lost_password">
                                    <a href="#">Lost Your Password?</a>
                                </div>
                            </div>
                        </div>
                        <div class="tab">
                            <div id="tab-2" class="tab-content">
                                <div class="custom-form">
                                    <form method="post" name="registerform" class="main-register-form"
                                          id="main-register-form2">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <label>Username * </label>
                                        <input name="name" type="text" onClick="this.select()" value="" required>
                                        <label>Email Address *</label>
                                        <input name="email" type="text" onClick="this.select()" value="" required>
                                        <label>Password *</label>
                                        <input name="password" type="password" onClick="this.select()" value=""
                                               required>
                                        <label>Password Repeat *</label>
                                        <input name="passwordRepeated" type="password" onClick="this.select()" value=""
                                               required>
                                        <button type="submit" class="log-submit-btn"><span>Register</span></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--register form end -->
    <a class="to-top"><i class="fa fa-angle-up"></i></a>
</div>
<!-- Main end -->
<!--=============== scripts  ===============-->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="js/plugins.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8lHMqT3f4RbfwD9aoQrGOCjAqeR4mwCc&libraries=places&callback=initAutocomplete"></script>
<script type="text/javascript" src="js/map_infobox.js"></script>
<script type="text/javascript" src="js/markerclusterer.js"></script>
<script type="text/javascript" src="js/maps.js"></script>
<#list js as path>
<script src="${path}"></script>
</#list>
${inlineJs!""}
</body>
</html>
</#macro>