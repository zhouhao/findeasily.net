<#macro dropdownCategories name class="chosen-select">
    <select name="${name}" id="${name}" class="${class}">
        <option>All</option>
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
