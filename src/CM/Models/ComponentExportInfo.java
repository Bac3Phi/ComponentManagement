package CM.Models;

public class ComponentExportInfo {
    private String CompExportInfoID;
    private int Quantities;
    private String Notes;
    private String ComponentID;
    private String CompExportID;

    public ComponentExportInfo(String compExportInfoID, int quantities, String notes, String componentID, String compExportID) {
        CompExportInfoID = compExportInfoID;
        Quantities = quantities;
        Notes = notes;
        ComponentID = componentID;
        CompExportID = compExportID;
    }

    public String getCompExportInfoID() { return CompExportInfoID; }

    public void setCompExportInfoID(String compExportInfoID) { CompExportInfoID = compExportInfoID; }

    public int getQuantities() { return Quantities; }

    public void setQuantities(int quantities) { Quantities = quantities; }

    public String getNotes() { return Notes; }

    public void setNotes(String notes) { Notes = notes; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }

    public String getCompExportID() { return CompExportID; }

    public void setCompExportID(String compExportID) { CompExportID = compExportID; }
}
