<#import "/spring.ftl" as spring>
<#include "../layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Add Your New Listing'/>
</#if>
<#assign inlineCss>
    <link rel="stylesheet" href="/vendor/simditor/simditor.css"/>
    <style>
        .simditor-body {
            text-align: left;
        }
    </style>
</#assign>
<#assign inlineJs>
    <script src="/vendor/simditor/module.js"></script>
    <script src="/vendor/simditor/hotkeys.js"></script>
    <script src="/vendor/simditor/simditor.js"></script>
    <script>
        Simditor.locale = 'en-US';
        var editor = new Simditor({
            textarea: $('#description'),
            toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', 'link', '|', 'indent', 'outdent', 'alignment']
            //optional options
        });
        var placeSearch, autocomplete;
        var componentForm = {
            street_number: 'short_name',
            route: 'long_name',
            locality: 'long_name',
            administrative_area_level_1: 'short_name',
            country: 'long_name',
            postal_code: 'short_name'
        };
        var elMap = {
            address1: ['street_number', 'route'],
            city: ['locality'],
            state: ['administrative_area_level_1'],
            country: ['country'],
            zipcode: ['postal_code']
        };

        function initAutocomplete() {
            autocomplete = new google.maps.places.Autocomplete(document.getElementById('autocomplete'), { types: ['geocode'] });
            autocomplete.setFields(['address_component', 'geometry']);
            autocomplete.addListener('place_changed', fillInAddress);
        }

        function fillInAddress() {
            var place = autocomplete.getPlace();
            var dict = {};
            for (var i = 0; i < place.address_components.length; i++) {
                var addressType = place.address_components[i].types[0];
                if (componentForm[addressType]) {
                    dict[addressType] = place.address_components[i][componentForm[addressType]];
                }
            }
            for (var el in elMap) {
                var val = '';
                for (var v in elMap[el]) {
                    val += dict[elMap[el][v]] + ' ';
                }
                document.getElementById(el).value = val.trim();
            }

            $('#longitude').val(place.geometry.location.lng);
            $('#latitude').val(place.geometry.location.lat);
            singleMap();
        }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=${google_map_api_key}&libraries=places&callback=initAutocomplete"></script>
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
                        <form id="form" name="form" method="post" action="/mgmt/listing">
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
                                            <label for="availableDate">Date Available<i class="fa fa-calendar"></i></label>
                                            <input type="date" id="availableDate" name="availableDate" required placeholder="Available Date"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="monthlyRent">Monthly Rent<i class="fa fa-dollar"></i></label>
                                            <input type="number" id="monthlyRent" name="monthlyRent" required placeholder="Monthly Rent"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="securityDeposit">Security Deposit<i class="fa fa-dollar"></i></label>
                                            <input type="number" id="securityDeposit" name="securityDeposit" required placeholder="Security Deposit"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="bedroomCount">Bedroom Count<i class="fa fa-bed"></i></label>
                                            <input type="number" id="bedroomCount" name="bedroomCount" required/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="bathroomCount">Bathroom Count<i class="fa fa-bath"></i></label>
                                            <input type="number" id="bathroomCount" name="bathroomCount" required/>
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
                                    <label for="autocomplete">Full Address<i class="fa fa-map-marker"></i></label>
                                    <input id="autocomplete" placeholder="Enter your address" type="text"/>
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
                                    <input type="hidden" id="latitude" name="latitude" value="40.7427837"/>
                                    <input type="hidden" id="longitude" name="longitude" value="-73.11445617675781"/>
                                    <div class="map-container">
                                        <div id="singleMap"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="contactType">Contact Type</label>
                                            <select id="contactType" name="contactType" class="chosen-select">
                                                <option value="owner">Property Owner</option>
                                                <option value="agent">Management Company / Broker</option>
                                                <option value="tenant">Tenant</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <label>Contact Name<i class="fa fa-user"></i></label>
                                            <@BaseUtils.requiredTextInput name="contactName" placeholder="Contact Name"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Contact Phone<i class="fa fa-phone"></i></label>
                                            <@BaseUtils.requiredTextInput name="contactPhone" placeholder="Contact Phone"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label>Contact Email<i class="fa fa-envelope-o"></i></label>
                                            <@BaseUtils.requiredTextInput name="contactEmail" placeholder="Contact Email"/>
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
                                    <label for="description">Description</label>
                                    <textarea cols="40" rows="3" id="description" name="description"></textarea>
                                </div>
                            </div>
                            <!-- profile-edit-container end-->
                            <div class="profile-edit-container">
                                <div class="custom-form">
                                    <button class="btn big-btn color-bg flat-btn" type="submit">
                                        Save & Continue<i class="fa fa-angle-right"></i>
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