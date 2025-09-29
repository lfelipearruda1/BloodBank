package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Hospital;

public class HospitalDAO {

    public void inserir(Hospital h) {
        String sql = "INSERT INTO Hospital (cnpj, nome, endereco) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, h.getCnpj());
            ps.setString(2, h.getNome());
            ps.setString(3, h.getEndereco());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir hospital: " + e.getMessage(), e);
        }
    }

    public List<Hospital> listarTodos() {
        List<Hospital> lista = new ArrayList<>();
        String sql = "SELECT * FROM Hospital";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Hospital(
                        rs.getString("cnpj"),
                        rs.getString("nome"),
                        rs.getString("endereco")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar hospitais: " + e.getMessage(), e);
        }
        return lista;
    }

    public Hospital buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM Hospital WHERE cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cnpj);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Hospital(
                        rs.getString("cnpj"),
                        rs.getString("nome"),
                        rs.getString("endereco")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar hospital por CNPJ: " + e.getMessage(), e);
        }
    }

    public void deletar(String cnpj) {
        String sql = "DELETE FROM Hospital WHERE cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cnpj);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar hospital: " + e.getMessage(), e);
        }
    }

    public void atualizar(Hospital h) {
        String sql = "UPDATE Hospital SET nome=?, endereco=? WHERE cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, h.getNome());
            ps.setString(2, h.getEndereco());
            ps.setString(3, h.getCnpj());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar hospital: " + e.getMessage(), e);
        }
    }
}
