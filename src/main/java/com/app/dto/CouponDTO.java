package com.app.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponDTO {
	@JsonProperty(access = Access.READ_ONLY) // used during serialization
    private Long id;
    private String code;
    private LocalDate expiryDate;

}
