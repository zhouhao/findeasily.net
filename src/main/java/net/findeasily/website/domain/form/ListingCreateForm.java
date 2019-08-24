package net.findeasily.website.domain.form;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingCreateForm {
    // TODO
    @Length(min = 4, max = 128)
    private String address1;


}
