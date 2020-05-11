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

import cl.ucn.disc.pdbp.utils.Validation;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The Persona class
 *
 * @author Julio Cerna Medina
 */
@DatabaseTable(tableName = "persona")
public class Persona {

    /**
     * The id
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The nombre
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * The apellido
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * The rut
     */
    @DatabaseField(canBeNull = false, index = true)
    private String rut;

     /**
     * The direccion
     */
    //private String direccion;

    /**
     * The telefono fijo
     */
    //private Integer telefonoFijo;

    /**
     * The telefono movil
     */
    //private Long telefonoMovil;

    /**
     * The email
     */
    //private String email;

    /**
     * Empty constructor
     */
    Persona() {
        // Nothing here
    }

    /**
     * Persona constructor
     * - The nombre can be not null
     * - The nombre must be more than 1 character
     * - The apellido can be not null
     * - The apellido must be more than 2 characters
     * - The rut can be not null
     * - The rut must be valid
     *
     * @param nombre to use
     * @param apellido to use
     * @param rut valid
     */
    public Persona(String nombre, String apellido, String rut) {

        // Check null fields
        if (nombre == null || apellido == null || rut == null) {
            throw new NullPointerException("The nombre, apellido and rut can be not null");
        }

        // Size of nombre
        if (nombre.length() < 2) {
            throw new RuntimeException("The nombre must be more than 1 character");
        }
        this.nombre = nombre;

        // Size of apellido
        if (apellido.length() < 3) {
            throw new RuntimeException("The apellido must be more than 2 characters");
        }
        this.apellido = apellido;

        // rut valid
        if (!Validation.isRutValid(rut)) {
            throw new RuntimeException("The RUT should be valid");
        }
        this.rut = rut;

        /**this.direccion = direccion;

        // Numero fijo should be valid
        // TODO: Verificar el largo del numero fijo
        if (telefonoFijo != null && telefonoFijo < 100000) {
            throw new RuntimeException("TelefonoFijo should be valid");
        }
        this.telefonoFijo = telefonoFijo;

        // Numero movil should be valid
        if (telefonoMovil != null && telefonoMovil < 10000000) {
            throw new RuntimeException("TelefonoMovil should be valid");
        }
        this.telefonoMovil = telefonoMovil;

        // Validation of the email
        if (!Validation.isEmailValid(email)) {
            throw new RuntimeException("Email should be valid");
        }
        this.email = email;*/

    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return this.rut;
    }

    /**
     * @return the direccion
     */
    /**public String getDireccion() {
        return direccion;
    }*/

    /**
     * @return the telefono fijo
     */
    /**public Integer getTelefonoFijo() {
        return telefonoFijo;
    }*/

    /**
     * @return the telefono movil
     */
    /**public Long getTelefonoMovil() {
        return telefonoMovil;
    }*/

    /**
     * @return the email
     */
    /**public String getEmail() {
        return email;
    }*/

    /**
     * @return the nombre plus apellido.
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }

}
