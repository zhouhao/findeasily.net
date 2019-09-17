<#macro dropdownCategories name class="chosen-select" value="" durationOnly=false includeAll=true>
    <select name="${name}" id="${name}" class="${class}">
        <#if includeAll>
            <option value="all" <#if value == 'all'>selected</#if>>All</option>
        </#if>
        <option value="sub-lease" <#if value == 'sub-lease'>selected</#if>>Sub-Lease</option>
        <option value="long-term" <#if value == 'long-term'>selected</#if>>Long-term</option>
        <option value="temp" <#if value == 'temp'>selected</#if>>Temp</option>
        <#if !durationOnly>
            <option value="house" <#if value == 'house'>selected</#if>>House</option>
            <option value="apt" <#if value == 'apt'>selected</#if>>Apartment</option>
        </#if>
    </select>
</#macro>

<#macro requiredTextInput name value="" placeholder="" class="">
    <input type="text" id="${name}" name="${name}" value="${value}" class="${class}" placeholder="${placeholder}" required/>
</#macro>
