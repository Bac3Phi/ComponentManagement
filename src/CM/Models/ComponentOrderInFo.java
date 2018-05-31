package CM.Models;

public class ComponentOrderInFo {
    private String CompOrderID;
    private String CompOrderInfoID;
    private String ComponentID;
    private int Quantities;

    public ComponentOrderInFo() {}

    public ComponentOrderInFo(String compOrderInfoID, int quantities, String compOrderID, String componentID) {
        CompOrderID = compOrderID;
        CompOrderInfoID = compOrderInfoID;
        ComponentID = componentID;
        Quantities = quantities;
    }

    public String getCompOrderID() { return CompOrderID; }

    public void setCompOrderID(String compOrderID) { CompOrderID = compOrderID; }

    public String getCompOrderInfoID() { return CompOrderInfoID; }

    public void setCompOrderInfoID(String compOrderInfoID) { CompOrderInfoID = compOrderInfoID; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }

    public int getQuantities() { return Quantities;  }

    public void setQuantities(int quantities) { Quantities = quantities; }
}
