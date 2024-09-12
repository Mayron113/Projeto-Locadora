package controller;

import model.Cliente;
import model.Reserva;
import model.Veiculo;
import database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/reserva")
public class ReservaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtém os dados enviados pelo formulário (via POST)
        String clienteNome = request.getParameter("cliente");
        String veiculoPlaca = request.getParameter("veiculo");
        String dataInicioStr = request.getParameter("dataInicio");
        String dataFimStr = request.getParameter("dataFim");

        // Converte as strings das datas para objetos Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio = null;
        Date dataFim = null;
        try {
            dataInicio = sdf.parse(dataInicioStr);
            dataFim = sdf.parse(dataFimStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Busca o cliente pelo nome
        Cliente cliente = Database.clientes.stream()
                .filter(c -> c.getNome().equals(clienteNome))
                .findFirst()
                .orElse(null);

        // Busca o veículo pela placa
        Veiculo veiculo = Database.veiculos.stream()
                .filter(v -> v.getPlaca().equals(veiculoPlaca))
                .findFirst()
                .orElse(null);

        // Se encontrou o cliente e o veículo, cria uma nova reserva
        if (cliente != null && veiculo != null) {
            Reserva reserva = new Reserva(cliente, veiculo, dataInicio, dataFim);
            Database.adicionarReserva(reserva);
        }

        // Redireciona o usuário de volta à página reserva.jsp (ou pode exibir uma mensagem de sucesso)
        response.sendRedirect("views/reserva.jsp");
    }
}
