<#import "/spring.ftl" as spring>
<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Upload Photos'/>
</#if>
<#assign inlineCss>
    <link rel="stylesheet" href="/vendor/dropzone/dropzone.css">
    <style>
        .dropzone {
            border: 2px dashed #0087F7;
            border-radius: 5px;
            background: white;
            min-height: 150px;
            padding: 54px 54px;
        }
    </style>
</#assign>
<#assign inlineJs>
    <script src="/vendor/dropzone/dropzone.js"></script>
</#assign>
<@layout>
    <!--section -->
    <section id="sec1">
        <!-- container -->
        <div class="container">
            <!-- profile-edit-wrap -->
            <div class="profile-edit-wrap">
                <div class="profile-edit-page-header">
                    <h2>Listing Management</h2>
                    <div class="breadcrumbs"><a href="#">Home</a><a href="#">Dashboard</a><span>My Listings</span></div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <#include "../layout/dashboard-left-menu.ftl">
                    </div>
                    <div class="col-md-9">
                        <div class="profile-edit-container add-list-container">
                            <div class="profile-edit-header fl-wrap">
                                <h4>Photo Management</h4>
                            </div>
                            <div class="row">
                                <!--col -->
                                <div class="col-md-12">
                                    <div class="add-list-media-wrap">
                                        <form class="fuzone dropzone" action="/mgmt/listing/${id}/photo" enctype="multipart/form-data">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <div class="dz-message needsclick">
                                                Drop files here or click to upload.
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!--col end-->
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!--profile-edit-wrap end -->
        </div>
        <!--container end -->
    </section>
    <!-- section end -->
</@layout>