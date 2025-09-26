package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.GerenteHospital;

public class GerenteHospitalDAO {

    public void inserir(GerenteHospital g) {
        String sql = "INSERT INTO Gerente_Hospital_Hospit (nome, cnpj, fk_Telefone_Telefone_PK, endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, g.getNome());
            ps.setString(2, g.getCnpj());
            ps.setInt(3, g.getTelefoneId());
            ps.setString(4, g.getEndereco());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir hospital/gerente: " + e.getMessage(), e);
        }
    }

    public void atualizar(GerenteHospital g) {
        String sql = "UPDATE Gerente_Hospital_Hospit SET nome=?, fk_Telefone_Telefone_PK=?, endereco=? WHERE cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, g.getNome());
            ps.setInt(2, g.getTelefoneId());
            ps.setString(3, g.getEndereco());
            ps.setString(4, g.getCnpj());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar hospital/gerente: " + e.getMessage(), e);
        }
    }

    public GerenteHospital buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM Gerente_Hospital_Hospit WHERE cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cnpj);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GerenteHospital g = new GerenteHospital();
                g.setIdGerente(rs.getInt("id_gerente"));
                g.setNome(rs.getString("nome"));
                g.setCnpj(rs.getString("cnpj"));
                g.setTelefoneId(rs.getInt("fk_Telefone_Telefone_PK"));
                g.setEndereco(rs.getString("endereco"));
                return g;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar hospital/gerente: " + e.getMessage(), e);
        }
    }

    public List<GerenteHospital> listarTodos() {
        String sql = "SELECT * FROM Gerente_Hospital_Hospit";
        List<GerenteHospital> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GerenteHospital g = new GerenteHospital();
                g.setIdGerente(rs.getInt("id_gerente"));
                g.setNome(rs.getString("nome"));
                g.setCnpj(rs.getString("cnpj"));
                g.setTelefoneId(rs.getInt("fk_Telefone_Telefone_PK"));
                g.setEndereco(rs.getString("endereco"));
                lista.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar hospitais/gerentes: " + e.getMessage(), e);
        }
        return lista;
    }

    public void deletar(String cnpj) {
        String sql = "DELETE FROM Gerente_Hospital_Hospit WHERE cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cnpj);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar hospital/gerente: " + e.getMessage(), e);
        }
    }
}
