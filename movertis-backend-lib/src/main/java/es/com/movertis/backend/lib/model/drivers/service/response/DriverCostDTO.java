package es.com.movertis.backend.lib.model.drivers.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverCostDTO {
    private String amount;
    private Long date;
    private Long unit;
    private String typeCost;
    private String description;
}
