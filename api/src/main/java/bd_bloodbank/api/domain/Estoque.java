package bd_bloodbank.api.domain;

public class Estoque {
    private String tipoSanguineo;   // PK da tabela
    private int qtdDisponivel;      // corresponde a qtd_disponivel no banco

    public Estoque() {}

    public Estoque(String tipoSanguineo, int qtdDisponivel) {
        this.tipoSanguineo = tipoSanguineo;
        this.qtdDisponivel = qtdDisponivel;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }
}
