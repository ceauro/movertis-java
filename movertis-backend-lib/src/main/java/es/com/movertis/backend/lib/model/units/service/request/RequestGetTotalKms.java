package es.com.movertis.backend.lib.model.units.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGetTotalKms {
    @JsonProperty(value = "unit")
    private Long unitId;
    @JsonProperty(value = "init")
    private Long initialDate;
    @JsonProperty(value = "end")
    private Long finalDate;
}
