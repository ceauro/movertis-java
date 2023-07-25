package es.com.movertis.backend.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.com.movertis.backend.lib.model.drivers.service.response.DriverCostDTO;
import es.com.movertis.backend.lib.model.drivers.service.response.SummaryDTO;
import es.com.movertis.backend.lib.model.units.service.response.SummaryTripDTO;
import es.com.movertis.backend.lib.model.units.service.response.TripDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static SummaryDTO getSummaryFromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        SummaryDTO summary = new SummaryDTO();
        try {
            JsonNode rootNode = objectMapper.readTree(json).get("results");
            JsonNode firstResult = rootNode.get(0);

            if(firstResult.has("global")){
                JsonNode globalNode = firstResult.get("global");
                if(globalNode.has("activity")){
                    summary.setActualActivity(globalNode.get("activity").asText());
                }
            }

            if(firstResult.has("rest")){
                JsonNode restNode = firstResult.get("rest");
                if(restNode.has("nextrest")) {
                    summary.setNextRest(restNode.get("nextrest").asLong());
                }
            }
        } catch (JsonProcessingException ex){
            return null;
        }

        return summary;
    }

    public static List<DriverCostDTO> getDriverCostsFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DriverCostDTO> costs = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(json);
            if (rootNode.isArray() && rootNode.size() > 0) {
                rootNode.elements().forEachRemaining(node -> {
                    DriverCostDTO driverCost = new DriverCostDTO();
                    if(node.has("amount")){
                        JsonNode amount = node.get("amount");
                        driverCost.setAmount(amount.get("value").asText() + " " + amount.get("currency").textValue());
                    }

                    if(node.has("driver")){
                        JsonNode driver = node.get("driver");
                        if(driver.has("unit")){
                            JsonNode unit = driver.get("unit");
                            driverCost.setUnit(unit.get("id").asLong());
                        }
                    }

                    driverCost.setDate(node.get("date").asLong());
                    driverCost.setTypeCost(node.get("type").asText());
                    driverCost.setDescription(node.get("description").asText());
                    costs.add(driverCost);
                });
            }
            return costs;
        } catch (JsonProcessingException ex){
            return null;
        }
    }

    public static SummaryTripDTO getSummaryTripFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            SummaryTripDTO summary = new SummaryTripDTO();

            if(rootNode.has("summary")) {
                JsonNode summaryNode = rootNode.get("summary");
                summary.setTotalKms(summaryNode.get("km").asInt());
            }

            if(rootNode.has("trips")){
                List<TripDTO> trips = new ArrayList<>();
                JsonNode tripsNode = rootNode.get("trips");

                tripsNode.elements().forEachRemaining(node -> {
                    TripDTO trip = new TripDTO();
                    trip.setKms(node.get("km").asInt());
                    trip.setEvent(node.get("event").asText());
                    trips.add(trip);
                });

                summary.setTrips(trips);
            }

            return summary;
        } catch (JsonProcessingException ex){
            return null;
        }
    }
}
