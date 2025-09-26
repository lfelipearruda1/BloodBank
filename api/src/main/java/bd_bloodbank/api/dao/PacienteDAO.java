package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Paciente;

public class PacienteDAO {

    public void inserir(Paciente p) {
        String sql = "INSERT INTO Paciente (cpf, nome, idade, tipo_sanguineo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCpf());
            ps.setString(2, p.getNome());
            ps.setInt(3, p.getIdade());
            ps.setString(4, p.getTipoSanguineo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir paciente: " + e.getMessage(), e);
        }
    }

    public void atualizar(Paciente p) {
        String sql = "UPDATE Paciente SET nome=?, idade=?, tipo_sanguineo=? WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setInt(2, p.getIdade());
            ps.setString(3, p.getTipoSanguineo());
            ps.setString(4, p.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar paciente: " + e.getMessage(), e);
        }
    }

    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM Paciente WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Paciente p = new Paciente();
                p.setCpf(rs.getString("cpf"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                p.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente: " + e.getMessage(), e);
        }
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setCpf(rs.getString("cpf"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                p.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes: " + e.getMessage(), e);
        }
        return lista;
    }

    public void deletar(String cpf) {
        String sql = "DELETE FROM Paciente WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente: " + e.getMessage(), e);
        }
    }
}
