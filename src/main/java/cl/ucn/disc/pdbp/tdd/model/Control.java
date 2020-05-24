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
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.ZonedDateTime;

/**
 * Control
 *
 * @author Julio Cerna Medina
 */
@DatabaseTable(tableName = "control")
public class Control {

    /**
     * The id
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The Fecha of the control
     */
    @DatabaseField(persisterClass  =  ZonedDateTimeType.class)
    private ZonedDateTime fecha;

    /**
     * The fecha of the next control
     */
    @DatabaseField(persisterClass  =  ZonedDateTimeType.class)
    private ZonedDateTime fechaProximoControl;

    /**
     * The temperatura (°C)
     *
     * Min: ... // TODO: Establecer parametros de la temperatura
     * Max: ...
     */
    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float temperatura;

    /**
     * The peso (kg)
     *
     * Min: ... // TODO: Establecer parametros del peso
     * Max: ...
     */
    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float peso;

    /**
     * The altura (cm)
     *
     * Min: 1
     * Max: ... // TODO: verificar la altura maxima
     */
    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float altura;

    /**
     * The diagnostico
     */
    @DatabaseField(canBeNull = false)
    private String diagnostico;

    /**
     * The veterinario
     *
     * Nota: Vet can perform many checks
     */
    @DatabaseField(canBeNull = false)
    private Persona veterinario;

    /**
     * Empty constructor
     */
    Control() {
        // Nothing here
    }

    /**
     * Constructor
     *
     * @param fecha to use
     * @param fechaProximoControl to use
     * @param temperatura to use
     * @param peso to use
     * @param altura to use
     * @param diagnostico to use
     * @param veterinario to use
     */
    public Control(ZonedDateTime fecha, ZonedDateTime fechaProximoControl, float temperatura, float peso, float altura, String diagnostico, Persona veterinario) {
        // TODO: Agregar validaciones
        this.fecha = fecha;
        this.fechaProximoControl = fechaProximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
    }

    /**
     * @return the fecha of the control
     */
    public ZonedDateTime getFecha() {
        return fecha;
    }

    /**
     * @return the fecha of the next control
     */
    public ZonedDateTime getFechaProximoControl() {
        return fechaProximoControl;
    }

    /**
     * @return the temperatura
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @return the altura
     */
    public float getAltura() {
        return altura;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @return the veterinario
     */
    public Persona getVeterinario() {
        return veterinario;
    }

}
