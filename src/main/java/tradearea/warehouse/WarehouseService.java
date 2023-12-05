package tradearea.warehouse;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import tradearea.model.WarehouseData;

@Service
public class WarehouseService {
    private Set<String> activeMessages;

    public WarehouseService() {
        this.activeMessages = new HashSet<>();
    }

    /**
     * adds a message that has been sent
     * @param warehouseID
     */
    public void addSentMessage(String warehouseID) {
        this.activeMessages.add(warehouseID);
    }

    /**
     * acknowledge a message 
     * @param warehouseID
     */
    public void acknowledgeSentMessage(String warehouseID) {
        this.activeMessages.remove(warehouseID);
    }

    /**
     * get all messages that havent been acknowledged yet
     * @return
     */
    public Set<String> getActiveMessages() {
        return this.activeMessages;
    }
	
	public String getGreetings( String inModule ) {
        return "Greetings from " + inModule;
    }

    public WarehouseData getWarehouseData( String inID ) {
    	
    	WarehouseSimulation simulation = new WarehouseSimulation(inID);
        return simulation.getData( inID );
        
    }
    
}
