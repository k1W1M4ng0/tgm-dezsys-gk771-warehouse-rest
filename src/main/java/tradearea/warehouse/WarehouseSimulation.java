package tradearea.warehouse;

import tradearea.model.Product;
import tradearea.model.WarehouseData;

public class WarehouseSimulation {
	
	public static final String[] cities = {
		"Wien", "St. Pölten", "Linz", "Graz", "Salzburg", "Eisenstadt", 
		"Klagenfurt", "Innsbruck", "Bregenz"
	};
	
	public static final String[] buildings = {
		"Bahnhof", "Flughafen", "Büro"
	};
	
	public static final String[] productPrefixes = {
		"Super ", "Bio ", "Turbo ", ""
	};
	
	public static final String[] productNames = {
		"Apfel", "Banane", "Orange", "Wasserflasche", "Laptop", "Brille"
	};
	
	public static final String[] productSuffixes = {
		" Max", " Plus", " Pro", " Gold", " Platinum", ""
	};
	
	public static final String[] units = {
		"s", "m", "kg", "A", "K", "mol", "cd"
	};
	
	private double getRandomDouble( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) )) + inMinimum; 
		double rounded = Math.round(number * 100.0) / 100.0; 
		return rounded;
		
	}

	private int getRandomInt( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) )) + inMinimum; 
		Double rounded = Math.floor(number); 
		System.out.println("random between " + inMinimum + "and " + inMaximum + ": " + rounded.intValue());
		return rounded.intValue();

	}
	
	public WarehouseData getData( String inID ) {
		String city = cities[getRandomInt(0, cities.length)];
		String building = buildings[getRandomInt(0, buildings.length)];
		
		WarehouseData data = new WarehouseData();
		data.setWarehouseID( inID );
		data.setWarehouseName(city + " " + building);
		data.setWarehouseAddress(building + "straße " + getRandomInt(1, 100)
			// 50% chance for house nr addon
			+ (getRandomInt(0, 1) == 1 ? "" : ("/" + getRandomInt(1, 50))));
		data.setWarehousePostalCode(city);
		data.setWarehouseCity(city);
		data.setWarehouseCountry("Austria");
		int countProducts = getRandomInt(1,4);
		for(int i = 0; i < countProducts; ++i) {
			Product p = new Product();
			
			p.setProductID(Integer.toString(getRandomInt(1000, 9999)));
			p.setProductName(
				productPrefixes[getRandomInt(0, productPrefixes.length)]
				+ productNames[getRandomInt(0, productNames.length)] 
				+ productSuffixes[getRandomInt(0, productSuffixes.length)]
			);
			p.setProductCategory("Verbrauchsgut");
			p.setProductQuantity(getRandomInt(1, 100));
			p.setProductUnit(units[getRandomInt(0, units.length)]);

			data.addProduct(p);			
		}

		return data;
		
	}

}
