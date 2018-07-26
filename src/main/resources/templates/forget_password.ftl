<#include "layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Forget Password'/>
</#if>
<#assign inlineJs>
    <script>
        <#-- todo -->
        var form = $("#main-register-form");
        form.submit(function (e) {
            $.ajax({
                type: form.attr('method'),
                url: form.attr('action'),
                data: form.serialize(), // serializes the form's elements.
                success: function (data) {
                    toastr.success(';-) Register successfully! Please check your email to activate your account');
                    form.trigger('reset');
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
<!--register form -->
    <div class="main-register-wrap main-register-single-page">
        <div class="main-register-holder">
            <div class="main-register fl-wrap">
                <h3>Forget Your Password?</h3>
                <div id="tabs-container">
                    <div id="tab-1" class="tab-content">
                        <div class="custom-form">
                            <form method="post" name="passwordForgetForm" action="/password/forget/handler">
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