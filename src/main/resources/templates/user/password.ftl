<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Change Password'/>
</#if>

<@layout>
    <!--section -->
    <section>
        <!-- container -->
        <div class="container">
            <!-- profile-edit-wrap -->
            <div class="profile-edit-wrap">
                <div class="profile-edit-page-header">
                    <h2>Edit profile</h2>
                    <div class="breadcrumbs"><a href="/">Home</a><a href="#">Dashboard</a><span>Change Password</span></div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <#include "../layout/dashboard-left-menu.ftl">
                    </div>
                    <div class="col-md-9">
                        <!-- profile-edit-container-->
                        <div class="profile-edit-container">
                            <div class="profile-edit-header fl-wrap" style="margin-top:30px">
                                <h4>Change Password</h4>
                            </div>
                            <form class="custom-form no-icons" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="pass-input-wrap fl-wrap">
                                    <label>Current Password</label>
                                    <input type="password" class="pass-input" name="current-password" required minlength="6"/>
                                    <span class="eye"><i class="fa fa-eye" aria-hidden="true"></i> </span>
                                </div>
                                <div class="pass-input-wrap fl-wrap">
                                    <label>New Password</label>
                                    <input type="password" class="pass-input" name="new-password" required minlength="6"/>
                                    <span class="eye"><i class="fa fa-eye" aria-hidden="true"></i> </span>
                                </div>
                                <div class="pass-input-wrap fl-wrap">
                                    <label>Confirm New Password</label>
                                    <input type="password" class="pass-input" name="repeated-password" required minlength="6"/>
                                    <span class="eye"><i class="fa fa-eye" aria-hidden="true"></i> </span>
                                </div>
                                <button class="btn  big-btn  color-bg flat-btn">Save Changes<i class="fa fa-angle-right"></i></button>
                            </form>
                        </div>
                        <!-- profile-edit-container end-->
                    </div>
                </div>
            </div>
            <!--profile-edit-wrap end -->
        </div>
        <!--container end -->
    </section>
    <!-- section end -->
</@layout>