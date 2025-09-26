package bd_bloodbank.api.domain;

public class Estoque {
    private int idEstoque;
    private String tipoSanguineo;
    private int qtdBolsas;

    public Estoque() {}

    public Estoque(int idEstoque, String tipoSanguineo, int qtdBolsas) {
        this.idEstoque = idEstoque;
        this.tipoSanguineo = tipoSanguineo;
        this.qtdBolsas = qtdBolsas;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public int getQtdBolsas() {
        return qtdBolsas;
    }

    public void setQtdBolsas(int qtdBolsas) {
        this.qtdBolsas = qtdBolsas;
    }
}
