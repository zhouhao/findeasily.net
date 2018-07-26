<#include "layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Forget Password'/>
</#if>
<#assign inlineJs>
    <script>
        <#-- todo -->
        var forgetPasswordForm = $("#forget-password-form");
        forgetPasswordForm.submit(function (e) {
            $.ajax({
                type: forgetPasswordForm.attr('method'),
                url: forgetPasswordForm.attr('action'),
                data: forgetPasswordForm.serialize(), // serializes the form's elements.
                success: function () {
                    toastr.success(';-) Email with password reset instructions was sent successfully! Please check your email for instructions to reset your password');
                },
                error: function (data) {
                    toastr.error(data.responseJSON.errors.join(', '));
                }
            });

            e.preventDefault(); // avoid to execute the actual submit of the form.
        });
    </script>
</#assign>
<@layout>
<!--password forget form -->
    <div class="main-register-wrap main-register-single-page">
        <div class="main-register-holder">
            <div class="main-register fl-wrap">
                <h3>Forget Your Password?</h3>
                <div id="tabs-container">
                    <div id="tab-1" class="tab-content">
                        <div class="custom-form">
                            <form method="post" name="passwordForgetForm" action="/password/forget/handler" id="forget-password-form">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <label>Email Address * </label>
                                <input name="email" type="text" required autofocus value="">
                                <button type="submit" class="log-submit-btn"><span>Submit</span>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--register form end -->
</@layout>