package bd_bloodbank.api.domain;

public class BolsaSangue {
    private int idBolsa;
    private String tipoSanguineo;
    private String fkDoadorCpf;  // 🔑 chave estrangeira para Doador

    public BolsaSangue() {}

    public BolsaSangue(int idBolsa, String tipoSanguineo, String fkDoadorCpf) {
        this.idBolsa = idBolsa;
        this.tipoSanguineo = tipoSanguineo;
        this.fkDoadorCpf = fkDoadorCpf;
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

    public String getFkDoadorCpf() {
        return fkDoadorCpf;
    }

    public void setFkDoadorCpf(String fkDoadorCpf) {
        this.fkDoadorCpf = fkDoadorCpf;
    }
}
