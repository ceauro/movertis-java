package es.com.movertis.backend.web.rest.units;

import es.com.movertis.backend.lib.model.units.service.request.RequestGetTotalKms;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;
import es.com.movertis.backend.service.units.IUnitService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/core/integration/unit", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UnitsRest {

    private IUnitService service;

    @GetMapping(value = "/kms")
    public ResponseEntity<SummaryTripDTO> getKilometers(@RequestParam Long id,
                                        @RequestParam(value = "initial-date") Long initialDate,
                                        @RequestParam(value = "end-date") Long endDate){
        RequestGetTotalKms kms = new RequestGetTotalKms();
        kms.setUnitId(id);
        kms.setInitialDate(initialDate);
        kms.setFinalDate(endDate);

        SummaryTripDTO response = service.getSummaryTrip(kms);

        if(Objects.isNull(response)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}
