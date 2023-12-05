package tradearea;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tradearea.warehouse.Receiver;

@SpringBootApplication
public class WarehouseApplication {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseApplication.class);

    public static int port = 8080;
    public static String warehouseApplicationID = "warehouse8080";

	public static void main(String[] args) {
        if(args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
                logger.info("Running on Port " + args[0]);
                warehouseApplicationID = "warehouse" + port;
            }
            catch(NumberFormatException ex) {
                logger.error("Arg0: " + args[0] + " couldnt be parsed, using 8080");
            }

            SpringApplication app = new SpringApplication(WarehouseApplication.class);
            app.setDefaultProperties(
                Collections.singletonMap("server.port", port)
            );
            app.run();
        }
        else {
            logger.info("no args entered, using port 8080");
            SpringApplication.run(WarehouseApplication.class);
        }
        Receiver receiver = new Receiver("acknowledgements");
	}
}
