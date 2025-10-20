package com.upn.autos.dao;

import com.upn.autos.model.Auto;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class AutoDaoImpl implements AutoDao {

    private Connection obtenerConexion() throws Exception {
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");
        props.load(input);
        Class.forName(props.getProperty("db.driver"));
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
    }

    @Override
    public List<Auto> listar() {
        List<Auto> lista = new ArrayList<>();
        String sql = "SELECT * FROM auto";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Auto a = new Auto();
                a.setId(rs.getInt("id"));
                a.setMarca(rs.getString("marca"));
                a.setModelo(rs.getString("modelo"));
                a.setAnio(rs.getInt("anio"));
                a.setPrecio(rs.getDouble("precio"));
                lista.add(a);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return lista;
    }

    @Override
    public void registrar(Auto auto) {
        String sql = "INSERT INTO auto (marca, modelo, anio, precio) VALUES (?, ?, ?, ?)";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, auto.getMarca());
            ps.setString(2, auto.getModelo());
            ps.setInt(3, auto.getAnio());
            ps.setDouble(4, auto.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
