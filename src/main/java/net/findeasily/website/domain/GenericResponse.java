package net.findeasily.website.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {
    private Boolean ok;
    private String msg;
}
