package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Solicitacao;

public class SolicitacaoDAO {

    public void inserir(Solicitacao s) {
        String sql = "INSERT INTO Solicitacao (tipo_sanguineo, qtd_bolsas, id_gerente, cnpj_hospital) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getTipoSanguineo());
            ps.setInt(2, s.getQtdBolsas());
            ps.setInt(3, s.getIdGerente());
            ps.setString(4, s.getCnpjHospital());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir solicitação: " + e.getMessage(), e);
        }
    }

    public List<Solicitacao> listarTodos() {
        String sql = "SELECT * FROM Solicitacao";
        List<Solicitacao> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Solicitacao s = new Solicitacao();
                s.setIdSolicitacao(rs.getInt("id_solicitacao"));
                s.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                s.setQtdBolsasSolicitadas(rs.getInt("qtd_bolsas"));
                s.setIdGerente(rs.getInt("id_gerente"));
                s.setCnpjHospital(rs.getString("cnpj_hospital"));
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
                Solicitacao s = new Solicitacao();
                s.setIdSolicitacao(rs.getInt("id_solicitacao"));
                s.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                s.setQtdBolsasSolicitadas(rs.getInt("qtd_bolsas"));
                s.setIdGerente(rs.getInt("id_gerente"));
                s.setCnpjHospital(rs.getString("cnpj_hospital"));
                return s;
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
