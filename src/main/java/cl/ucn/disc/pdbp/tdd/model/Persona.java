package cl.ucn.disc.pdbp.tdd.model;

import cl.ucn.disc.pdbp.utils.Validation;

/**
 * The Persona class
 *
 * @author Julio Cerna Medina
 */
public class Persona {

    /**
     * The id
     */
    private Long id;

    /**
     * The nombre
     */
    private final String nombre;

    /**
     * The apellido
     */
    private final String apellido;

    /**
     * The rut
     */
    private final String rut;

    /**
     * The direccion
     */
    private final String direccion;

    /**
     * The telefono fijo
     */
    private final Integer telefonoFijo;

    /**
     * The telefono movil
     */
    private final Long telefonoMovil;

    /**
     * The email
     */
    private final String email;

    /**
     * Persona constructor
     * - The nombre can be not null
     * - The nombre must be more than 1 character
     * - The apellido can be not null
     * - The apellido must be more than 2 characters
     * - The rut can be not null
     * - The rut must be valid
     *
     *  @param nombre to use
     * @param apellido to use
     * @param rut valid
     * @param direccion to use
     * @param telefonoFijo to use
     * @param telefonoMovil to use
     * @param email to use
     */
    public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo, Long telefonoMovil, String email) {

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

        this.direccion = direccion;

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
        this.email = email;

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
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the telefono fijo
     */
    public Integer getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * @return the telefono movil
     */
    public Long getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the nombre plus apellido.
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }

}
