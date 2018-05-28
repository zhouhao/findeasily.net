<#include "layout/layout.ftl"/>
<#assign pageTitle = 'Login'/>
<#assign showHeaderLogin = false />
<@layout>
<!--register form -->
    <div class="main-register-wrap main-register-single-page">
        <div class="main-register-holder">
            <div class="main-register fl-wrap">
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
                                    <form method="post" role="form" name="form" class="main-register-form"
                                          action="/signup"
                                          id="main-register-form2">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <label>Username * </label>
                                        <input name="username" type="text" onClick="this.select()" value="" required>
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
</@layout>