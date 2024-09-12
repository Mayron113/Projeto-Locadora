package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Veiculo;
import model.Reserva;

public class Database {

    // Método para adicionar um cliente ao banco de dados
    public static void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os clientes do banco de dados
    public static List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Método para adicionar um veículo ao banco de dados
    public static void adicionarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO veiculos (marca, modelo, ano, cor, placa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getPlaca());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os veículos do banco de dados
    public static List<Veiculo> listarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getString("cor"),
                    rs.getString("placa")
                );
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    // Método para adicionar uma reserva ao banco de dados
    public static void adicionarReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas (cliente_id, veiculo_id, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Supondo que o Cliente e o Veículo já estejam cadastrados no banco de dados e tenham IDs
            stmt.setInt(1, buscarClienteId(reserva.getCliente()));
            stmt.setInt(2, buscarVeiculoId(reserva.getVeiculo()));
            stmt.setDate(3, new java.sql.Date(reserva.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(reserva.getDataFim().getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as reservas do banco de dados
    public static List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = buscarClientePorId(rs.getInt("cliente_id"));
                Veiculo veiculo = buscarVeiculoPorId(rs.getInt("veiculo_id"));
                Reserva reserva = new Reserva(
                    cliente,
                    veiculo,
                    rs.getDate("data_inicio"),
                    rs.getDate("data_fim")
                );
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    // Método auxiliar para buscar o ID de um cliente pelo nome
    private static int buscarClienteId(Cliente cliente) throws SQLException {
        String sql = "SELECT id FROM clientes WHERE nome = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Cliente não encontrado.");
            }
        }
    }

    // Método auxiliar para buscar o ID de um veículo pela placa
    private static int buscarVeiculoId(Veiculo veiculo) throws SQLException {
        String sql = "SELECT id FROM veiculos WHERE placa = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Veículo não encontrado.");
            }
        }
    }

    // Método auxiliar para buscar um cliente pelo ID
    private static Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            } else {
                throw new SQLException("Cliente não encontrado.");
            }
        }
    }

    // Método auxiliar para buscar um veículo pelo ID
    private static Veiculo buscarVeiculoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getString("cor"),
                    rs.getString("placa")
                );
            } else {
                throw new SQLException("Veículo não encontrado.");
            }
        }
    }
}
