package bd_bloodbank.api.domain;

public class Atende {
    private int idGerente;
    private String cpfPaciente;

    public Atende() {}

    public Atende(int idGerente, String cpfPaciente) {
        this.idGerente = idGerente;
        this.cpfPaciente = cpfPaciente;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
}
