<#include "../layout/layout.ftl"/>
<#assign includeMap = true />
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Add Your New Listing'/>
</#if>
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
                        <div class="fixed-bar fl-wrap">
                            <div class="user-profile-menu-wrap fl-wrap">
                                <!-- user-profile-menu-->
                                <div class="user-profile-menu">
                                    <h3>Main</h3>
                                    <ul>
                                        <li><a href="dashboard.html"><i class="fa fa-gears"></i>Dashboard</a></li>
                                        <li><a href="dashboard-myprofile.html"><i class="fa fa-user-o"></i> Edit profile</a>
                                        </li>
                                        <li><a href="dashboard-messages.html"><i class="fa fa-envelope-o"></i> Messages
                                                <span>3</span></a></li>
                                        <li><a href="dashboard-password.html"><i class="fa fa-unlock-alt"></i>Change
                                                Password</a></li>
                                    </ul>
                                </div>
                                <!-- user-profile-menu end-->
                                <!-- user-profile-menu-->
                                <div class="user-profile-menu">
                                    <h3>Listings</h3>
                                    <ul>
                                        <li><a href="dashboard-listing-table.html"><i class="fa fa-th-list"></i> My
                                                listigs </a></li>
                                        <li><a href="dashboard-bookings.html"> <i class="fa fa-calendar-check-o"></i>
                                                Bookings <span>2</span></a></li>
                                        <li><a href="dashboard-review.html"><i class="fa fa-comments-o"></i> Reviews
                                            </a></li>
                                        <li><a href="dashboard-add-listing.html" class="user-profile-act"><i
                                                        class="fa fa-plus-square-o"></i> Add New</a></li>
                                    </ul>
                                </div>
                                <!-- user-profile-menu end-->
                                <a href="#" class="log-out-btn">Log Out</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <!-- profile-edit-container-->
                        <div class="profile-edit-container add-list-container">
                            <div class="profile-edit-header fl-wrap">
                                <h4>Basic Informations</h4>
                            </div>
                            <div class="custom-form">
                                <label>Listing Title <i class="fa fa-briefcase"></i></label>
                                <input type="text" placeholder="Name of your business" value=""/>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Category</label>
                                        <select data-placeholder="All Categories" class="chosen-select">
                                            <option>All Categories</option>
                                            <option>Shops</option>
                                            <option>Hotels</option>
                                            <option>Restaurants</option>
                                            <option>Fitness</option>
                                            <option>Events</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Keywords <i class="fa fa-key"></i></label>
                                        <input type="text" placeholder="Maximum 15, should be separated by commas"
                                               value=""/>
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
                                <label>Address<i class="fa fa-map-marker"></i></label>
                                <input type="text" placeholder="Address of your business" value=""/>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Latitude:<i class="fa fa-map-marker"></i></label>
                                        <input type="text" id="lat" placeholder="" value=""/>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Longitude:<i class="fa fa-map-marker"></i></label>
                                        <input type="text" id="long" placeholder="" value=""/>
                                    </div>
                                </div>
                                <div class="map-container">
                                    <div id="singleMap" data-latitude="40.7427837"
                                         data-longitude="-73.11445617675781"></div>
                                </div>
                                <label>Location</label>
                                <select data-placeholder="Location" class="chosen-select">
                                    <option>All Locations</option>
                                    <option>Bronx</option>
                                    <option>Brooklyn</option>
                                    <option>Manhattan</option>
                                    <option>Queens</option>
                                    <option>Staten Island</option>
                                </select>
                                <label>Phone<i class="fa fa-phone"></i></label>
                                <input type="text" placeholder="Your Phone" value=""/>
                                <label>Email<i class="fa fa-envelope-o"></i></label>
                                <input type="text" placeholder="Your Email" value=""/>
                                <label>Website<i class="fa fa-globe"></i></label>
                                <input type="text" placeholder="Your Website" value=""/>
                            </div>
                        </div>
                        <!-- profile-edit-container end-->
                        <!-- profile-edit-container-->
                        <div class="profile-edit-container add-list-container">
                            <div class="profile-edit-header fl-wrap">
                                <h4>Header Media</h4>
                            </div>
                            <div class="custom-form">
                                <div class="row">
                                    <!--col -->
                                    <div class="col-md-4">
                                        <div class="add-list-media-header">
                                            <label class="radio inline">
                                                <input type="radio" name="gender" checked>
                                                <span>Background image</span>
                                            </label>
                                        </div>
                                        <div class="add-list-media-wrap">
                                            <form class="fuzone">
                                                <div class="fu-text">
                                                    <span><i class="fa fa-picture-o"></i> Click here or drop files to upload</span>
                                                </div>
                                                <input type="file" class="upload">
                                            </form>
                                        </div>
                                    </div>
                                    <!--col end-->
                                    <!--col -->
                                    <div class="col-md-4">
                                        <div class="add-list-media-header">
                                            <label class="radio inline">
                                                <input type="radio" name="gender">
                                                <span>Carousel</span>
                                            </label>
                                        </div>
                                        <div class="add-list-media-wrap">
                                            <form class="fuzone">
                                                <div class="fu-text">
                                                    <span><i class="fa fa-picture-o"></i> Click here or drop files to upload</span>
                                                </div>
                                                <input type="file" class="upload">
                                            </form>
                                        </div>
                                    </div>
                                    <!--col end-->
                                    <!--col -->
                                    <div class="col-md-4">
                                        <div class="add-list-media-header">
                                            <label class="radio inline">
                                                <input type="radio" name="gender">
                                                <span>Video</span>
                                            </label>
                                        </div>
                                        <div class="add-list-media-wrap">
                                            <label>Youtube <i class="fa fa-youtube"></i></label>
                                            <input type="text" placeholder="https://www.youtube.com/" value=""/>
                                            <label>Vimeo <i class="fa fa-vimeo"></i></label>
                                            <input type="text" placeholder="https://vimeo.com/" value=""/>
                                            <div class="change-photo-btn">
                                                <div class="photoUpload">
                                                    <span><i class="fa fa-upload"></i> Upload Video</span>
                                                    <input type="file" class="upload">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--col end-->
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
                        <!-- profile-edit-container-->
                        <div class="profile-edit-container add-list-container">
                            <div class="profile-edit-header fl-wrap">
                                <h4>Content Widgets</h4>
                            </div>
                            <div class="custom-form">
                                <div class="row">
                                    <div class="col-md-4">
                                        <!-- act-widget-->
                                        <div class="act-widget fl-wrap">
                                            <div class="act-widget-header">
                                                <h4>1. Promo video</h4>
                                                <div class="onoffswitch">
                                                    <input type="checkbox" name="onoffswitch"
                                                           class="onoffswitch-checkbox" id="myonoffswitch5" checked>
                                                    <label class="onoffswitch-label" for="myonoffswitch5">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="add-list-media-wrap">
                                                <form class="fuzone">
                                                    <div class="fu-text">
                                                        <span><i class="fa fa-picture-o"></i> Click here or drop files to upload</span>
                                                    </div>
                                                    <input type="file" class="upload">
                                                </form>
                                                <label>Video url : <i class="fa fa-youtube"></i></label>
                                                <input type="text" placeholder="https://www.youtube.com/" value=""/>
                                            </div>
                                        </div>
                                        <!-- act-widget end-->
                                    </div>
                                    <div class="col-md-4">
                                        <!-- act-widget-->
                                        <div class="act-widget fl-wrap">
                                            <div class="act-widget-header">
                                                <h4>2. Gallery Thumbnails</h4>
                                                <div class="onoffswitch">
                                                    <input type="checkbox" name="onoffswitch"
                                                           class="onoffswitch-checkbox" id="myonoffswitch6" checked>
                                                    <label class="onoffswitch-label" for="myonoffswitch6">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="add-list-media-wrap">
                                                <form class="fuzone">
                                                    <div class="fu-text">
                                                        <span><i class="fa fa-picture-o"></i> Click here or drop files to upload</span>
                                                    </div>
                                                    <input type="file" class="upload">
                                                </form>
                                            </div>
                                        </div>
                                        <!-- act-widget end-->
                                    </div>
                                    <div class="col-md-4">
                                        <!-- act-widget-->
                                        <div class="act-widget fl-wrap">
                                            <div class="act-widget-header">
                                                <h4>3. Slider</h4>
                                                <div class="onoffswitch">
                                                    <input type="checkbox" name="onoffswitch"
                                                           class="onoffswitch-checkbox" id="myonoffswitch7">
                                                    <label class="onoffswitch-label" for="myonoffswitch7">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="add-list-media-wrap">
                                                <form class="fuzone">
                                                    <div class="fu-text">
                                                        <span><i class="fa fa-picture-o"></i> Click here or drop files to upload</span>
                                                    </div>
                                                    <input type="file" class="upload">
                                                </form>
                                            </div>
                                        </div>
                                        <!-- act-widget end-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- profile-edit-container end-->
                        <!-- profile-edit-container-->
                        <div class="profile-edit-container add-list-container">
                            <div class="profile-edit-header fl-wrap">
                                <h4>Sidebar Widgets</h4>
                            </div>
                            <div class="custom-form">
                                <!-- act-widget-->
                                <div class="act-widget fl-wrap">
                                    <div class="act-widget-header">
                                        <h4>1. Booking Form</h4>
                                        <div class="onoffswitch">
                                            <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                                   id="myonoffswitch" checked>
                                            <label class="onoffswitch-label" for="myonoffswitch">
                                                <span class="onoffswitch-inner"></span>
                                                <span class="onoffswitch-switch"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- act-widget end-->
                                <!-- act-widget-->
                                <div class="act-widget fl-wrap">
                                    <div class="act-widget-header">
                                        <h4>2. Event Counter</h4>
                                        <div class="onoffswitch">
                                            <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                                   id="myonoffswitch2">
                                            <label class="onoffswitch-label" for="myonoffswitch2">
                                                <span class="onoffswitch-inner"></span>
                                                <span class="onoffswitch-switch"></span>
                                            </label>
                                        </div>
                                    </div>
                                    <label>Event Date<i class="fa fa-calendar-o"></i></label>
                                    <input type="text" placeholder="Date Example : 09/12/2019" value=""/>
                                </div>
                                <!-- act-widget end-->
                                <!-- act-widget-->
                                <div class="act-widget fl-wrap">
                                    <div class="act-widget-header">
                                        <h4>3. Working Hours</h4>
                                        <div class="onoffswitch">
                                            <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                                   id="myonoffswitch3" checked>
                                            <label class="onoffswitch-label" for="myonoffswitch3">
                                                <span class="onoffswitch-inner"></span>
                                                <span class="onoffswitch-switch"></span>
                                            </label>
                                        </div>
                                    </div>
                                    <textarea cols="40" rows="3" placeholder="Details"></textarea>
                                </div>
                                <!-- act-widget end-->
                            </div>
                        </div>
                        <!-- profile-edit-container end-->
                        <!-- profile-edit-container-->
                        <div class="profile-edit-container">
                            <div class="profile-edit-header fl-wrap" style="margin-top:30px">
                                <h4>Socials</h4>
                            </div>
                            <div class="custom-form">
                                <label>Facebook <i class="fa fa-facebook"></i></label>
                                <input type="text" placeholder="https://www.facebook.com/" value=""/>
                                <label>Twitter<i class="fa fa-twitter"></i> </label>
                                <input type="text" placeholder="https://twitter.com/" value=""/>
                                <label>Vkontakte<i class="fa fa-vk"></i> </label>
                                <input type="text" placeholder="vk.com" value=""/>
                                <label> Whatsapp <i class="fa fa-whatsapp"></i> </label>
                                <input type="text" placeholder="https://www.whatsapp.com" value=""/>
                                <button class="btn  big-btn  color-bg flat-btn">Save Changes<i
                                            class="fa fa-angle-right"></i></button>
                            </div>
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
    <div class="limit-box fl-wrap"></div>
    <!--section -->
    <section class="gradient-bg">
        <div class="cirle-bg">
            <div class="bg" data-bg="images/bg/circle.png"></div>
        </div>
        <div class="container">
            <div class="join-wrap fl-wrap">
                <div class="row">
                    <div class="col-md-8">
                        <h3>Do You Have Questions ?</h3>
                        <p>Lorem ipsum dolor sit amet, harum dolor nec in, usu molestiae at no.</p>
                    </div>
                    <div class="col-md-4"><a href="contacts.html" class="join-wrap-btn">Get In Touch <i
                                    class="fa fa-envelope-o"></i></a></div>
                </div>
            </div>
        </div>
    </section>
    <!-- section end -->
</@layout>