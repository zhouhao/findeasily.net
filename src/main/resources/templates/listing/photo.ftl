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

        img.listing-photo {
            width: 100%;
        }

        .listing-photo-delete {
            position: absolute;
            right: 20px;
            top: 5px;
            color: #e23d3d;
            font-size: large;
        }
    </style>
</#assign>
<#assign inlineJs>
    <script src="/vendor/dropzone/dropzone.js"></script>
    <script>
        $('.listing-photo-delete').on('click', function () {
            var confirmed = confirm("Are you sure to delete this photo? This cannot be undone later");
            if (confirmed) {
                console.log($(this).data('photo-id'));
            }
        });
    </script>
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
                                <#list photos as p>
                                    <div class="col-md-4">
                                        <img src="${fmInstance.getListingImage(p.path)}" alt="" class="listing-photo">
                                        <a href="javascript:void(0);" class="listing-photo-delete" data-photo-id="${p.id}"><i class="fa fa-trash"></i></a>
                                    </div>
                                </#list>
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