package bd_bloodbank.api.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.EstoqueDAO;
import bd_bloodbank.api.dao.SolicitacaoDAO;
import bd_bloodbank.api.domain.Estoque;
import bd_bloodbank.api.domain.Solicitacao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/solicitacoes")
public class SolicitacaoServlet extends HttpServlet {

    private final SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final Gson gson = new Gson();

    // =========================
    // GET → listar ou buscar por ID
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        try {
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                Solicitacao s = solicitacaoDAO.buscarPorId(id);
                if (s != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(s));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erro\":\"Solicitação não encontrada\"}");
                }
            } else {
                List<Solicitacao> lista = solicitacaoDAO.listarTodos();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(lista));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"ID inválido\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao buscar solicitações: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // POST → criar nova solicitação e dar baixa no estoque
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try (BufferedReader reader = req.getReader()) {
            Solicitacao s = gson.fromJson(reader, Solicitacao.class);

            if (s.getTipoSanguineo() == null || s.getTipoSanguineo().isBlank() || s.getQtdBolsasSolicitadas() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"Tipo sanguíneo e quantidade válidos são obrigatórios\"}");
                return;
            }

            // Verifica estoque
            Estoque e = estoqueDAO.buscarPorTipo(s.getTipoSanguineo());
            if (e == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"erro\":\"Tipo sanguíneo não encontrado no estoque\"}");
                return;
            }

            if (e.getQtdDisponivel() < s.getQtdBolsasSolicitadas()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"❌ Estoque insuficiente para atender a solicitação\"}");
                return;
            }

            // Atualiza estoque
            e.setQtdDisponivel(e.getQtdDisponivel() - s.getQtdBolsasSolicitadas());
            estoqueDAO.atualizar(e);

            // Registra solicitação
            solicitacaoDAO.inserir(s);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"status\":\"✅ Solicitação registrada e estoque atualizado\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao registrar solicitação: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // DELETE → excluir solicitação por ID
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"Informe o ID da solicitação\"}");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            solicitacaoDAO.deletar(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"status\":\"✅ Solicitação removida (se existia)\"}");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"ID inválido\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao deletar solicitação: " + e.getMessage() + "\"}");
        }
    }
}
