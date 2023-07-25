package es.com.movertis.backend.lib.model.drivers.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGetCost {

    @JsonProperty(value = "init")
    private long initialDate;
    @JsonProperty(value = "end")
    private long endDate;
    private List<Integer> drivers;
    @JsonIgnore
    private Long unit;
}
