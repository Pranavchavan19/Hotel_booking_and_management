package com.swaraj.swarajhotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

public class BookingDTO {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
}
