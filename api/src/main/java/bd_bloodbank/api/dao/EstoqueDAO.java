package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Estoque;

public class EstoqueDAO {

    // Inserir novo registro de estoque
    public void inserir(Estoque e) {
    String sql = "INSERT INTO Estoque (tipo_sanguineo, qtd_disponivel) VALUES (?, ?) " +
                 "ON DUPLICATE KEY UPDATE qtd_disponivel = qtd_disponivel + VALUES(qtd_disponivel)";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, e.getTipoSanguineo());
        ps.setInt(2, e.getQtdDisponivel());
        ps.executeUpdate();
    } catch (SQLException ex) {
        throw new RuntimeException("Erro ao inserir/atualizar estoque: " + ex.getMessage(), ex);
    }
}


    // Atualizar estoque
    public void atualizar(Estoque e) {
        String sql = "UPDATE Estoque SET qtd_disponivel = ? WHERE tipo_sanguineo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getQtdDisponivel());
            ps.setString(2, e.getTipoSanguineo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar estoque: " + ex.getMessage(), ex);
        }
    }

    // Buscar estoque por tipo sanguíneo
    public Estoque buscarPorTipo(String tipo) {
        String sql = "SELECT * FROM Estoque WHERE tipo_sanguineo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estoque(
                        rs.getString("tipo_sanguineo"),
                        rs.getInt("qtd_disponivel")
                    );
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar estoque: " + ex.getMessage(), ex);
        }
        return null;
    }

    // Listar todo o estoque
    public List<Estoque> listarTodos() {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estoque";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Estoque(
                    rs.getString("tipo_sanguineo"),
                    rs.getInt("qtd_disponivel")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar estoque: " + ex.getMessage(), ex);
        }
        return lista;
    }

    // Deletar estoque por tipo sanguíneo
    public void remover(String tipo, int qtd) {
    String sql = "UPDATE Estoque SET qtd_disponivel = qtd_disponivel - ? " +
                 "WHERE tipo_sanguineo = ? AND qtd_disponivel >= ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, qtd);
        ps.setString(2, tipo);
        ps.setInt(3, qtd);

        int rows = ps.executeUpdate();
        if (rows == 0) {
            throw new RuntimeException("❌ Estoque insuficiente ou tipo não encontrado.");
        }
    } catch (SQLException ex) {
        throw new RuntimeException("Erro ao remover do estoque: " + ex.getMessage(), ex);
    }
}
}
