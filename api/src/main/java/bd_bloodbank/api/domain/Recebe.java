package bd_bloodbank.api.domain;

public class Recebe {
    private int idBolsa;
    private String cnpjHospital;

    public Recebe() {}

    public Recebe(int idBolsa, String cnpjHospital) {
        this.idBolsa = idBolsa;
        this.cnpjHospital = cnpjHospital;
    }

    public int getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(int idBolsa) {
        this.idBolsa = idBolsa;
    }

    public String getCnpjHospital() {
        return cnpjHospital;
    }

    public void setCnpjHospital(String cnpjHospital) {
        this.cnpjHospital = cnpjHospital;
    }
}
