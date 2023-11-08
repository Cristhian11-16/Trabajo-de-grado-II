package com.trabajodegrado.ucatolica.TrabajoGradoII.Precios;

import jakarta.persistence.*;

@Entity
@Table
public class Precios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_precio;
    private long id_cancha;
    private String dia;
    private int hora;
    private double precio;
    //Crear precio

    public Precios(long id_precio, long id_cancha, String dia, int hora, double precio) {
        this.id_precio = id_precio;
        this.id_cancha = id_cancha;
        this.dia = dia;
        this.hora = hora;
        this.precio = precio;
    }

    //Modificar precios
    public Precios(String dia, int hora, double precio) {
        this.dia = dia;
        this.hora = hora;
        this.precio = precio;
    }

    public long getId_cancha() {
        return id_cancha;
    }

    public void setId_cancha(long id_cancha) {
        this.id_cancha = id_cancha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Precios(){
        super();
    }

    @Override
    public String toString() {
        return "Precios{" +
                "id_precio=" + id_precio +
                ", id_cancha=" + id_cancha +
                ", dia='" + dia + '\'' +
                ", hora=" + hora +
                ", precio=" + precio +
                '}';
    }
}
