package CM.Models;

public class Provider {
    private String ProviderID;
    private String ProviderName;
    private String ProviderAddress;
    private String ProviderEmail;
    private String ProviderPhone;

    public Provider() {}

    public Provider(String nccID, String providerName, String providerAddress, String providerEmail, String sdtNCC) {
        this.ProviderID = nccID;
        this.ProviderName = providerName;
        this.ProviderAddress = providerAddress;
        this.ProviderEmail = providerEmail;
        this.ProviderPhone = sdtNCC;
    }

    public String getProviderID() {
        return ProviderID;
    }

    public void setProviderID(String nccID) {
        this.ProviderID = nccID;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String providerName) {
        this.ProviderName = providerName;
    }

    public String getProviderAddress() {
        return ProviderAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.ProviderAddress = providerAddress;
    }

    public String getProviderEmail() {
        return ProviderEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.ProviderEmail = providerEmail;
    }

    public String getProviderPhone() {
        return ProviderPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.ProviderPhone = providerPhone;
    }
}
