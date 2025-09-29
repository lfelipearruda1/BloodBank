package bd_bloodbank.api.servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.GerenteDAO;
import bd_bloodbank.api.domain.Gerente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gerente")
public class GerenteServlet extends HttpServlet {

    private final GerenteDAO gerenteDAO = new GerenteDAO();
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
                Gerente g = gerenteDAO.buscarPorId(id);

                if (g != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(g));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erro\":\"Gerente não encontrado\"}");
                }
            } else {
                List<Gerente> lista = gerenteDAO.listarTodos();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(lista));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"ID inválido\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao buscar gerente: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // POST → cadastrar gerente
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Gerente g = gson.fromJson(req.getReader(), Gerente.class);

            gerenteDAO.inserir(g);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"mensagem\":\"✅ Gerente cadastrado com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao cadastrar gerente: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // DELETE → excluir gerente por ID
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"Informe o ID do gerente\"}");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            gerenteDAO.deletar(id);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Gerente removido com sucesso!\"}");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"ID inválido\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao excluir gerente: " + e.getMessage() + "\"}");
        }
    }
}
