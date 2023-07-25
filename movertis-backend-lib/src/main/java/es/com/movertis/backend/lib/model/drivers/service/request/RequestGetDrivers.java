package es.com.movertis.backend.lib.model.drivers.service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGetDrivers {
    private List<String> id;
    private Flags flags;
}
