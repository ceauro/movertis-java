package es.com.movertis.backend.lib.model.drivers.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetHistoryUnitDriver {
    private Integer id;
    @JsonProperty(value = "initial_date")
    private Long initialDate;
    @JsonProperty(value = "end_date")
    private Long endDate;
}
