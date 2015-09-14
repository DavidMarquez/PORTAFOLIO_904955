/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portafolio.jsf.controllers;

import com.example.portafolio.jpa.entities.Ciudad;
import com.example.portafolio.jpa.entities.Departamento;
import com.example.portafolio.jpa.entities.Rol;
import com.example.portafolio.jpa.entities.Usuario;
import com.example.portafolio.jpa.sessions.UsuarioSession;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leoandresm
 */
@ManagedBean
@ViewScoped
public class UsuarioController {

    @EJB
    private UsuarioSession usuarioSession;

    private Usuario selectedUsuario;
    private int idCiudad;
    private int idDepartamento;
    private String idRol;
    private List<Usuario> itemsUsuario = null;

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    public Usuario getSelectedUsuario() {
        if (selectedUsuario == null) {
            selectedUsuario = new Usuario();
        }
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getIdCiudad() {
        return idCiudad + "," + idDepartamento;
    }

    public void setIdCiudad(String idCiudad) {
        StringTokenizer tokens = new StringTokenizer(idCiudad, ",");
        this.idCiudad = Integer.parseInt(tokens.nextToken());
        idDepartamento = Integer.parseInt(tokens.nextToken());
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public UsuarioSession getUsuarioSession() {
        return usuarioSession;
    }

    public List<Usuario> getItemsUsuario() {
        if (itemsUsuario == null) {
            try {
                itemsUsuario = getUsuarioSession().findAll();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return itemsUsuario;
    }

    public void create() {
        try {
            selectedUsuario.setCiudad(new Ciudad(idCiudad, new Departamento(idDepartamento)));
            selectedUsuario.setRol(new Rol(idRol));
            getUsuarioSession().create(selectedUsuario);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
