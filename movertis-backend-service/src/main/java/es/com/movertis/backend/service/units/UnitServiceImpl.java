package es.com.movertis.backend.service.units;

import es.com.movertis.backend.core.api.units.IUnitAPI;
import es.com.movertis.backend.lib.model.units.service.request.RequestGetTotalKms;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnitServiceImpl implements IUnitService{

    private IUnitAPI api;

    @Override
    public SummaryTripDTO getSummaryTrip(RequestGetTotalKms request) {
        return api.getSummaryTrip(request);
    }
}
