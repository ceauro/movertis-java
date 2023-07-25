package es.com.movertis.backend.core.api.drivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.com.movertis.backend.core.utils.Ctes;
import es.com.movertis.backend.core.utils.HttpUtils;
import es.com.movertis.backend.core.utils.Utils;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetCost;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetDrivers;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetHistoryUnitDriver;
import es.com.movertis.backend.lib.model.drivers.service.response.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class DriverAPIImpl implements IDriverAPI{

    private static final Logger logger = LogManager.getLogger(DriverAPIImpl.class);
    private final ObjectMapper om = new ObjectMapper();

    @Override
    public List<DriverDTO> getDrivers(RequestGetDrivers requestDrivers) throws IOException {

        try{
            String request = om.writeValueAsString(requestDrivers);
            logger.info("GET DRIVER --> Request: {}", request);
            String response = HttpUtils.post(Ctes.Driver.GET_DIRVERS_URL, Ctes.Security.TOKEN, request);
            logger.info("GET DRIVER --> Response: {}", response);
            return om.readValue(response, new TypeReference<List<DriverDTO>>(){});
        } catch (Exception ex){
            logger.error("Error in Get Drivers", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public List<DriverUnitDTO> getDriversUnits(RequestGetHistoryUnitDriver requestUnitDriver) {
        Set<Integer> driverUnits;

        try{
            String request = om.writeValueAsString(requestUnitDriver);
            logger.info("GET HISTORY UNIT DRIVER --> Request: {}", request);
            String response = HttpUtils.post(Ctes.Driver.GET_UNIT_DRIVERS_URL, Ctes.Security.TOKEN, request);
            logger.info("GET HISTORY UNIT DRIVER --> Response: {}", response);
            driverUnits = new HashSet<>();
            List<DriverUnit> units = om.readValue(response, new TypeReference<>(){});
            units.forEach(unit -> driverUnits.add(unit.getUnitId()));
            return driverUnits.stream().map(DriverUnitDTO::new).toList();
        } catch (Exception ex){
            logger.error("Error in Get Drivers Units", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public SummaryDTO getSummary(Integer driverId) {
        if(Objects.isNull(driverId)){
            return null;
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", driverId);
        logger.info("GET SUMMARY DRIVER --> Driver Id: {}", driverId);
        String response = HttpUtils.get(Ctes.Driver.GET_SUMMARY_DRIVER_URL,Ctes.Security.TOKEN, parameters);
        logger.info("GET HISTORY UNIT DRIVER --> Response: {}", response);

        return Utils.getSummaryFromJson(response);
    }

    @Override
    public List<DriverCostDTO> getDriverCosts(RequestGetCost requestCosts) {
        if(Objects.isNull(requestCosts)){
            return Collections.emptyList();
        }

        try {
            String request = om.writeValueAsString(requestCosts);
            logger.info("GET DRIVER COSTS --> Request: {}", request);
            String response = HttpUtils.post(Ctes.Driver.GET_COSTS_DRIVER_URL, Ctes.Security.TOKEN, request);
            logger.info("GET DRIVER COSTS --> Response: {}", response);
            List<DriverCostDTO> allCosts = Utils.getDriverCostsFromJson(response);

            if(Objects.isNull(requestCosts.getUnit())){
                return allCosts;
            }

            return allCosts.stream()
                    .filter(cost -> cost.getUnit().equals(requestCosts.getUnit()))
                    .toList();
        } catch (JsonProcessingException ex){
            logger.error("Error in Get Drivers Costs", ex);
            return Collections.emptyList();
        }

    }


}
