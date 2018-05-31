package CM.Models;

public class Components {
    private String ComponentID;
    private String ComponentName;
    private String Unit;
    private String CompMaker;
    private String Photos;
    private String TypesOfComp;
    private String CompInfo;
    private String AreaName;
    private String NumOfComp;

    public Components(){}

    public Components(String componentID, String componentName, String unit, String compMaker, String photos, String typesOfComp, String compInfo, String areaName, String numOfComp) {
        ComponentID = componentID;
        ComponentName = componentName;
        Unit = unit;
        CompMaker = compMaker;
        Photos = photos;
        TypesOfComp = typesOfComp;
        CompInfo = compInfo;
        AreaName = areaName;
        NumOfComp = numOfComp;
    }

    public String getComponentID() {
        return ComponentID;
    }

    public void setComponentID(String componentID) {
        ComponentID = componentID;
    }

    public String getComponentName() {
        return ComponentName;
    }

    public void setComponentName(String componentName) {
        ComponentName = componentName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getCompMaker() {
        return CompMaker;
    }

    public void setCompMaker(String compMaker) {
        CompMaker = compMaker;
    }

    public String getPhotos() {
        return Photos;
    }

    public void setPhotos(String photos) {
        Photos = photos;
    }

    public String getTypesOfComp() {
        return TypesOfComp;
    }

    public void setTypesOfComp(String typesOfComp) {
        TypesOfComp = typesOfComp;
    }

    public String getCompInfo() {
        return CompInfo;
    }

    public void setCompInfo(String compInfo) {
        CompInfo = compInfo;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getNumOfComp() {
        return NumOfComp;
    }

    public void setNumOfComp(String numOfComp) {
        NumOfComp = numOfComp;
    }
}
