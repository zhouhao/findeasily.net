<!-- header-->
<header class="main-header dark-header fs-header sticky">
    <div class="header-inner">
        <div class="logo-holder">
            <a href="/"><img src="/images/logo.png" alt=""></a>
        </div>
        <div class="header-search vis-header-search">
            <div class="header-search-input-item">
                <input type="text" placeholder="Keywords" value=""/>
            </div>
            <div class="header-search-select-item">
                <@BaseUtils.dropdownCategories name="category" />
            </div>
            <button class="header-search-button">Search</button>
        </div>
        <div class="show-search-button"><i class="fa fa-search"></i> <span>Search</span></div>
        <a href="/mgmt/listing/new" class="add-list">Add Listing <span><i class="fa fa-plus"></i></span></a>
        <#if showHeaderLogin!true>
            <#if !currentUser??>
                <div class="show-reg-form modal-open" id="header-signin-btn"><i class="fa fa-sign-in"></i>Sign In</div>
            <#else>
                <div class="header-user-menu">
                    <div class="header-user-name">
                        <span><img src="${currentUser.avatar!'/images/avatar/1.jpg'}" alt="" title=""></span>Hello
                        , ${currentUser.user.username!''}
                    </div>
                    <ul>
                        <li><a href="/user"> Edit profile</a></li>
                        <li><a href="/mgmt/listings">My Listings </a></li>
                        <li><a href="/mgmt/listing/new">Add Listing</a></li>
                        <li><a href="#"> Bookings </a></li>
                        <li><a href="#"> Reviews </a></li>
                        <li><a href="#" id="logout-header">Log Out</a></li>
                    </ul>
                </div>
                <form action="/logout" method="post" class="hide" id="logout-header-form">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </#if>
        </#if>
        <!-- nav-button-wrap-->
        <div class="nav-button-wrap color-bg">
            <div class="nav-button">
                <span></span><span></span><span></span>
            </div>
        </div>
        <!-- nav-button-wrap end-->
        <!--  navigation -->
        <div class="nav-holder main-menu">
            <nav>
                <ul>
                    <li>
                        <a href="#" class="act-link">Home</a>
                    </li>
                    <li>
                        <a href="#">Listings <i class="fa fa-caret-down"></i></a>
                        <!--second level -->
                        <ul>
                            <li><a href="#">Placeholder</a></li>
                            <li>
                                <a href="#">Placeholder <i class="fa fa-caret-down"></i></a>
                                <!--third  level  -->
                                <ul>
                                    <li><a href="#">Placeholder 1</a></li>
                                    <li><a href="#">Placeholder 2</a></li>
                                </ul>
                                <!--third  level end-->
                            </li>
                        </ul>
                        <!--second level end-->
                    </li>
                </ul>
            </nav>
        </div>
        <!-- navigation  end -->
    </div>
</header>
<!--  header end -->