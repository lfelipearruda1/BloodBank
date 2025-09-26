package bd_bloodbank.api.domain;

public class BolsaSangue {
    private int idBolsa;
    private String tipoSanguineo;

    public BolsaSangue() {}

    public BolsaSangue(int idBolsa, String tipoSanguineo) {
        this.idBolsa = idBolsa;
        this.tipoSanguineo = tipoSanguineo;
    }

    public int getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(int idBolsa) {
        this.idBolsa = idBolsa;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
}
