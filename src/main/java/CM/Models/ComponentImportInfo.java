package CM.Models;

public class ComponentImportInfo {
    private String CompImportInforId;
    private String CompImportInforImportId;
    private String Note;
    private String ComponentName;
    private String NumOfComp;
    private String UnitPrice;
    private String Price;
    private String Amount;

    public ComponentImportInfo(String compImportInforId, String compImportInforImportId, String note, String componentName, String numOfComp, String unitPrice, String price, String amount) {
        CompImportInforId = compImportInforId;
        CompImportInforImportId = compImportInforImportId;
        Note = note;
        ComponentName = componentName;
        NumOfComp = numOfComp;
        UnitPrice = unitPrice;
        Price = price;
        Amount = amount;
    }

    public String getCompImportInforId() {
        return CompImportInforId;
    }

    public void setCompImportInforId(String compImportInforId) {
        CompImportInforId = compImportInforId;
    }

    public String getCompImportInforImportId() {
        return CompImportInforImportId;
    }

    public void setCompImportInforImportId(String compImportInforImportId) {
        CompImportInforImportId = compImportInforImportId;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getComponentName() {
        return ComponentName;
    }

    public void setComponentName(String componentName) {
        ComponentName = componentName;
    }

    public String getNumOfComp() {
        return NumOfComp;
    }

    public void setNumOfComp(String numOfComp) {
        NumOfComp = numOfComp;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
