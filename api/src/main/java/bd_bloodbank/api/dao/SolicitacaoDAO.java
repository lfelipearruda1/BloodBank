package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Solicitacao;

public class SolicitacaoDAO {

    public void inserir(Solicitacao s) {
        String sql = "INSERT INTO Solicitacao (tipo_sanguineo, qtd_bolsas, id_gerente, cnpj_hospital) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getTipoSanguineo());
            ps.setInt(2, s.getQtdBolsasSolicitadas());
            ps.setInt(3, s.getIdGerente());
            ps.setString(4, s.getCnpjHospital());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                s.setIdSolicitacao(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir solicitação: " + e.getMessage(), e);
        }
    }

    public List<Solicitacao> listarTodos() {
        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicitacao";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Solicitacao s = new Solicitacao(
                        rs.getInt("id_solicitacao"),
                        rs.getString("tipo_sanguineo"),
                        rs.getInt("qtd_bolsas"),
                        rs.getInt("id_gerente"),
                        rs.getString("cnpj_hospital")
                );
                lista.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações: " + e.getMessage(), e);
        }
        return lista;
    }

    public Solicitacao buscarPorId(int id) {
        String sql = "SELECT * FROM Solicitacao WHERE id_solicitacao=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Solicitacao(
                        rs.getInt("id_solicitacao"),
                        rs.getString("tipo_sanguineo"),
                        rs.getInt("qtd_bolsas"),
                        rs.getInt("id_gerente"),
                        rs.getString("cnpj_hospital")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar solicitação: " + e.getMessage(), e);
        }
        return null;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Solicitacao WHERE id_solicitacao=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar solicitação: " + e.getMessage(), e);
        }
    }
}
