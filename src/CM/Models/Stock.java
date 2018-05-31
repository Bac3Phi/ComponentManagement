package CM.Models;

public class Stock {
    private String AreaCode;
    private String AreaName;

    public Stock(String areaCode, String areaName) {
        AreaCode = areaCode;
        AreaName = areaName;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
}
