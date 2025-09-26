package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.Recebe;

public class RecebeDAO {

    public void inserir(Recebe r) {
        String sql = "INSERT INTO Recebe (fk_Bolsa_Sangue_id_bolsa, fk_Hospital_Cnpj) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getIdBolsa());
            ps.setString(2, r.getCnpjHospital());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar recebimento: " + e.getMessage(), e);
        }
    }

    public void deletar(Recebe r) {
        String sql = "DELETE FROM Recebe WHERE fk_Bolsa_Sangue_id_bolsa=? AND fk_Hospital_Cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getIdBolsa());
            ps.setString(2, r.getCnpjHospital());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar recebimento: " + e.getMessage(), e);
        }
    }

    public List<Recebe> listarTodos() {
        List<Recebe> lista = new ArrayList<>();
        String sql = "SELECT * FROM Recebe";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Recebe r = new Recebe();
                r.setIdBolsa(rs.getInt("fk_Bolsa_Sangue_id_bolsa"));
                r.setCnpjHospital(rs.getString("fk_Hospital_Cnpj"));
                lista.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar recebimentos: " + e.getMessage(), e);
        }
        return lista;
    }

    public List<Recebe> listarPorHospital(String cnpj) {
        List<Recebe> lista = new ArrayList<>();
        String sql = "SELECT * FROM Recebe WHERE fk_Hospital_Cnpj=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cnpj);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recebe r = new Recebe();
                r.setIdBolsa(rs.getInt("fk_Bolsa_Sangue_id_bolsa"));
                r.setCnpjHospital(rs.getString("fk_Hospital_Cnpj"));
                lista.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar recebimentos do hospital: " + e.getMessage(), e);
        }
        return lista;
    }
}
