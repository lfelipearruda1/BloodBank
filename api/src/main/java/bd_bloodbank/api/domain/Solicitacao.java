package bd_bloodbank.api.domain;

public class Solicitacao {
    private int idSolicitacao;
    private String tipoSanguineo;
    private int qtdBolsasSolicitadas;
    private int idGerente;
    private String cnpjHospital;

    public Solicitacao() {}

    public Solicitacao(int idSolicitacao, String tipoSanguineo, int qtdBolsasSolicitadas, int idGerente, String cnpjHospital) {
        this.idSolicitacao = idSolicitacao;
        this.tipoSanguineo = tipoSanguineo;
        this.qtdBolsasSolicitadas = qtdBolsasSolicitadas;
        this.idGerente = idGerente;
        this.cnpjHospital = cnpjHospital;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public int getQtdBolsasSolicitadas() {
        return qtdBolsasSolicitadas;
    }

    public void setQtdBolsasSolicitadas(int qtdBolsasSolicitadas) {
        this.qtdBolsasSolicitadas = qtdBolsasSolicitadas;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public String getCnpjHospital() {
        return cnpjHospital;
    }

    public void setCnpjHospital(String cnpjHospital) {
        this.cnpjHospital = cnpjHospital;
    }
}
