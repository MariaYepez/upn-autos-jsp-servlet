<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Autos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light p-4">
<div class="container">
    <h1 class="text-center mb-4">Listado de Autos</h1>

    <a href="nuevoAuto.jsp" class="btn btn-primary mb-3">+ Registrar Auto</a>

    <c:choose>
        <c:when test="${empty listarAutos}">
            <div class="alert alert-warning text-center">No hay autos registrados.</div>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Año</th>
                    <th>Precio ($)</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${listarAutos}">
                    <tr>
                        <td>${a.id}</td>
                        <td>${a.marca}</td>
                        <td>${a.modelo}</td>
                        <td>${a.anio}</td>
                        <td>$${a.precio}</td>
                        <td>
                            <a href="autos?action=eliminar&id=${a.id}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('¿Estás seguro de eliminar este auto?')">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>