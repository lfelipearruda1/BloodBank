package bd_bloodbank.api.domain;

public class Paciente {
    private String cpf;
    private String nome;
    private int idade;
    private String tipoSanguineo;

    public Paciente() {}

    public Paciente(String cpf, String nome, int idade, String tipoSanguineo) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.tipoSanguineo = tipoSanguineo;
    }

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

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
}
