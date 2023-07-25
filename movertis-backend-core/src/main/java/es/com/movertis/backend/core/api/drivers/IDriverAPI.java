package es.com.movertis.backend.core.api.drivers;

import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetCost;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetHistoryUnitDriver;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverCostDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverDTO;
import es.com.movertis.backend.lib.model.drivers.service.request.RequestGetDrivers;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverUnitDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.SummaryDTO;

import java.io.IOException;
import java.util.List;

public interface IDriverAPI {
    List<DriverDTO> getDrivers(RequestGetDrivers request) throws IOException;

    List<DriverUnitDTO> getDriversUnits(RequestGetHistoryUnitDriver request);

    SummaryDTO getSummary(Integer driverId);

    List<DriverCostDTO> getDriverCosts(RequestGetCost request);
}
