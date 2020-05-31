/*
 * MIT License
 *
 * Copyright (c) 2020 Julio Cerna Medina <julio.cerna@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/

package cl.ucn.disc.pdbp.tdd.model;

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ficha Veterinaria
 *
 * @author Julio Cerna Medina
 */
@SuppressWarnings("ClassWithTooManyFields")
@DatabaseTable(tableName = "ficha")
public class Ficha {

    /**
     * The id
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * Numero de ficha //
     */
    @DatabaseField(unique = true)
    private Long numero;

    /**
     * Nombre del paciente
     */
    @DatabaseField(canBeNull = false)
    private String nombrePaciente;

    /**
     * Especie del animal, ej: canino
     */
    @DatabaseField(canBeNull = false)
    private String especie;

    /**
     * Raza del animal, ej: rottweiler
     */
    @DatabaseField
    private String raza;

    /**
     * Fecha de nacimiento
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fechaNacimiento;

    /**
     * Sexo del animal
     */
    @DatabaseField(canBeNull = false)
    private Sexo sexo;

    /**
     * Color: rojo cobrizo
     */
    @DatabaseField
    private String color;

    /**
     * Tipo: interno/externo
     */
    @DatabaseField(canBeNull = false)
    private Tipo tipo;

    /**
     * The duenio
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona duenio;

    /**
     * The List of Control
     */
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Control> controles;

    /**
     * The empty constructor
     */
    Ficha () {
        // Nothing here...
    }

    /**
     * The constructor
     *
     * @param numero            of the ficha
     * @param nombrePaciente    of the paciente
     * @param especie           of the pet
     * @param raza              of the pet
     * @param fechaNacimiento   of the pet
     * @param sexo              of the pet
     * @param color             of the pet
     * @param tipo              of paciente
     * @param duenio            of the pet
     */
    public Ficha(Long numero, String nombrePaciente, String especie, String raza, ZonedDateTime fechaNacimiento, Sexo sexo, String color, Tipo tipo, Persona duenio) {
        // TODO: Agregar validaciones
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
        this.duenio = duenio;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
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

    /**
     * @return the duenio
     */
    public Persona getDuenio() {
        return duenio;
    }

    /**
     * @return the List of Controles
     */
    public List<Control> getControles() {
        return Collections.unmodifiableList(new ArrayList<>(controles));
    }

}
