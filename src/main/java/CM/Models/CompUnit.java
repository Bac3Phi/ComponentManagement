package CM.Models;

public class CompUnit {
    private String UnitCode;
    private String UnitName;

    public CompUnit(String unitCode, String unitName) {
        UnitCode = unitCode;
        UnitName = unitName;
    }

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
