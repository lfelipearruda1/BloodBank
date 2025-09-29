package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Doador;

public class DoadorDAO {

    public void inserir(Doador d) {
        String sql = "INSERT INTO Doador (cpf, nome, idade, sexo, tipo_sanguineo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getCpf());
            ps.setString(2, d.getNome());
            ps.setInt(3, d.getIdade());
            ps.setString(4, d.getSexo());
            ps.setString(5, d.getTipoSanguineo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir doador: " + e.getMessage(), e);
        }
    }

    public void atualizar(Doador d) {
        String sql = "UPDATE Doador SET nome=?, idade=?, sexo=?, tipo_sanguineo=? WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNome());
            ps.setInt(2, d.getIdade());
            ps.setString(3, d.getSexo());
            ps.setString(4, d.getTipoSanguineo());
            ps.setString(5, d.getCpf());

            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new RuntimeException("Nenhum doador encontrado com o CPF informado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar doador: " + e.getMessage(), e);
        }
    }

    public List<Doador> listarTodos() {
        List<Doador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Doador";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doador d = new Doador();
                d.setCpf(rs.getString("cpf"));
                d.setNome(rs.getString("nome"));
                d.setIdade(rs.getInt("idade"));
                d.setSexo(rs.getString("sexo"));
                d.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar doadores: " + e.getMessage(), e);
        }
        return lista;
    }

    public void deletar(String cpf) {
        String sql = "DELETE FROM Doador WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar doador: " + e.getMessage(), e);
        }
    }

    public Doador buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM Doador WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Doador(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("sexo"),
                        rs.getString("tipo_sanguineo")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar doador por CPF: " + e.getMessage(), e);
        }
        return null;
    }
}
