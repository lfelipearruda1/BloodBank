package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.BolsaSangue;
import bd_bloodbank.api.domain.Estoque;

public class BolsaSangueDAO {

    public void inserir(BolsaSangue b) {
        String sql = "INSERT INTO Bolsa_Sangue (tipo_sanguineo) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. Inserir nova bolsa de sangue
            ps.setString(1, b.getTipoSanguineo());
            ps.executeUpdate();

            // 2. Atualizar o estoque correspondente
            EstoqueDAO estoqueDAO = new EstoqueDAO();
            Estoque existente = estoqueDAO.buscarPorTipo(b.getTipoSanguineo());

            if (existente != null) {
                existente.setQtdBolsas(existente.getQtdBolsas() + 1);
                estoqueDAO.atualizarPorTipoSanguineo(existente);
            } else {
                // Se não existir ainda o tipo no estoque, cria novo registro
                Estoque novo = new Estoque();
                novo.setTipoSanguineo(b.getTipoSanguineo());
                novo.setQtdBolsas(1);
                estoqueDAO.inserir(novo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir bolsa de sangue: " + e.getMessage(), e);
        }
    }

    public BolsaSangue buscarPorId(int id) {
        String sql = "SELECT * FROM Bolsa_Sangue WHERE id_bolsa=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BolsaSangue b = new BolsaSangue();
                b.setIdBolsa(rs.getInt("id_bolsa"));
                b.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                return b;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar bolsa de sangue: " + e.getMessage(), e);
        }
    }

    public List<BolsaSangue> listarTodas() {
        List<BolsaSangue> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bolsa_Sangue";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BolsaSangue b = new BolsaSangue();
                b.setIdBolsa(rs.getInt("id_bolsa"));
                b.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                lista.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar bolsas de sangue: " + e.getMessage(), e);
        }
        return lista;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Bolsa_Sangue WHERE id_bolsa=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar bolsa de sangue: " + e.getMessage(), e);
        }
    }
}
