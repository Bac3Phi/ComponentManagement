package CM.Models;

public class ComponentOrderInFo {
    private String CompOrderID;
    private String CompOrderInfoID;
    private String ComponentName;
    private int Quantities;

    public ComponentOrderInFo() {}

    public ComponentOrderInFo(String compOrderInfoID, int quantities, String compOrderID, String componentName) {
        CompOrderID = compOrderID;
        CompOrderInfoID = compOrderInfoID;
        ComponentName = componentName;
        Quantities = quantities;
    }

    public String getCompOrderID() { return CompOrderID; }

    public void setCompOrderID(String compOrderID) { CompOrderID = compOrderID; }

    public String getCompOrderInfoID() { return CompOrderInfoID; }

    public void setCompOrderInfoID(String compOrderInfoID) { CompOrderInfoID = compOrderInfoID; }

    public String getComponentName() { return ComponentName; }

    public void setComponentName(String componentName) { ComponentName = componentName; }

    public int getQuantities() { return Quantities;  }

    public void setQuantities(int quantities) { Quantities = quantities; }
}
