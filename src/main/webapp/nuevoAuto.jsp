<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Auto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light p-4">
<div class="container">
    <h1 class="text-center mb-4">Registrar Nuevo Auto</h1>
    <form method="post" action="autos" class="card p-4 shadow-sm">
        <div class="mb-3">
            <label class="form-label">Marca</label>
            <input type="text" name="marca" class="form-control" maxlength="60" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Modelo</label>
            <input type="text" name="modelo" class="form-control" maxlength="60" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Año</label>
            <input type="number" id="anio" name="anio" class="form-control" min="1950" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Precio</label>
            <input type="number" name="precio" step="0.01" min="0" class="form-control" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="autos" class="btn btn-secondary">Volver</a>
        </div>
    </form>
</div>

<script>
    // Asignar dinámicamente el año máximo al input
    const inputAnio = document.getElementById("anio");
    const anioActual = new Date().getFullYear();
    inputAnio.max = anioActual + 1;
</script>

</body>
</html>