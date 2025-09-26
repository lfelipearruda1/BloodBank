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
        String sql = "INSERT INTO Doador (cpf, nome, sobrenome, idade, sexo, tipo_sanguineo, qtd_bolsas_doadas, cep, rua, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getCpf());
            ps.setString(2, d.getNome());
            ps.setString(3, d.getSobrenome());
            ps.setInt(4, d.getIdade());
            ps.setString(5, d.getSexo());
            ps.setString(6, d.getTipoSanguineo());
            ps.setInt(7, d.getQtdBolsasDoadas());
            ps.setString(8, d.getCep());
            ps.setString(9, d.getRua());
            ps.setString(10, d.getCidade());
            ps.setString(11, d.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir doador: " + e.getMessage(), e);
        }
    }

    public Doador buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM Doador WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doador doador = new Doador();
                doador.setCpf(rs.getString("cpf"));
                doador.setNome(rs.getString("nome"));
                doador.setSobrenome(rs.getString("sobrenome"));
                doador.setIdade(rs.getInt("idade"));
                doador.setSexo(rs.getString("sexo"));
                doador.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                doador.setQtdBolsasDoadas(rs.getInt("qtd_bolsas_doadas"));
                doador.setCep(rs.getString("cep"));
                doador.setRua(rs.getString("rua"));
                doador.setCidade(rs.getString("cidade"));
                doador.setEstado(rs.getString("estado"));
                return doador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar doador: " + e.getMessage(), e);
        }
    }

    public List<Doador> listarTodos() {
        List<Doador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Doador";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doador doador = new Doador();
                doador.setCpf(rs.getString("cpf"));
                doador.setNome(rs.getString("nome"));
                doador.setSobrenome(rs.getString("sobrenome"));
                doador.setIdade(rs.getInt("idade"));
                doador.setSexo(rs.getString("sexo"));
                doador.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                doador.setQtdBolsasDoadas(rs.getInt("qtd_bolsas_doadas"));
                doador.setCep(rs.getString("cep"));
                doador.setRua(rs.getString("rua"));
                doador.setCidade(rs.getString("cidade"));
                doador.setEstado(rs.getString("estado"));
                lista.add(doador);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar doadores: " + e.getMessage(), e);
        }

        return lista;
    }

    public void atualizar(Doador d) {
        String sql = "UPDATE Doador SET nome=?, sobrenome=?, idade=?, sexo=?, tipo_sanguineo=?, qtd_bolsas_doadas=?, cep=?, rua=?, cidade=?, estado=? WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNome());
            ps.setString(2, d.getSobrenome());
            ps.setInt(3, d.getIdade());
            ps.setString(4, d.getSexo());
            ps.setString(5, d.getTipoSanguineo());
            ps.setInt(6, d.getQtdBolsasDoadas());
            ps.setString(7, d.getCep());
            ps.setString(8, d.getRua());
            ps.setString(9, d.getCidade());
            ps.setString(10, d.getEstado());
            ps.setString(11, d.getCpf());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar doador: " + e.getMessage(), e);
        }
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

    public void atualizarQtdBolsas(String cpf, int novaQtd) {
        String sql = "UPDATE Doador SET qtd_bolsas_doadas=? WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, novaQtd);
            ps.setString(2, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar quantidade de bolsas doadas: " + e.getMessage(), e);
        }
    }
}
