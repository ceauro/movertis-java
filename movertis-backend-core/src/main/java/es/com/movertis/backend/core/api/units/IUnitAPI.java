package es.com.movertis.backend.core.api.units;

import es.com.movertis.backend.lib.model.units.service.request.RequestGetTotalKms;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;

public interface IUnitAPI {
    SummaryTripDTO getSummaryTrip(RequestGetTotalKms request);
}
