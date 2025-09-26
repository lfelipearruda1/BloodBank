package bd_bloodbank.api.domain;

public class Doador {
    private String cpf;
    private String nome;
    private String sobrenome;
    private int idade;
    private String sexo;
    private String tipoSanguineo;
    private int qtdBolsasDoadas;

    // Novos atributos de endereço
    private String cep;
    private String rua;
    private String cidade;
    private String estado;

    public Doador() {}

    public Doador(String cpf, String nome, String sobrenome, int idade, String sexo,
                  String tipoSanguineo, int qtdBolsasDoadas,
                  String cep, String rua, String cidade, String estado) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.sexo = sexo;
        this.tipoSanguineo = tipoSanguineo;
        this.qtdBolsasDoadas = qtdBolsasDoadas;
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
    }

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public int getQtdBolsasDoadas() { return qtdBolsasDoadas; }
    public void setQtdBolsasDoadas(int qtdBolsasDoadas) { this.qtdBolsasDoadas = qtdBolsasDoadas; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
