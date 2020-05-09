package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Ficha Veterinaria
 *
 * @author Julio Cerna Medina
 */
public class Ficha {

    /**
     * Numero de ficha
     */
    private final Long numero;

    /**
     * Nombre del paciente
     */
    private final String nombrePaciente;

    /**
     * Especie del animal, ej: canino
     */
    private final String especie;

    /**
     * Raza del animal, ej: rottweiler
     */
    private final String raza;

    /**
     * Fecha de nacimiento
     */
    private final ZonedDateTime fechaNacimiento;

    /**
     * Sexo del animal
     */
    private final Sexo sexo;

    /**
     * Color: rojo cobrizo
     */
    private final String color;

    /**
     * Tipo: interno/externo
     */
    private final Tipo tipo;

    /**
     * The constructor
     *
     * @param numero to use
     * @param nombrePaciente to use
     * @param especie to use
     * @param raza to use
     * @param fechaNacimiento to use
     * @param sexo to use
     * @param color to use
     * @param tipo to use
     */
    public Ficha(Long numero, String nombrePaciente, String especie, String raza, ZonedDateTime fechaNacimiento, Sexo sexo, String color, Tipo tipo) {
        // TODO: Agregar validaciones
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
    }

    /**
     * @return the numero de ficha
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @return the nombre del paciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * @return the especie
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @return the fecha de nacimiento
     */
    public ZonedDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return the sexo
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

}
