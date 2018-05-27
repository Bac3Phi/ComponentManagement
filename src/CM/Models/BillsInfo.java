package CM.Models;

public class BillsInfo {
    private String BillsInfoID;
    private String SellingPrice;
    private int Quantities;
    private String BillID;
    private String ComponentID;

    public BillsInfo(String billsInfoID, String sellingPrice, int quantities, String billID, String componentID) {
        BillsInfoID = billsInfoID;
        SellingPrice = sellingPrice;
        Quantities = quantities;
        BillID = billID;
        ComponentID = componentID;
    }

    public String getBillsInfoID() { return BillsInfoID; }

    public void setBillsInfoID(String billsInfoID) { BillsInfoID = billsInfoID; }

    public String getSellingPrice() { return SellingPrice; }

    public void setSellingPrice(String sellingPrice) { SellingPrice = sellingPrice; }

    public int getQuantities() { return Quantities; }

    public void setQuantities(int quantities) { Quantities = quantities; }

    public String getBillID() { return BillID; }

    public void setBillID(String billID) { BillID = billID; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }
}
