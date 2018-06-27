/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author estudiante
 */
public class Pelicula {
    private int id;
    private String codigo;
    private String director;
    private String pais;
    private String marca;
    private int stock;
    private boolean existencia;
    
    public Pelicula(){
        
    }
    
    public Pelicula(int id, String codigo, String director, String pais, String marca, int stock, boolean existencia){
        this.id=id;
        this.director=director;
        this.pais=pais;
        this.marca=marca;
        this.stock=stock;
        this.existencia=existencia;
        this.codigo=codigo;
    }
    
    public Pelicula(String codigo, String director, String pais, String marca, int stock, boolean existencia){
        this.codigo=codigo;
        this.director=director;
        this.pais=pais;
        this.marca=marca;
        this.stock=stock;
        this.existencia=existencia;
    }
    
    public Pelicula(String director, String pais, String marca, int stock, boolean existencia){
        this.director=director;
        this.pais=pais;
        this.marca=marca;
        this.stock=stock;
        this.existencia=existencia;
    }
    
    public String getCodigo() {
    return this.codigo;
    }
    
    public String getDirector() {
        return this.director;
    }
    
    public String getPais(){
        return this.pais;
    }

    public String getMarca() {
    return this.marca;
    }

    public int getStock() {
    return this.stock;
    }

    public boolean getExistencia() {
    return this.existencia;
    }

    public void setExistencia(boolean b) {
    this.existencia = b;
    }
}