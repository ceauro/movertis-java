package es.com.movertis.backend.lib.model.units.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private int kms;
    private String event;
}
