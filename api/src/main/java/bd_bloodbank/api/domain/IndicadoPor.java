package bd_bloodbank.api.domain;

public class IndicadoPor {
    private String cpfIndicador; 
    private String cpfIndicado;  

    public IndicadoPor() {}

    public IndicadoPor(String cpfIndicador, String cpfIndicado) {
        this.cpfIndicador = cpfIndicador;
        this.cpfIndicado = cpfIndicado;
    }

    public String getCpfIndicador() {
        return cpfIndicador;
    }

    public void setCpfIndicador(String cpfIndicador) {
        this.cpfIndicador = cpfIndicador;
    }

    public String getCpfIndicado() {
        return cpfIndicado;
    }

    public void setCpfIndicado(String cpfIndicado) {
        this.cpfIndicado = cpfIndicado;
    }
}
