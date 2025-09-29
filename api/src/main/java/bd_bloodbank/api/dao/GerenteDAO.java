package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Gerente;

public class GerenteDAO {

    public void inserir(Gerente g) {
        String sql = "INSERT INTO Gerente (nome, cnpj_hospital) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getNome());
            ps.setString(2, g.getCnpjHospital());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir gerente: " + e.getMessage(), e);
        }
    }

    public List<Gerente> listarTodos() {
        List<Gerente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Gerente";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Gerente g = new Gerente(
                        rs.getInt("id_gerente"),
                        rs.getString("nome"),
                        rs.getString("cnpj_hospital")
                );
                lista.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar gerentes: " + e.getMessage(), e);
        }
        return lista;
    }

    public Gerente buscarPorId(int idGerente) {
        String sql = "SELECT * FROM Gerente WHERE id_gerente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGerente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Gerente(
                            rs.getInt("id_gerente"),
                            rs.getString("nome"),
                            rs.getString("cnpj_hospital")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar gerente: " + e.getMessage(), e);
        }
        return null;
    }

    public void deletar(int idGerente) {
        String sql = "DELETE FROM Gerente WHERE id_gerente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGerente);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar gerente: " + e.getMessage(), e);
        }
    }
}
