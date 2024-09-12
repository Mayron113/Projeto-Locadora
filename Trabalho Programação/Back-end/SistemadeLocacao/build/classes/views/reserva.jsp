<!DOCTYPE html>
<html>
<head>
    <title>Reservar Veículo</title>
</head>
<body>
    <h1>Reservar Veículo</h1>
    <form action="reserva" method="post">
        Cliente (Nome): <input type="text" name="cliente" required><br>
        Veículo (Placa): <input type="text" name="veiculo" required><br>
        Data de Início: <input type="date" name="dataInicio" required><br>
        Data de Fim: <input type="date" name="dataFim" required><br>
        <input type="submit" value="Reservar Veículo">
    </form>
</body>
</html>
