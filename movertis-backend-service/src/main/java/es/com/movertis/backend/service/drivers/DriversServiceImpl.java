package es.com.movertis.backend.service.drivers;

import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetCost;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetHistoryUnitDriver;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverCostDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverUnitDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.SummaryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import es.com.movertis.backend.core.api.drivers.IDriverAPI;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverDTO;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetDrivers;

@Service
@AllArgsConstructor
public class DriversServiceImpl implements IDriversService{

    private IDriverAPI api;
    @Override
    public List<DriverDTO> getAllDrivers(RequestGetDrivers request) {
        try{
           return api.getDrivers(request);
        } catch(IOException ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<DriverUnitDTO> getDriversUnits(RequestGetHistoryUnitDriver request) {
        return api.getDriversUnits(request);
    }

    @Override
    public SummaryDTO getSummaryDriver(Integer idDriver) {
        return api.getSummary(idDriver);
    }

    @Override
    public List<DriverCostDTO> getDriverCost(RequestGetCost cost) {
        return api.getDriverCosts(cost);
    }
}
