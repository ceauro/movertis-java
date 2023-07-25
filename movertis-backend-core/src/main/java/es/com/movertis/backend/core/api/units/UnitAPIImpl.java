package es.com.movertis.backend.core.api.units;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.com.movertis.backend.core.utils.Ctes;
import es.com.movertis.backend.core.utils.HttpUtils;
import es.com.movertis.backend.core.utils.Utils;
import es.com.movertis.backend.lib.model.units.service.request.RequestGetTotalKms;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UnitAPIImpl implements IUnitAPI{
    private static final Logger logger = LogManager.getLogger(UnitAPIImpl.class);
    private final ObjectMapper om = new ObjectMapper();
    @Override
    public SummaryTripDTO getSummaryTrip(RequestGetTotalKms requestTotalKms) {
        try {
            String request = om.writeValueAsString(requestTotalKms);
            logger.info("GET KMS --> Request: {}", request);
            String response = HttpUtils.post(Ctes.Unit.GET_SUMMARY_TRIP_URL, Ctes.Security.TOKEN, request);
            logger.info("GET KMS --> Response: {}", response);

            if (response.isBlank()){
                return null;
            }

            return Utils.getSummaryTripFromJson(response);
        } catch (Exception ex){
            logger.error("Error in Get Summary Trip", ex);
            return null;
        }
    }
}
