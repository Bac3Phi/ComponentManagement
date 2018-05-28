package CM.Models;

public class ComponentOrderInFo {
    private String CompOrderID;
    private String CompOrderInfoID;
    private String ComponentID;
    private int Quantities;
    private String ImportPrice;


    public ComponentOrderInFo() {}

    public ComponentOrderInFo(String compOrderInfoID, String importPrice, int quantities, String compOrderID, String componentID) {
        CompOrderID = compOrderID;
        CompOrderInfoID = compOrderInfoID;
        ComponentID = componentID;
        Quantities = quantities;
        ImportPrice = importPrice;
    }

    public String getCompOrderID() { return CompOrderID; }

    public void setCompOrderID(String compOrderID) { CompOrderID = compOrderID; }

    public String getCompOrderInfoID() { return CompOrderInfoID; }

    public void setCompOrderInfoID(String compOrderInfoID) { CompOrderInfoID = compOrderInfoID; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }

    public int getQuantities() { return Quantities;  }

    public void setQuantities(int quantities) { Quantities = quantities; }

    public String getImportPrice() { return ImportPrice; }

    public void setImportPrice(String importPrice) { ImportPrice = importPrice; }
}
