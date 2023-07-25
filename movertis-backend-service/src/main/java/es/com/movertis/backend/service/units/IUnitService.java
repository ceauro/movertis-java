package es.com.movertis.backend.service.units;

import es.com.movertis.backend.lib.model.units.service.request.RequestGetTotalKms;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;

public interface IUnitService {

    SummaryTripDTO getSummaryTrip(RequestGetTotalKms request);
}
