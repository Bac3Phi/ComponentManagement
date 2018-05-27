package CM.Models;

public class Components {
    private String ComponentID;
    private String ComponentName;
    private String Unit;
    private String CompMaker;
    private String Photos;
    private String TypesOfCompID;
    private String CompInfo;

    public Components(){}

    public Components(String MHID, String componentName, String hangsx, String donvitinh, String cauhinh, String hinh, String maloai){
        this.ComponentID = MHID;
        this.ComponentName = componentName;
        this.CompMaker = hangsx;
        this.Unit = donvitinh;
        this.CompInfo = cauhinh;
        this.Photos = hinh;
        this.TypesOfCompID = maloai;
    }

    public String getComponentID() {
        return ComponentID;
    }

    public void setComponentID(String MHID) {
        this.ComponentID = MHID;
    }

    public String getComponentName() {
        return ComponentName;
    }

    public void setComponentName(String componentName) {
        this.ComponentName = componentName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String donvitinh) {
        this.Unit = donvitinh;
    }

    public String getCompMaker() {
        return CompMaker;
    }

    public void setCompMaker(String hangsx) {
        this.CompMaker = hangsx;
    }

    public String getPhotos() { return Photos; }

    public void setPhotos(String photos) {
        Photos = photos;
    }

    public String getTypesOfCompID() { return TypesOfCompID; }

    public void setTypesOfCompID(String typesOfCompID) { TypesOfCompID = typesOfCompID; }

    public String getCompInfo() { return CompInfo; }

    public void setCompInfo(String compInfo) { CompInfo = compInfo; }
}
