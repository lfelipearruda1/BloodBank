package bd_bloodbank.api.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bd_bloodbank.api.dao.DoadorDAO;
import bd_bloodbank.api.domain.Doador;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/doador")
public class DoadorServlet extends HttpServlet {

    private final DoadorDAO doadorDAO = new DoadorDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            Doador d = gson.fromJson(reader, Doador.class);
            doadorDAO.inserir(d);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Doador cadastrado com sucesso!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"error\":\"Erro ao cadastrar doador: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Doador> doadores = doadorDAO.listarTodos();
        String json = gson.toJson(doadores);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            Doador d = gson.fromJson(reader, Doador.class);

            if (d.getCpf() == null || d.getCpf().isBlank()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"CPF é obrigatório para atualização\"}");
                return;
            }

            doadorDAO.atualizar(d);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Doador atualizado com sucesso!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"error\":\"Erro ao atualizar doador: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cpf = req.getParameter("cpf");
        if (cpf == null || cpf.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erro\": \"CPF é obrigatório\"}");
            return;
        }

        doadorDAO.deletar(cpf);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
