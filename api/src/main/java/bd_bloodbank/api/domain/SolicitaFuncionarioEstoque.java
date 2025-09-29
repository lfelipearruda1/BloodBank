package bd_bloodbank.api.domain;

public class SolicitaFuncionarioEstoque {
    private int idSolicitacao;
    private int funcionarioId;
    private String hospitalCnpj;
    private String tipoSanguineo;
    private String dataSolicitacao;

    public SolicitaFuncionarioEstoque() {}

    public SolicitaFuncionarioEstoque(int idSolicitacao, int funcionarioId, String hospitalCnpj, String tipoSanguineo, String dataSolicitacao) {
        this.idSolicitacao = idSolicitacao;
        this.funcionarioId = funcionarioId;
        this.hospitalCnpj = hospitalCnpj;
        this.tipoSanguineo = tipoSanguineo;
        this.dataSolicitacao = dataSolicitacao;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getHospitalCnpj() {
        return hospitalCnpj;
    }

    public void setHospitalCnpj(String hospitalCnpj) {
        this.hospitalCnpj = hospitalCnpj;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public int getIdGerenteHospital() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdGerenteHospital'");
    }

    public int getIdFuncionario() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdFuncionario'");
    }

    public int getQtdBolsas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQtdBolsas'");
    }
}
