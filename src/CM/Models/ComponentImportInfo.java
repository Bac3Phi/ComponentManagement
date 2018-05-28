package CM.Models;

public class ComponentImportInfo {
    private String CompImportInfoID;
    private int Quantities;
    private String ImportPrice;
    private String SellingPrice;
    private String Note;
    private String ComponentID;
    private String CompImportID;

    public ComponentImportInfo(String compImportInfoID, int quantities, String importPrice, String sellingPrice, String note, String componentID, String compImportID) {
        CompImportInfoID = compImportInfoID;
        Quantities = quantities;
        ImportPrice = importPrice;
        SellingPrice = sellingPrice;
        Note = note;
        ComponentID = componentID;
        CompImportID = compImportID;
    }

    public String getCompImportInfoID() { return CompImportInfoID; }

    public void setCompImportInfoID(String compImportInfoID) { CompImportInfoID = compImportInfoID; }

    public int getQuantities() { return Quantities; }

    public void setQuantities(int quantities) { Quantities = quantities; }

    public String getImportPrice() { return ImportPrice; }

    public void setImportPrice(String importPrice) { ImportPrice = importPrice; }

    public String getSellingPrice() { return SellingPrice; }

    public void setSellingPrice(String sellingPrice) { SellingPrice = sellingPrice; }

    public String getNote() { return Note; }

    public void setNote(String note) { Note = note; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }

    public String getCompImportID() { return CompImportID; }

    public void setCompImportID(String compImportID) { CompImportID = compImportID; }
}
