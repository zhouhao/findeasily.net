<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'User Profile'/>
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
                    <div class="breadcrumbs"><a href="#">Home</a><a href="#">Dasboard</a><span>Edit profile</span></div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <#include "../layout/dashboard-left-menu.ftl">
                    </div>
                    <form method="post" enctype="multipart/form-data">
                        <div class="col-md-7">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <!-- profile-edit-container-->
                            <div class="profile-edit-container">
                                <div class="profile-edit-header fl-wrap">
                                    <h4>My Account</h4>
                                </div>
                                <div class="custom-form">
                                    <label>Your Name <i class="fa fa-user-o"></i></label>
                                    <input type="text" value="${currentUser.user.username!''}" disabled/>
                                    <label>Email Address<i class="fa fa-envelope-o"></i> </label>
                                    <input type="text" value="${currentUser.user.email!''}" disabled/>
                                    <label> Notes</label>
                                    <textarea cols="40" rows="3" placeholder="About Me" name="self-introduction">${user_ext.description!''}</textarea>
                                    <button class="btn  big-btn  color-bg flat-btn">Save Changes<i class="fa fa-angle-right"></i></button>
                                </div>
                            </div>
                            <!-- profile-edit-container end-->
                        </div>
                        <div class="col-md-2">
                            <div class="edit-profile-photo fl-wrap">
                                <img src="${avatar!'/images/avatar/1.jpg'}" class="respimg" alt="">
                                <div class="change-photo-btn">
                                    <div class="photoUpload">
                                        <span><i class="fa fa-upload"></i> Upload Photo</span>
                                        <input type="file" name="file" accept=".png, .jpg, .jpeg" class="upload">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!--profile-edit-wrap end -->
        </div>
        <!--container end -->
    </section>
    <!-- section end -->
</@layout>