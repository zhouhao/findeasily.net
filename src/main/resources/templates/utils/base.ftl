<#macro dropdownCategories name class="chosen-select" durationOnly=false includeAll=true>
    <select name="${name}" id="${name}" class="${class}">
        <#if includeAll>
            <option>All</option>
        </#if>
        <option>Sub-Lease</option>
        <option>Long-term</option>
        <option>Temp</option>
        <#if !durationOnly>
            <option>Houses</option>
            <option>Apartment</option>
        </#if>
    </select>
</#macro>

<#macro requiredTextInput name placeholder="" class="">
    <input type="text" id="${name}" name="${name}" class="${class}" placeholder="${placeholder}" required/>
</#macro>
