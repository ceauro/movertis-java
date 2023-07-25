package es.com.movertis.backend.service.drivers;

import java.util.List;

import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetCost;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetHistoryUnitDriver;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverCostDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverDTO;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetDrivers;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverUnitDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.SummaryDTO;

public interface IDriversService {

    List<DriverDTO> getAllDrivers(RequestGetDrivers request);
    List<DriverUnitDTO> getDriversUnits(RequestGetHistoryUnitDriver request);
    SummaryDTO getSummaryDriver(Integer idDriver);
    List<DriverCostDTO> getDriverCost(RequestGetCost cost);
}
