package es.com.movertis.backend.lib.model.drivers.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDTO {
    private String actualActivity;
    private Long nextRest;
}
