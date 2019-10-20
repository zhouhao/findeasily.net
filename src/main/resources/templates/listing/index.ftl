<#import "/spring.ftl" as spring>
<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'My Listings'/>
</#if>
<#assign inlineJs>
    <script>
        $('.del-btn').on('click', function () {
            var listingId = $(this).data('id');
            ajaxDelete(this, "Are you sure to delete this listing? This cannot be undone later",
                '/mgmt/listing/' + listingId, function () {
                    location.reload()
                });
        });
    </script>
</#assign>
<@layout>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
                        <div class="dashboard-list-box fl-wrap">
                            <div class="dashboard-header fl-wrap">
                                <h3>Listings Management</h3>
                            </div>
                            <#list listings as lst>
                                <!-- dashboard-list start-->
                                <div class="dashboard-list">
                                    <div class="dashboard-message">
                                        <div class="dashboard-listing-table-image">
                                            <a href="#"><img src="/images/all/1.jpg" alt=""></a>
                                        </div>
                                        <div class="dashboard-listing-table-text">
                                            <h4><a href="#">${lst.title}</a></h4>
                                            <span class="dashboard-listing-table-address"><i class="fa fa-map-marker"></i>
                                                <a href="#">${lst.address1},
                                                    <#if lst.address2?has_content>${lst.address2},</#if>
                                                    ${lst.city}, ${lst.state}, ${lst.zip}</a>
                                            </span>
                                            <ul class="dashboard-listing-table-opt  fl-wrap">
                                                <li><a href="/mgmt/listing/${lst.id}">Edit <i class="fa fa-pencil-square-o"></i></a></li>
                                                <li><a href="/mgmt/listing/${lst.id}/amenity-photo">Amenity & Photo <i class="fa fa-puzzle-piece"></i></a></li>
                                                <li><a href="javascript:void(0);" class="del-btn" data-id="${lst.id}">Delete <i class="fa fa-trash-o"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- dashboard-list end-->
                            </#list>
                        </div>
                        <!-- pagination-->
                        <div class="pagination">
                            <a href="#" class="prevposts-link"><i class="fa fa-caret-left"></i></a>
                            <a href="#">1</a>
                            <a href="#" class="current-page">2</a>
                            <a href="#">3</a>
                            <a href="#">4</a>
                            <a href="#" class="nextposts-link"><i class="fa fa-caret-right"></i></a>
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