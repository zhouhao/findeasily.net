<#include "layout/layout.ftl"/>
<#if !pageTitle?has_content>
    <#assign pageTitle = 'Oh! Error Happens!'/>
</#if>

<@layout>
    <!--  section  -->
    <section class="parallax-section" data-scrollax-parent="true" id="sec1">
        <div class="bg par-elem "  data-bg="images/bg/1.jpg" data-scrollax="properties: { translateY: '30%' }"></div>
        <div class="overlay"></div>
        <div class="bubble-bg"></div>
        <div class="container">
            <div class="error-wrap">
                <h2>404</h2>
                <p>We're sorry, but the Page you were looking for, couldn't be found.</p>
                <div class="clearfix"></div>
                <form action="#">
                    <input name="se" id="se" type="text" class="search" placeholder="Search.." value="Search...">
                    <button class="search-submit" id="submit_btn"><i class="fa fa-search transition"></i> </button>
                </form>
                <div class="clearfix"></div>
                <p>Or</p>
                <a href="/" class="btn  big-btn  color-bg flat-btn">Back to Home Page<i class="fa fa-angle-right"></i></a>
            </div>
        </div>
    </section>
    <!--  section  end-->
</@layout>