package tradearea;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseApplication {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseApplication.class);

	public static void main(String[] args) {
        if(args.length > 0) {
            logger.info("Running on Port " + args[0]);
            SpringApplication app = new SpringApplication(WarehouseApplication.class);
            app.setDefaultProperties(
                Collections.singletonMap("server.port", Integer.parseInt( args[0]))
            );
            app.run();
        }
        else {
            logger.info("no args entered, using port 8080");
            SpringApplication.run(WarehouseApplication.class);
        }
	}
}
