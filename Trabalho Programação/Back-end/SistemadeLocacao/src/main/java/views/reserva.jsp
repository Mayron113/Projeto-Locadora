<!DOCTYPE html>
<html>
<head>
    <title>Reservar Ve�culo</title>
</head>
<body>
    <h1>Reservar Ve�culo</h1>
    <form action="reserva" method="post">
        Cliente (Nome): <input type="text" name="cliente" required><br>
        Ve�culo (Placa): <input type="text" name="veiculo" required><br>
        Data de In�cio: <input type="date" name="dataInicio" required><br>
        Data de Fim: <input type="date" name="dataFim" required><br>
        <input type="submit" value="Reservar Ve�culo">
    </form>
</body>
</html>
