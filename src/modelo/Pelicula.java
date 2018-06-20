/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Pelicula {
    private int id;
    private String nombre;
    private String director;
    private String clasificacion;
    private String pais;
    private int año;

    public Pelicula() {
    }
    
    

    public Pelicula(int id, String nombre, String director, String clasificacion, String pais, int año) {
        this.id = id;
        this.director = director;
        this.clasificacion = clasificacion;
        this.pais = pais;
        this.año = año;
    }
    
    public Pelicula(String nombre, String director, String clasificacion, String pais, int año) {
        this.director = director;
        this.clasificacion= clasificacion;
        this.pais = pais;
        this.año = año;
    }
    public Pelicula(String director, String clasificacion, String pais, int año) {
        this.director = director;
        this.clasificacion= clasificacion;
        this.pais = pais;
        this.año = año;
    }
    
    public Pelicula(String clasificacion, String pais, int año) {
        this.clasificacion = clasificacion;
        this.pais = pais;
        this.año = año;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    
    
    
}
