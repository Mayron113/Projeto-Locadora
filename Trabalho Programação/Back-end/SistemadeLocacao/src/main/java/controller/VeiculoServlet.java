package controller;

import model.Veiculo;
import database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/veiculo")
public class VeiculoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtém os dados enviados pelo formulário (via POST)
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        int ano = Integer.parseInt(request.getParameter("ano"));
        String cor = request.getParameter("cor");
        String placa = request.getParameter("placa");

        // Cria um novo objeto Veiculo com os dados do formulário
        Veiculo veiculo = new Veiculo(marca, modelo, ano, cor, placa);

        // Adiciona o veículo ao banco de dados simulado (lista de veículos)
        Database.adicionarVeiculo(veiculo);

        // Redireciona o usuário de volta à página veiculo.jsp (ou pode exibir uma mensagem de sucesso)
        response.sendRedirect("views/veiculo.jsp");
    }
}
