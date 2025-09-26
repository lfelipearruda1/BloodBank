package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Estoque;

public class EstoqueDAO {

    public void inserir(Estoque e) {
        // Verifica se o tipo sanguíneo já existe
        Estoque existente = buscarPorTipo(e.getTipoSanguineo());

        if (existente != null) {
            // Se existir, soma as quantidades e atualiza
            int novaQtd = existente.getQtdBolsas() + e.getQtdBolsas();
            existente.setQtdBolsas(novaQtd);
            atualizarPorTipoSanguineo(existente);
        } else {
            // Se não existir, insere normalmente
            String sql = "INSERT INTO Estoque (tipo_sanguineo, qtd_bolsas) VALUES (?, ?)";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, e.getTipoSanguineo());
                ps.setInt(2, e.getQtdBolsas());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    e.setIdEstoque(rs.getInt(1));
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao inserir estoque: " + ex.getMessage(), ex);
            }
        }
    }

    public void atualizar(Estoque e) {
        String sql = "UPDATE Estoque SET tipo_sanguineo=?, qtd_bolsas=? WHERE id_estoque=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getTipoSanguineo());
            ps.setInt(2, e.getQtdBolsas());
            ps.setInt(3, e.getIdEstoque());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar estoque: " + ex.getMessage(), ex);
        }
    }

    // Atualiza a quantidade baseada no tipo sanguíneo (sem precisar do ID)
    public void atualizarPorTipoSanguineo(Estoque e) {
        String sql = "UPDATE Estoque SET qtd_bolsas=? WHERE tipo_sanguineo=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getQtdBolsas());
            ps.setString(2, e.getTipoSanguineo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar por tipo sanguíneo: " + ex.getMessage(), ex);
        }
    }

    public Estoque buscarPorTipo(String tipo) {
        String sql = "SELECT * FROM Estoque WHERE tipo_sanguineo=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Estoque(
                        rs.getInt("id_estoque"),
                        rs.getString("tipo_sanguineo"),
                        rs.getInt("qtd_bolsas")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar estoque: " + ex.getMessage(), ex);
        }
    }

    public List<Estoque> listarTodos() {
        String sql = "SELECT * FROM Estoque";
        List<Estoque> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estoque e = new Estoque(
                        rs.getInt("id_estoque"),
                        rs.getString("tipo_sanguineo"),
                        rs.getInt("qtd_bolsas")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar estoque: " + ex.getMessage(), ex);
        }
        return lista;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Estoque WHERE id_estoque=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar estoque: " + ex.getMessage(), ex);
        }
    }
}
