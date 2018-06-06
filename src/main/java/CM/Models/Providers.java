package CM.Models;

public class Providers {
    private String ProvidersID;
    private String ProvidersName;
    private String ProvidersAddress;
    private String ProvidersEmail;
    private String ProvidersPhone;

    public Providers() {}

    public Providers(String nccID, String providersName, String providersAddress, String providersEmail, String sdtNCC) {
        this.ProvidersID = nccID;
        this.ProvidersName = providersName;
        this.ProvidersAddress = providersAddress;
        this.ProvidersEmail = providersEmail;
        this.ProvidersPhone = sdtNCC;
    }

    public String getProvidersID() {
        return ProvidersID;
    }

    public void setProvidersID(String nccID) {
        this.ProvidersID = nccID;
    }

    public String getProvidersName() {
        return ProvidersName;
    }

    public void setProvidersName(String providersName) {
        this.ProvidersName = providersName;
    }

    public String getProvidersAddress() {
        return ProvidersAddress;
    }

    public void setProvidersAddress(String providersAddress) {
        this.ProvidersAddress = providersAddress;
    }

    public String getProvidersEmail() {
        return ProvidersEmail;
    }

    public void setProvidersEmail(String providersEmail) {
        this.ProvidersEmail = providersEmail;
    }

    public String getProvidersPhone() {
        return ProvidersPhone;
    }

    public void setProvidersPhone(String providersPhone) {
        this.ProvidersPhone = providersPhone;
    }
}
