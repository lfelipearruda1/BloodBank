package bd_bloodbank.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd_bloodbank.api.domain.IndicadoPor;

public class IndicadoPorDAO {

    public void inserir(IndicadoPor i) {
        String sql = "INSERT INTO Indicado_por (fk_Doador_CPF, fk_Doador_CPF2) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, i.getCpfIndicador());
            ps.setString(2, i.getCpfIndicado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir indicação: " + e.getMessage(), e);
        }
    }

    public void deletar(IndicadoPor i) {
        String sql = "DELETE FROM Indicado_por WHERE fk_Doador_CPF=? AND fk_Doador_CPF2=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, i.getCpfIndicador());
            ps.setString(2, i.getCpfIndicado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar indicação: " + e.getMessage(), e);
        }
    }

    public List<IndicadoPor> listarTodos() {
        List<IndicadoPor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Indicado_por";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                IndicadoPor i = new IndicadoPor();
                i.setCpfIndicador(rs.getString("fk_Doador_CPF"));
                i.setCpfIndicado(rs.getString("fk_Doador_CPF2"));
                lista.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar indicações: " + e.getMessage(), e);
        }
        return lista;
    }

    public List<IndicadoPor> listarPorIndicador(String cpfIndicador) {
        List<IndicadoPor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Indicado_por WHERE fk_Doador_CPF=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpfIndicador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IndicadoPor i = new IndicadoPor();
                i.setCpfIndicador(rs.getString("fk_Doador_CPF"));
                i.setCpfIndicado(rs.getString("fk_Doador_CPF2"));
                lista.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar indicações do doador: " + e.getMessage(), e);
        }
        return lista;
    }
}
