package CM.Models;

public class TypeOfGoods {
    private String TypesOfCompID;
    private String TypesOfCompName;

    public String getTypesOfCompID() { return TypesOfCompID; }

    public void setTypesOfCompID(String typesOfCompID) { TypesOfCompID = typesOfCompID; }

    public String getTypesOfCompName() { return TypesOfCompName; }

    public void setTypesOfCompName(String typesOfCompName) { TypesOfCompName = typesOfCompName; }

    public TypeOfGoods(String maLoai, String typesOfCompName) {
        TypesOfCompID = maLoai;
        TypesOfCompName = typesOfCompName;
    }
}
