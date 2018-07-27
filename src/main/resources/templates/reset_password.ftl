<#include "layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Reset Password'/>
</#if>

<#assign inlineJs>
    <script>
        var resetPasswordForm = $("#reset-password-form");
        <#-- todo -->
        // resetPasswordForm.elements["userId"].value = userId;
        resetPasswordForm.submit(function (e) {
            $.ajax({
                type: resetPasswordForm.attr('method'),
                url: resetPasswordForm.attr('action'),
                data: resetPasswordForm.serialize(), // serializes the form's elements.
                success: function () {
                    toastr.success(';-) Password has been reset successfully! Please log in with your new password');
                },
                error: function (data) {
                    toastr.error(data.responseJSON.errors.join(', '));
                    resetPasswordForm.trigger("reset");
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
                <h3>Reset Your Password</h3>
                <div id="tabs-container">
                    <div id="tab-1" class="tab-content">
                        <div class="custom-form">
                            <form method="post" name="passwordResetForm" action="/password/reset/handler"
                                  id="reset-password-form">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <label>New Password *</label>
                                <input name="password" type="password" onClick="this.select()" value=""
                                       required>
                                <label>New Password Repeat *</label>
                                <input name="passwordRepeated" type="password" onClick="this.select()" value=""
                                       required>
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