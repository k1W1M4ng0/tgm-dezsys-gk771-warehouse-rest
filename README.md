# SYT Dezentrale Systeme 7.1 Warehouse REST & Dataformats

## Einführung

Es soll eine REST-API Schnittstelle erstellt werden, die zufällige Daten von einem Warenhaus, in einem bestimmten Format liefert. Dabei sollen die Daten in JSON und in XML je nach Anfrage formatiert sein.

## Spring Boot Backend

Zuerst wurde ein vorhandenes Spring Boot Projekt geklont: 

[Vorlageprojekt](https://github.com/ThomasMicheler/DEZSYS_GK771_WAREHOUSE_REST)

### Datenklassen

Es gab schon ein WarehouseData, welches die Daten für eine Anfrage speichert. Diese war noch nicht vollständig, deswegen wurde sie um einfache *private* Attribute erweitert. 

Für diese wurden außerdem noch Setter und Getter hinzugefügt. Dieser Schritt ist besonders wichtig:  
Die Setter werden gebraucht, um später die (zufälligen) Daten zu setzen.  
Die Getter werden gebraucht, damit automatisch der XML / JSON String gebaut werden kann.

#### Product

Es wurde zusätzlich die Datenklasse Product hinzugefügt, weil sie noch fehlte.

### Zufällige Zahlen

In WarehouseSimulation wurde die Methode *getData* so angepasst, dass die meisten Attribute zufällig generiert werden.

Später wurde das zufällige Generieren so geändert, dass ein *java.util.Random* Objekt verwendet wird, welches als Seed die ID des Warehouses hat.

### CORS

Beim RestController muss auch noch @CrossOrigin hinzugefügt werden, damit keine CORS Fehler passieren.

### RequestMapping

Code Snippet:

```java
    @RequestMapping(value="/warehouse/{inID}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public WarehouseData warehouseData( @PathVariable String inID ) {
        return service.getWarehouseData( inID );
    }
```

Hier gibt es eine PathVariable. Wenn man also `GET localhost:8080/warehouse/123/data` aufruft, dann wird "123" in den Parameter *inID* gespeichert.

*produces* gibt das Datenformat an. In diesem Beispiel wird JSON angegeben, deswegen wird das von der Methode zurückgegebene WarehouseData automatisch in ein JSON Objekt umgewandelt.

## HTML Consumer Frontend

Mit jQuery wird die Tabelle gebaut.

