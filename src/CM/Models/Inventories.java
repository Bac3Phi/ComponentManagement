package CM.Models;

public class Inventories {
    private String StockID;
    private int InvenQuantities;
    private String ComponentID;

    public Inventories(String stockID, int invenQuantities, String componentID) {
        StockID = stockID;
        InvenQuantities = invenQuantities;
        ComponentID = componentID;
    }

    public String getStockID() { return StockID; }

    public void setStockID(String stockID) { StockID = stockID; }

    public int getInvenQuantities() { return InvenQuantities; }

    public void setInvenQuantities(int invenQuantities) { InvenQuantities = invenQuantities; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }
}
