package bd_bloodbank.api.servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.FuncionarioDAO;
import bd_bloodbank.api.domain.Funcionario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/funcionario")
public class FuncionarioServlet extends HttpServlet {
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final Gson gson = new Gson();

    // =========================
    // GET → listar todos ou buscar por ID
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            String idParam = req.getParameter("id");

            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                Funcionario f = funcionarioDAO.buscarPorId(id);

                if (f != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(f));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erro\":\"❌ Funcionário não encontrado\"}");
                }
            } else {
                List<Funcionario> lista = funcionarioDAO.listarTodos();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(lista));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"ID inválido\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao buscar funcionário: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // POST → cadastrar funcionário
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Funcionario f = gson.fromJson(req.getReader(), Funcionario.class);

            funcionarioDAO.inserir(f);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"mensagem\":\"✅ Funcionário cadastrado com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao cadastrar funcionário: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // PUT → atualizar funcionário
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Funcionario f = gson.fromJson(req.getReader(), Funcionario.class);

            funcionarioDAO.atualizar(f);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Funcionário atualizado com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao atualizar funcionário: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // DELETE → excluir funcionário por ID
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"Informe o ID para exclusão\"}");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            funcionarioDAO.deletar(id);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Funcionário removido com sucesso!\"}");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"ID inválido\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao excluir funcionário: " + e.getMessage() + "\"}");
        }
    }
}
