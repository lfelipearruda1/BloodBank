package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Telefone;

public class TelefoneDAO {

    public int inserir(Telefone t) {
        String sql = "INSERT INTO Telefone (Telefone) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, t.getTelefone());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir telefone: " + e.getMessage(), e);
        }
    }

    public Telefone buscarPorId(int id) {
        String sql = "SELECT * FROM Telefone WHERE Telefone_PK=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Telefone t = new Telefone();
                t.setId(rs.getInt("Telefone_PK"));
                t.setTelefone(rs.getString("Telefone"));
                return t;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar telefone: " + e.getMessage(), e);
        }
    }

    public List<Telefone> listarTodos() {
        List<Telefone> lista = new ArrayList<>();
        String sql = "SELECT * FROM Telefone";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Telefone t = new Telefone();
                t.setId(rs.getInt("Telefone_PK"));
                t.setTelefone(rs.getString("Telefone"));
                lista.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar telefones: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Telefone t) {
        String sql = "UPDATE Telefone SET Telefone=? WHERE Telefone_PK=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getTelefone());
            ps.setInt(2, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar telefone: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Telefone WHERE Telefone_PK=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar telefone: " + e.getMessage(), e);
        }
    }
}
