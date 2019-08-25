<#import "/spring.ftl" as spring>
<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Add Your New Listing'/>
</#if>
<#assign inlineJs>
    <script src="https://maps.googleapis.com/maps/api/js?key=${google_map_api_key}"></script>
    <script type="text/javascript" src="/js/map-add.js"></script>
</#assign>
<@layout>
    <!--section -->
    <section id="sec1">
        <!-- container -->
        <div class="container">
            <!-- profile-edit-wrap -->
            <div class="profile-edit-wrap">
                <div class="profile-edit-page-header">
                    <h2>Add Listing</h2>
                    <div class="breadcrumbs"><a href="#">Home</a><a href="#">Dashboard</a><span>Add Listing</span></div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <#include "../layout/dashboard-left-menu.ftl">
                    </div>
                    <div class="col-md-9">
                        <form id="form" name="form" method="post">
                            <!-- profile-edit-container-->
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="profile-edit-container add-list-container">
                                <div class="profile-edit-header fl-wrap">
                                    <h4>Basic Information</h4>
                                </div>
                                <div class="custom-form">
                                    <label for="title">Listing Title <i class="fa fa-briefcase"></i></label>
                                    <input type="text" name="title" id="title" placeholder="Name of your listing"/>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="category">Category</label>
                                            <@BaseUtils.dropdownCategories name="category" durationOnly=true includeAll=false />
                                        </div>
                                        <div class="col-md-6">
                                            <label for="rent_price">Date Available<i class="fa fa-calendar"></i></label>
                                            <input type="date" id="available_date" name="available_date" required placeholder="Available Date"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="rent_price">Monthly Rent<i class="fa fa-dollar"></i></label>
                                            <input type="number" id="rent_price" name="rent_price" required placeholder="Monthly Rent"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="rent_price">Security Deposit<i class="fa fa-dollar"></i></label>
                                            <input type="number" id="security_deposit" name="security_deposit" required placeholder="Security Deposit"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="rent_price">Bedroom Count<i class="fa fa-bed"></i></label>
                                            <input type="number" id="bedroom_count" name="bedroom_count" required/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="rent_price">Bathroom Count<i class="fa fa-bath"></i></label>
                                            <input type="number" id="bathroom_count" name="bathroom_count" required/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- profile-edit-container end-->
                            <!-- profile-edit-container-->
                            <div class="profile-edit-container add-list-container">
                                <div class="profile-edit-header fl-wrap">
                                    <h4>Location / Contacts</h4>
                                </div>
                                <div class="custom-form">
                                    <label for="address1">Address Line 1<i class="fa fa-map-marker"></i></label>
                                    <@BaseUtils.requiredTextInput name="address1"/>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Address Line 2<i class="fa fa-map-marker"></i></label>
                                            <@BaseUtils.requiredTextInput name="address2"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label>Zip Code:<i class="fa fa-map-marker"></i></label>
                                            <@BaseUtils.requiredTextInput name="zipcode"/>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>City:<i class="fa fa-map-marker"></i></label>
                                            <@BaseUtils.requiredTextInput name="city"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label>State:<i class="fa fa-map-marker"></i></label>
                                            <@BaseUtils.requiredTextInput name="state"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label>Country:<i class="fa fa-map-marker"></i></label>
                                            <@BaseUtils.requiredTextInput name="country"/>
                                        </div>
                                    </div>
                                    <input type="hidden" id="latitude"/>
                                    <input type="hidden" id="longitude"/>
                                    <div class="map-container">
                                        <div id="singleMap" data-latitude="40.7427837" data-longitude="-73.11445617675781"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Phone<i class="fa fa-phone"></i></label>
                                            <@BaseUtils.requiredTextInput name="contact_phone" placeholder="Contact Phone"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label>Email<i class="fa fa-envelope-o"></i></label>
                                            <@BaseUtils.requiredTextInput name="contact_email" placeholder="Contact Email"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- profile-edit-container end-->
                            <!-- profile-edit-container-->
                            <div class="profile-edit-container add-list-container">
                                <div class="profile-edit-header fl-wrap">
                                    <h4>Detailed Information</h4>
                                </div>
                                <div class="custom-form">
                                    <label>Description</label>
                                    <textarea cols="40" rows="3" placeholder=""></textarea>
                                    <!-- Checkboxes -->
                                    <div class=" fl-wrap filter-tags">
                                        <h4>Amenities </h4>
                                        <input id="check-a" type="checkbox" name="check">
                                        <label for="check-a">Elevator in building</label>
                                        <input id="check-b" type="checkbox" name="check">
                                        <label for="check-b">Friendly workspace</label>
                                        <input id="check-c" type="checkbox" name="check">
                                        <label for="check-c">Instant Book</label>
                                        <input id="check-d" type="checkbox" name="check">
                                        <label for="check-d">Wireless Internet</label>
                                    </div>
                                </div>
                            </div>
                            <!-- profile-edit-container end-->
                            <div class="profile-edit-container">
                                <div class="custom-form">
                                    <button class="btn big-btn color-bg flat-btn" type="submit">
                                        Save Changes<i class="fa fa-angle-right"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--profile-edit-wrap end -->
        </div>
        <!--container end -->
    </section>
    <!-- section end -->
</@layout>

<@spring.bind "form" />
<#if spring.status.error>
    <ul>
        <#list spring.status.errorMessages as error>
            <li>${error}</li>
        </#list>
    </ul>
</#if>