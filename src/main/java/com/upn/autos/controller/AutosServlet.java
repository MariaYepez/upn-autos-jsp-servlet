package com.upn.autos.controller;

import com.upn.autos.dao.AutoDao;
import com.upn.autos.dao.AutoDaoImpl;
import com.upn.autos.model.Auto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Year;
import java.util.List;

@WebServlet("/autos")
public class AutosServlet extends HttpServlet {

    private final AutoDao dao = new AutoDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Auto> lista = dao.listar();
        request.setAttribute("listarAutos", lista);
        request.getRequestDispatcher("autos.jsp").forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        int anio = Integer.parseInt(req.getParameter("anio"));
        double precio = Double.parseDouble(req.getParameter("precio"));

        int anioActual = Year.now().getValue();
        if (marca.isEmpty() || modelo.isEmpty() || anio < 1950 || anio > anioActual + 1 || precio < 0) {
            req.setAttribute("error", "Datos inválidos. Verifica los campos.");
            req.getRequestDispatcher("nuevoAuto.jsp").forward(req, resp);
            return;
        }

        Auto auto = Auto.builder()
                .marca(marca)
                .modelo(modelo)
                .anio(anio)
                .precio(precio)
                .build();

        dao.registrar(auto);
        resp.sendRedirect("autos");
    }
}