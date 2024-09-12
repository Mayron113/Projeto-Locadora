package controller;

import model.Cliente;
import database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtém os dados enviados pelo formulário (via POST)
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");

        // Cria um novo objeto Cliente com os dados do formulário
        Cliente cliente = new Cliente(nome, endereco, telefone, email);

        // Adiciona o cliente ao banco de dados simulado (lista de clientes)
        Database.adicionarCliente(cliente);

        // Redireciona o usuário de volta à página cliente.jsp (ou pode exibir uma mensagem de sucesso)
        response.sendRedirect("views/cliente.jsp");
    }
}

