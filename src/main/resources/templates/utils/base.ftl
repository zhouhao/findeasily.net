<#macro dropdownCategories name class="chosen-select" includeAll=true>
    <select name="${name}" id="${name}" class="${class}">
        <#if includeAll>
            <option>All</option>
        </#if>
        <option>Sub-Lease</option>
        <option>Long-term</option>
        <option>Temp</option>
        <option>Houses</option>
        <option>Apartment</option>
    </select>
</#macro>

<#macro requiredTextInput name placeholder="" class="">
    <input type="text" id="${name}" name="${name}" class="${class}" placeholder="${placeholder}" required/>
</#macro>
