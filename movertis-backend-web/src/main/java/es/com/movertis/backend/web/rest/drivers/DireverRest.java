package es.com.movertis.backend.web.rest.drivers;

import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetCost;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetHistoryUnitDriver;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverCostDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverUnitDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.SummaryDTO;
import es.com.movertis.backend.service.drivers.IDriversService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetDrivers;
import es.com.movertis.backend.lib.model.drivers.service.request.Flags;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverDTO;

import java.util.List;
import java.util.Collections;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/core/integration/driver", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DireverRest {

    private IDriversService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<DriverDTO>> getAllDrivers(){
        RequestGetDrivers request = new RequestGetDrivers();
        request.setId(Collections.emptyList());
        request.setFlags(new Flags(true));

        List<DriverDTO> response = service.getAllDrivers(request);
        if(Objects.nonNull(response)){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(404).body(Collections.emptyList());
    }

    @GetMapping(value = "/units")
    public ResponseEntity<List<DriverUnitDTO>> getAllDrivers(@RequestParam Integer id,
                                                         @RequestParam(value = "initial-date") Long initialDate,
                                                         @RequestParam(value = "end-date") Long endDate){
        if(Objects.isNull(id) || Objects.isNull(initialDate) || Objects.isNull(endDate)) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        RequestGetHistoryUnitDriver request = new RequestGetHistoryUnitDriver();
        request.setId(id);
        request.setInitialDate(initialDate);
        request.setEndDate(endDate);

        List<DriverUnitDTO> response = service.getDriversUnits(request);
        if(Objects.nonNull(response)){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(404).body(Collections.emptyList());
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<SummaryDTO> getSummary(@RequestParam Integer id){
        if(Objects.isNull(id)) {
            return ResponseEntity.badRequest().build();
        }

        SummaryDTO summary = service.getSummaryDriver(id);
        if(Objects.isNull(summary.getActualActivity())) summary.setActualActivity(Strings.EMPTY);
        return ResponseEntity.ok(summary);
    }

    @GetMapping(value = "/costs")
    public ResponseEntity<List<DriverCostDTO>> getCosts(@RequestParam Integer id,
                                                        @RequestParam(required = false) Long unit,
                                                        @RequestParam(value = "initial-date") Long initialDate,
                                                        @RequestParam(value = "end-date") Long endDate){
        if(Objects.isNull(id) || Objects.isNull(initialDate) || Objects.isNull(endDate)) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        RequestGetCost requestGetCost = new RequestGetCost();
        requestGetCost.setDrivers(List.of(id));
        requestGetCost.setInitialDate(initialDate);
        requestGetCost.setEndDate(endDate);
        if(Objects.nonNull(unit)) {
            requestGetCost.setUnit(unit);
        }

        List<DriverCostDTO> costs = service.getDriverCost(requestGetCost);
        return ResponseEntity.ok(costs);
    }
}
