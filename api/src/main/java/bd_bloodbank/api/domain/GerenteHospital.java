package bd_bloodbank.api.domain;

public class GerenteHospital {
    private int idGerente;
    private String nome;
    private String cnpj;
    private int telefoneId; // FK da tabela Telefone
    private String endereco;

    public GerenteHospital() {}

    public GerenteHospital(int idGerente, String nome, String cnpj, int telefoneId, String endereco) {
        this.idGerente = idGerente;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefoneId = telefoneId;
        this.endereco = endereco;
    }

    public int getIdGerente() { return idGerente; }
    public void setIdGerente(int idGerente) { this.idGerente = idGerente; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public int getTelefoneId() { return telefoneId; }
    public void setTelefoneId(int telefoneId) { this.telefoneId = telefoneId; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
