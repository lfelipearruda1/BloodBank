package bd_bloodbank.api.servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.BolsaSangueDAO;
import bd_bloodbank.api.domain.BolsaSangue;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bolsas")
public class BolsaSangueServlet extends HttpServlet {

    private final BolsaSangueDAO bolsaDAO = new BolsaSangueDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                BolsaSangue b = bolsaDAO.buscarPorId(id);
                if (b != null) {
                    resp.getWriter().write(gson.toJson(b));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erro\":\"Bolsa de sangue não encontrada\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"ID inválido\"}");
            }
        } else {
            List<BolsaSangue> lista = bolsaDAO.listarTodas();
            resp.getWriter().write(gson.toJson(lista));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                bolsaDAO.deletar(id);
                resp.getWriter().write("{\"status\":\"✅ Bolsa removida (se existia)\"}");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erro\":\"ID inválido\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\":\"Informe o ID da bolsa\"}");
        }
    }
}
