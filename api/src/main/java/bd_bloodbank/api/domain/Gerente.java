package bd_bloodbank.api.domain;

public class Gerente {
    private int idGerente;
    private String nome;
    private String cnpjHospital;

    public Gerente() {}

    public Gerente(int idGerente, String nome, String cnpjHospital) {
        this.idGerente = idGerente;
        this.nome = nome;
        this.cnpjHospital = cnpjHospital;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpjHospital() {
        return cnpjHospital;
    }

    public void setCnpjHospital(String cnpjHospital) {
        this.cnpjHospital = cnpjHospital;
    }
}
