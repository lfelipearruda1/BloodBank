package bd_bloodbank.api.domain;

public class Doador {
    private String cpf;             // PK
    private String nome;
    private int idade;
    private String sexo;            // "M" ou "F"
    private String tipoSanguineo;   // Ex: A+, O-, etc.

    public Doador() {}

    public Doador(String cpf, String nome, int idade, String sexo, String tipoSanguineo) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.tipoSanguineo = tipoSanguineo;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    @Override
    public String toString() {
        return "Doador{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", tipoSanguineo='" + tipoSanguineo + '\'' +
                '}';
    }
}
