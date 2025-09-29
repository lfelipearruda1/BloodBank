package bd_bloodbank.api.servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.EstoqueDAO;
import bd_bloodbank.api.domain.Estoque;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/estoque")
public class EstoqueServlet extends HttpServlet {

    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final Gson gson = new Gson();

    // =========================
    // GET → listar todos ou buscar por tipo
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            String tipo = req.getParameter("tipo");

            if (tipo != null) {
                Estoque e = estoqueDAO.buscarPorTipo(tipo);
                if (e != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(e));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erro\":\"❌ Estoque não encontrado para o tipo informado\"}");
                }
            } else {
                List<Estoque> lista = estoqueDAO.listarTodos();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(lista));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"❌ Erro ao buscar estoque: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // POST → inserir novo tipo de estoque
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Estoque e = gson.fromJson(req.getReader(), Estoque.class);

            if (e.getTipoSanguineo() == null || e.getTipoSanguineo().isBlank()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"Tipo sanguíneo é obrigatório\"}");
                return;
            }

            estoqueDAO.inserir(e);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"mensagem\":\"✅ Estoque inserido com sucesso\"}");
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"❌ Erro ao inserir estoque: " + ex.getMessage() + "\"}");
        }
    }

    // =========================
    // PUT → atualizar qtd de um tipo existente
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Estoque e = gson.fromJson(req.getReader(), Estoque.class);

            if (e.getTipoSanguineo() == null || e.getTipoSanguineo().isBlank()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"Tipo sanguíneo é obrigatório\"}");
                return;
            }

            estoqueDAO.atualizar(e);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Estoque atualizado com sucesso\"}");
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"❌ Erro ao atualizar estoque: " + ex.getMessage() + "\"}");
        }
    }

    // =========================
    // DELETE → remover bolsas de um tipo existente
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            String tipo = req.getParameter("tipo");
            String qtdStr = req.getParameter("qtd"); // quantidade a remover

            if (tipo == null || tipo.isBlank() || qtdStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"Informe tipo e qtd para exclusão\"}");
                return;
            }

            int qtd = Integer.parseInt(qtdStr);
            Estoque e = estoqueDAO.buscarPorTipo(tipo);

            if (e == null || e.getQtdDisponivel() < qtd) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"❌ Estoque insuficiente ou tipo não encontrado\"}");
                return;
            }

            e.setQtdDisponivel(e.getQtdDisponivel() - qtd);
            estoqueDAO.atualizar(e);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ " + qtd + " bolsas removidas de " + tipo + "\"}");
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"Qtd inválida\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"❌ Erro ao excluir estoque: " + e.getMessage() + "\"}");
        }
    }
}
