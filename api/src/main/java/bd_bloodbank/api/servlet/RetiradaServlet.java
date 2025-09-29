package bd_bloodbank.api.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.EstoqueDAO;
import bd_bloodbank.api.domain.Estoque;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/retirada")
public class RetiradaServlet extends HttpServlet {
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try (BufferedReader reader = req.getReader()) {
            RetiradaRequest request = gson.fromJson(reader, RetiradaRequest.class);

            // Valida entrada
            if (request.tipo == null || request.tipo.isBlank() || request.qtd <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"Informe tipo sanguíneo válido e quantidade positiva\"}");
                return;
            }

            // Verifica estoque
            Estoque e = estoqueDAO.buscarPorTipo(request.tipo);
            if (e == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"erro\":\"Tipo sanguíneo não encontrado no estoque\"}");
                return;
            }

            if (e.getQtdDisponivel() < request.qtd) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"Estoque insuficiente!\"}");
                return;
            }

            // Atualiza estoque
            e.setQtdDisponivel(e.getQtdDisponivel() - request.qtd);
            estoqueDAO.atualizar(e);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Retirada de " + request.qtd + " bolsas de " + request.tipo + " concluída!\"}");
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao processar retirada: " + ex.getMessage() + "\"}");
        }
    }

    // Classe auxiliar para receber dados no POST
    private static class RetiradaRequest {
        String tipo;
        int qtd;
    }
}
