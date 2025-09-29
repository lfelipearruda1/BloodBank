package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.BolsaSangue;

public class BolsaSangueDAO {

    // Inserir bolsa vinculada a um doador
    public void inserir(BolsaSangue b) {
    String sql = "INSERT INTO Bolsa_Sangue (tipo_sanguineo, fk_doador_cpf, data_doacao) VALUES (?, ?, CURDATE())";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, b.getTipoSanguineo());
        ps.setString(2, b.getFkDoadorCpf());  // 🔑 cpf do doador
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao inserir bolsa de sangue: " + e.getMessage(), e);
    }
}

    // Listar todas as bolsas
    public List<BolsaSangue> listarTodas() {
        List<BolsaSangue> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bolsa_Sangue";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new BolsaSangue(
                        rs.getInt("id_bolsa"),
                        rs.getString("tipo_sanguineo"),
                        rs.getString("fk_doador_cpf")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar bolsas: " + ex.getMessage(), ex);
        }
        return lista;
    }

    // Buscar bolsa por ID
    public BolsaSangue buscarPorId(int id) {
        String sql = "SELECT * FROM Bolsa_Sangue WHERE id_bolsa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BolsaSangue(
                            rs.getInt("id_bolsa"),
                            rs.getString("tipo_sanguineo"),
                            rs.getString("fk_doador_cpf")
                    );
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar bolsa por ID: " + ex.getMessage(), ex);
        }
        return null;
    }

    // Deletar bolsa por ID
    public void deletar(int id) {
        String sql = "DELETE FROM Bolsa_Sangue WHERE id_bolsa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar bolsa: " + ex.getMessage(), ex);
        }
    }
}
