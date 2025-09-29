package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.SolicitaFuncionarioEstoque;

public class SolicitaFuncionarioEstoqueDAO {

    public void inserir(SolicitaFuncionarioEstoque sfe) {
        String sql = "INSERT INTO Solicita_Funcionario_Estoque (fk_Gerente_Hospital_Hospit, fk_Funcionario_id_funcionario, tipo_sanguineo, qtd_bolsas) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, sfe.getIdGerenteHospital());
            ps.setInt(2, sfe.getIdFuncionario());
            ps.setString(3, sfe.getTipoSanguineo());
            ps.setInt(4, sfe.getQtdBolsas());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                sfe.setIdSolicitacao(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir solicitação funcionário-estoque: " + e.getMessage(), e);
        }
    }

    public List<SolicitaFuncionarioEstoque> listarTodos() {
        List<SolicitaFuncionarioEstoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicita_Funcionario_Estoque";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SolicitaFuncionarioEstoque sfe = new SolicitaFuncionarioEstoque(
                );
                lista.add(sfe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações funcionário-estoque: " + e.getMessage(), e);
        }
        return lista;
    }
}
