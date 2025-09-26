package bd_bloodbank.api.domain;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String funcionarioTipo; 

    public Funcionario() {}

    public Funcionario(int idFuncionario, String nome, String funcionarioTipo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.funcionarioTipo = funcionarioTipo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncionarioTipo() {
        return funcionarioTipo;
    }

    public void setFuncionarioTipo(String funcionarioTipo) {
        this.funcionarioTipo = funcionarioTipo;
    }
}
