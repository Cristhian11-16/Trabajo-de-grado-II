package com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reserva;
    private long id_cancha;
    private long id_usuario;
    private LocalDate fecha;
    //@JsonFormat(pattern = "hh:mm:ss a")
    private int hora;
    private double precio;
    private String estado;
    private int num_cancha;

    //Crear reserva

    public Reserva(long id_reserva, long id_cancha, long id_usuario, LocalDate fecha, int hora, double precio, String estado, int num_cancha) {
        this.id_reserva = id_reserva;
        this.id_cancha = id_cancha;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.estado = estado;
        this.num_cancha = num_cancha;
    }

    //Actualizar reserva


    public Reserva(long id_cancha, long id_usuario, LocalDate fecha, int hora, double precio, String estado, int num_cancha) {
        this.id_cancha = id_cancha;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.estado = estado;
        this.num_cancha = num_cancha;
    }

    public int getNum_cancha() {
        return num_cancha;
    }

    public void setNum_cancha(int num_cancha) {
        this.num_cancha = num_cancha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public long getId_cancha() {
        return id_cancha;
    }

    public void setId_cancha(long id_cancha) {
        this.id_cancha = id_cancha;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
    public Reserva(){
        super();
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id_reserva=" + id_reserva +
                ", id_cancha=" + id_cancha +
                ", id_usuario=" + id_usuario +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                ", num_cancha=" + num_cancha +
                '}';
    }
}
