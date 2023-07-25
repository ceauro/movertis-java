package es.com.movertis.backend.web.main;
/**
 * Importación de librarias
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase Main inicializadora del springboot
 * ({"es.com.movertis.backend.web.rest.clouddocs", "es.com.movertis.backend.service.clouddocs", "es.com.movertis.backend.core.api.clouddocs",
 * 	 "es.com.movertis.backend.web.rest.etecnic", "es.com.movertis.backend.service.etecnic", "es.com.movertis.backend.core.api.etecnic",
 * 	 "es.com.movertis.backend.web.rest.sensors", "es.com.movertis.backend.service.sensors", "es.com.movertis.backend.core.api.sensors"
 *  })
 */
@SpringBootApplication
@ComponentScan({
	"es.com.movertis.backend"
})
public class MovertisBackendWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovertisBackendWebApplication.class, args);
	}
}
