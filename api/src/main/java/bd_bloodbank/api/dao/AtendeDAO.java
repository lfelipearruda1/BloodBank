package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Atende;

public class AtendeDAO {

    public void inserir(Atende a) {
        String sql = "INSERT INTO Atende (fk_Gerente_Hospital_Hospit, fk_Paciente_Cpf) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getIdGerente());
            ps.setString(2, a.getCpfPaciente());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir atendimento: " + e.getMessage(), e);
        }
    }

    public void deletar(Atende a) {
        String sql = "DELETE FROM Atende WHERE fk_Gerente_Hospital_Hospit=? AND fk_Paciente_Cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getIdGerente());
            ps.setString(2, a.getCpfPaciente());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar atendimento: " + e.getMessage(), e);
        }
    }

    public List<Atende> listarTodos() {
        List<Atende> lista = new ArrayList<>();
        String sql = "SELECT * FROM Atende";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Atende a = new Atende();
                a.setIdGerente(rs.getInt("fk_Gerente_Hospital_Hospit"));
                a.setCpfPaciente(rs.getString("fk_Paciente_Cpf"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar atendimentos: " + e.getMessage(), e);
        }
        return lista;
    }

    public List<Atende> listarPorGerente(int idGerente) {
        List<Atende> lista = new ArrayList<>();
        String sql = "SELECT * FROM Atende WHERE fk_Gerente_Hospital_Hospit=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idGerente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Atende a = new Atende();
                a.setIdGerente(rs.getInt("fk_Gerente_Hospital_Hospit"));
                a.setCpfPaciente(rs.getString("fk_Paciente_Cpf"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar atendimentos do gerente: " + e.getMessage(), e);
        }
        return lista;
    }
}
