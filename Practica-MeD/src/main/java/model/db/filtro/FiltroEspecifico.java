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
public class FiltroEspecifico implements Filtro {

    private Long id;
    private String nome;
    private String datos;
    
    @Override
    public void aplicar(Busqueda b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
