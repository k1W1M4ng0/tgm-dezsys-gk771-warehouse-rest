package tradearea.warehouse;

import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import tradearea.WarehouseApplication;
import tradearea.model.WarehouseData;

@RestController
@CrossOrigin
public class WarehouseController {
    Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private WarehouseService service;
	
    @RequestMapping("/")
    public String warehouseMain() {
    	String mainPage = "This is the warehouse application! (DEZSYS_WAREHOUSE_REST) <br/><br/>" +
                          "<a href='http://localhost:" + WarehouseApplication.port + "/warehouse/001/data'>Link to warehouse/001/data</a><br/>" +
                          "<a href='http://localhost:" + WarehouseApplication.port + "/warehouse/001/xml'>Link to warehouse/001/xml</a><br/>" +
                          "<a href='http://localhost:" + WarehouseApplication.port + "/warehouse/001/transfer'>Link to warehouse/001/transfer</a><br/>" +
                          "<a href='http://localhost:" + WarehouseApplication.port + "/warehouse/activemessages'>Link to get the sent messages that were not accepted</a><br/>";
        return mainPage;
    }

    @RequestMapping(value="/warehouse/{inID}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public WarehouseData warehouseData( @PathVariable String inID ) {
        logger.info(String.format("called /warehouse/%s/data", inID));

        WarehouseData data = service.getWarehouseData(inID);
        logger.info(String.format("Sent message with warehouseID: %s", data.getWarehouseID()));
        WarehouseService.addSentMessage(data.getWarehouseID());

        return data;
    }

    @GetMapping(value="/warehouse/activemessages") 
    public Set<String> activeMessages() {
        return WarehouseService.getActiveMessages();
    }

    @RequestMapping(value="/warehouse/{inID}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public WarehouseData warehouseDataXML( @PathVariable String inID ) {
        return service.getWarehouseData( inID );
    }

    @RequestMapping("/warehouse/{inID}/transfer")
    public String warehouseTransfer( @PathVariable String inID ) {
        return service.getGreetings("Warehouse.Transfer!");
    }

}
