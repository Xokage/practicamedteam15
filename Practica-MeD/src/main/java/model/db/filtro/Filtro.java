/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.model.db.filtro;

import main.java.model.db.busqueda.Busqueda;

/**
 *
 * @author Urist
 */
public interface Filtro {

    public void aplicar(Busqueda b);

}
