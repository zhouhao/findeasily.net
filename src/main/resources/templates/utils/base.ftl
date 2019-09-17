<#macro dropdownCategories name class="chosen-select" value="" durationOnly=false includeAll=true>
    <select name="${name}" id="${name}" class="${class}">
        <#if includeAll>
            <option value="all">All</option>
        </#if>
        <option value="sub-lease">Sub-Lease</option>
        <option value="long-term">Long-term</option>
        <option value="temp">Temp</option>
        <#if !durationOnly>
            <option value="house">House</option>
            <option value="apt">Apartment</option>
        </#if>
    </select>
</#macro>

<#macro requiredTextInput name value="" placeholder="" class="">
    <input type="text" id="${name}" name="${name}" value="${value}" class="${class}" placeholder="${placeholder}" required/>
</#macro>
