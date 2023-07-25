package es.com.movertis.backend.lib.model.units.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryTripDTO {

    private int totalKms;
    private List<TripDTO> trips;
}
