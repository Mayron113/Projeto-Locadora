<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Veículo</title>
</head>
<body>
    <h1>Cadastrar Novo Veículo</h1>
    <form action="veiculo" method="post">
        Marca: <input type="text" name="marca" required><br>
        Modelo: <input type="text" name="modelo" required><br>
        Ano: <input type="number" name="ano" required><br>
        Cor: <input type="text" name="cor" required><br>
        Placa: <input type="text" name="placa" required><br>
        <input type="submit" value="Cadastrar Veículo">
    </form>
</body>
</html>
