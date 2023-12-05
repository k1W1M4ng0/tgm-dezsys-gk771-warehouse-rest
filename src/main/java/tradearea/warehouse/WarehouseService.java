package tradearea.warehouse;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import tradearea.model.WarehouseData;

@Service
public class WarehouseService {
    private static Set<String> activeMessages = new HashSet<>();

    /**
     * adds a message that has been sent
     * @param warehouseID
     */
    public static void addSentMessage(String warehouseID) {
        activeMessages.add(warehouseID);
    }

    /**
     * acknowledge a message 
     * @param warehouseID
     */
    public static void acknowledgeSentMessage(String warehouseID) {
        activeMessages.remove(warehouseID);
    }

    /**
     * get all messages that havent been acknowledged yet
     * @return
     */
    public static Set<String> getActiveMessages() {
        return activeMessages;
    }
	
	public String getGreetings( String inModule ) {
        return "Greetings from " + inModule;
    }

    public WarehouseData getWarehouseData( String inID ) {
    	
    	WarehouseSimulation simulation = new WarehouseSimulation(inID);
        return simulation.getData( inID );
        
    }
    
}
