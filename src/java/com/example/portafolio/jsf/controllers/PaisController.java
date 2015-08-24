package com.example.portafolio.jsf.controllers;

import com.example.portafolio.jpa.entities.Pais;
import com.example.portafolio.jpa.sessions.PaisSession;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leoandresm
 */
@ManagedBean
@ViewScoped
public class PaisController implements Serializable {
    
    @EJB //Enterprise Java Beans
    private PaisSession paisSession;
    
    private Pais selectedPais;
    private List<Pais> itemsPais = null;

    /**
     * Creates a new instance of PaisController
     */
    public PaisController() {
    }

    public Pais getSelectedPais() {
        if (selectedPais == null) {
            selectedPais = new Pais();
        }
        return selectedPais;
    }

    public void setSelectedPais(Pais selectedPais) {
        this.selectedPais = selectedPais;
    }

    public PaisSession getPaisSession() {
        return paisSession;
    }
    
    public void create () {
        try {
            getPaisSession().create(selectedPais);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Pais> getItemsPais() {
        if (itemsPais == null) {
            try {
                itemsPais = getPaisSession().findAll();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return itemsPais;
    }    
}
