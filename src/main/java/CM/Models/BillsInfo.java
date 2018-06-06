package CM.Models;

public class BillsInfo {
    private String BillsInfoID;
    private String SellingPrice;
    private int Quantities;
    private String BillID;
    private String ComponentName;
    private int PurchaseMoney;

    public BillsInfo(String billsInfoID, String sellingPrice, int quantities, String billID, String componentName, int tientt) {
        BillsInfoID = billsInfoID;
        SellingPrice = sellingPrice;
        Quantities = quantities;
        BillID = billID;
        ComponentName = componentName;
        this.PurchaseMoney = tientt;
    }

    public String getBillsInfoID() { return BillsInfoID; }

    public void setBillsInfoID(String billsInfoID) { BillsInfoID = billsInfoID; }

    public String getSellingPrice() { return SellingPrice; }

    public void setSellingPrice(String sellingPrice) { SellingPrice = sellingPrice; }

    public int getQuantities() { return Quantities; }

    public void setQuantities(int quantities) { Quantities = quantities; }

    public String getBillID() { return BillID; }

    public void setBillID(String billID) { BillID = billID; }

    public String getComponentName() { return ComponentName; }

    public void setComponentName(String componentName) { ComponentName = componentName; }

    public int getPurchaseMoney() {
        return PurchaseMoney;
    }

    public void setPurchaseMoney(int purchaseMoney) {
        this.PurchaseMoney = purchaseMoney;
    }
}
