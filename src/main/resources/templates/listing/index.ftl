<#import "/spring.ftl" as spring>
<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'My Listings'/>
</#if>
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
                        <div class="dashboard-list-box fl-wrap">
                            <div class="dashboard-header fl-wrap">
                                <h3>Listing Management</h3>
                            </div>
                            <!-- dashboard-list end-->
                            <div class="dashboard-list">
                                <div class="dashboard-message">
                                    <div class="dashboard-listing-table-image">
                                        <a href="#"><img src="/images/all/1.jpg" alt=""></a>
                                    </div>
                                    <div class="dashboard-listing-table-text">
                                        <h4><a href="#">Event In City Hall</a></h4>
                                        <span class="dashboard-listing-table-address"><i class="fa fa-map-marker"></i><a href="#">USA 27TH Brooklyn NY</a></span>
                                        <div class="listing-rating card-popup-rainingvis fl-wrap" data-starrating2="5">
                                            <span>(2 reviews)</span>
                                        </div>
                                        <ul class="dashboard-listing-table-opt  fl-wrap">
                                            <li><a href="#">Edit <i class="fa fa-pencil-square-o"></i></a></li>
                                            <li><a href="#" class="del-btn">Delete <i class="fa fa-trash-o"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- dashboard-list end-->
                            <!-- dashboard-list end-->
                            <div class="dashboard-list">
                                <div class="dashboard-message">
                                    <div class="dashboard-listing-table-image">
                                        <a href="#"><img src="/images/all/1.jpg" alt=""></a>
                                    </div>
                                    <div class="dashboard-listing-table-text">
                                        <h4><a href="#">Event In City Hall</a></h4>
                                        <span class="dashboard-listing-table-address"><i class="fa fa-map-marker"></i><a href="#">USA 27TH Brooklyn NY</a></span>
                                        <div class="listing-rating card-popup-rainingvis fl-wrap" data-starrating2="3">
                                            <span>(2 reviews)</span>
                                        </div>
                                        <ul class="dashboard-listing-table-opt  fl-wrap">
                                            <li><a href="#">Edit <i class="fa fa-pencil-square-o"></i></a></li>
                                            <li><a href="#" class="del-btn">Delete <i class="fa fa-trash-o"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- dashboard-list end-->
                            <!-- dashboard-list end-->
                            <div class="dashboard-list">
                                <div class="dashboard-message">
                                    <div class="dashboard-listing-table-image">
                                        <a href="#"><img src="/images/all/1.jpg" alt=""></a>
                                    </div>
                                    <div class="dashboard-listing-table-text">
                                        <h4><a href="#">Event In City Hall</a></h4>
                                        <span class="dashboard-listing-table-address"><i class="fa fa-map-marker"></i><a href="#">USA 27TH Brooklyn NY</a></span>
                                        <div class="listing-rating card-popup-rainingvis fl-wrap" data-starrating2="4">
                                            <span>(2 reviews)</span>
                                        </div>
                                        <ul class="dashboard-listing-table-opt  fl-wrap">
                                            <li><a href="#">Edit <i class="fa fa-pencil-square-o"></i></a></li>
                                            <li><a href="#" class="del-btn">Delete <i class="fa fa-trash-o"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- dashboard-list end-->
                            <!-- dashboard-list end-->
                            <div class="dashboard-list">
                                <div class="dashboard-message">
                                    <div class="dashboard-listing-table-image">
                                        <a href="#"><img src="/images/all/1.jpg" alt=""></a>
                                    </div>
                                    <div class="dashboard-listing-table-text">
                                        <h4><a href="#">Event In City Hall</a></h4>
                                        <span class="dashboard-listing-table-address"><i class="fa fa-map-marker"></i><a href="#">USA 27TH Brooklyn NY</a></span>
                                        <div class="listing-rating card-popup-rainingvis fl-wrap" data-starrating2="5">
                                            <span>(2 reviews)</span>
                                        </div>
                                        <ul class="dashboard-listing-table-opt  fl-wrap">
                                            <li><a href="#">Edit <i class="fa fa-pencil-square-o"></i></a></li>
                                            <li><a href="#" class="del-btn">Delete <i class="fa fa-trash-o"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- dashboard-list end-->
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