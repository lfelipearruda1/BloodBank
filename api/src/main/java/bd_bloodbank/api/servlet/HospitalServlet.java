package bd_bloodbank.api.servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.HospitalDAO;
import bd_bloodbank.api.domain.Hospital;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hospital")
public class HospitalServlet extends HttpServlet {

    private final HospitalDAO hospitalDAO = new HospitalDAO();
    private final Gson gson = new Gson();

    // =========================
    // GET → listar todos ou buscar por CNPJ
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            String cnpj = req.getParameter("cnpj");

            if (cnpj != null) {
                Hospital h = hospitalDAO.buscarPorCnpj(cnpj);
                if (h != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(h));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erro\":\"Hospital não encontrado\"}");
                }
            } else {
                List<Hospital> lista = hospitalDAO.listarTodos();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(lista));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao buscar hospital: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // POST → cadastrar hospital
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Hospital h = gson.fromJson(req.getReader(), Hospital.class);
            hospitalDAO.inserir(h);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"mensagem\":\"✅ Hospital cadastrado com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao cadastrar hospital: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // PUT → atualizar hospital
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            Hospital h = gson.fromJson(req.getReader(), Hospital.class);

            if (h.getCnpj() == null || h.getCnpj().isBlank()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"CNPJ é obrigatório para atualização\"}");
                return;
            }

            hospitalDAO.atualizar(h);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Hospital atualizado com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao atualizar hospital: " + e.getMessage() + "\"}");
        }
    }

    // =========================
    // DELETE → excluir hospital por CNPJ
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String cnpj = req.getParameter("cnpj");

        if (cnpj == null || cnpj.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"Informe o CNPJ do hospital\"}");
            return;
        }

        try {
            hospitalDAO.deletar(cnpj);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"mensagem\":\"✅ Hospital removido com sucesso!\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"erro\":\"Erro ao excluir hospital: " + e.getMessage() + "\"}");
        }
    }
}
