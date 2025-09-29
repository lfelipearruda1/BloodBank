package bd_bloodbank.api.servlet;

import java.io.IOException;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.BolsaSangueDAO;
import bd_bloodbank.api.dao.DoadorDAO;
import bd_bloodbank.api.dao.EstoqueDAO;
import bd_bloodbank.api.domain.BolsaSangue;
import bd_bloodbank.api.domain.Doador;
import bd_bloodbank.api.domain.Estoque;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/doacao")
public class DoacaoServlet extends HttpServlet {
    private final DoadorDAO doadorDAO = new DoadorDAO();
    private final BolsaSangueDAO bolsaDAO = new BolsaSangueDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            DoacaoRequest request = gson.fromJson(req.getReader(), DoacaoRequest.class);

            // Verifica se o doador existe
            Doador d = doadorDAO.buscarPorCpf(request.cpfDoador);
            if (d == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"❌ Doador não encontrado!\"}");
                return;
            }

            // Cria bolsas
            for (int i = 0; i < request.qtd; i++) {
                BolsaSangue b = new BolsaSangue(0, d.getTipoSanguineo(), d.getCpf());
                bolsaDAO.inserir(b);
            }

            // Atualiza estoque
            Estoque e = estoqueDAO.buscarPorTipo(d.getTipoSanguineo());
            if (e == null) {
                e = new Estoque(d.getTipoSanguineo(), request.qtd);
                estoqueDAO.inserir(e);
            } else {
                e.setQtdDisponivel(e.getQtdDisponivel() + request.qtd);
                estoqueDAO.atualizar(e);
            }

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"mensagem\":\"✅ Doação registrada com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro interno: " + e.getMessage() + "\"}");
        }
    }

    // Classe auxiliar para receber dados no POST
    private static class DoacaoRequest {
        String cpfDoador;
        int qtd;
    }
}
