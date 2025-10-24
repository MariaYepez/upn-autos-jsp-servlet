package com.upn.autos.dao;

import com.upn.autos.model.Auto;

import java.util.List;

public interface AutoDao {
    List<Auto> listar();
    void registrar(Auto auto);
    void eliminar(int id);
}
