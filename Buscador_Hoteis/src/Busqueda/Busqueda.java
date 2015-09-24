/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import java.util.List;

/**
 *
 * @author Urist
 */
public class Busqueda {

    private Long id;
    private List<FiltroXeral> filtros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FiltroXeral> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<FiltroXeral> filtros) {
        this.filtros = filtros;
    }

}
