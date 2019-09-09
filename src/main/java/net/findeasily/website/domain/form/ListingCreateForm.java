package net.findeasily.website.domain.form;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListingCreateForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @Min(0)
    private Integer monthlyRent;

    @Min(0)
    private Integer securityDeposit;

    @Min(0)
    private Integer bathroomCount;

    @Min(0)
    private Integer bedroomCount;

    @NotEmpty
    private String availableDate;
    // This field will be populated in validator
    private LocalDate availableDateTS;

    @Length(min = 4, max = 128)
    private String address1;

    private String address2;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String country;

    @NotEmpty
    private String zipcode;

    private Float latitude;
    private Float longitude;

    @NotEmpty
    private String contactType;
    @NotEmpty
    private String contactName;
    private String contactPhone;
    private String contactEmail;

    private String description;

}
