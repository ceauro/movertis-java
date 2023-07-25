package es.com.movertis.backend.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ctes {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Security{
        public static final String TOKEN = "b19d0e6bc045b6e4017ca8a8d9013ae92674E1610E84A2D7FB018F1400C5FB1D404E7815";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Driver{
        public static final String GET_DIRVERS_URL = "https://devapi.hellomovertis.com/driver/getdrivers";
        public static final String GET_UNIT_DRIVERS_URL = "https://devapi.hellomovertis.com/driver/bindrecords";
        public static final String GET_SUMMARY_DRIVER_URL = "https://devapi.hellomovertis.com/driver/summarytimes";
        public static final String GET_COSTS_DRIVER_URL = "https://devapi.hellomovertis.com/costs/read";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Unit{
        public static final String GET_SUMMARY_TRIP_URL = "https://devapi.hellomovertis.com/v2/units/trips/summary";
    }
}
