/*
 * Licencia MIT
 *
 * Copyright (c) 2020 Julio Cerna Medina <julio.cerna@alumnos.ucn.cl>
 *
 * Por la presente se otorga permiso, sin cargo, a cualquier persona que obtenga una copia
 * de este software y los archivos de documentación asociados (el "Software"), para tratar
 * en el Software sin restricción, incluidos, entre otros, los derechos
 * para usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar y / o vender
 * copias del Software y para permitir a las personas a quienes pertenece el Software
 * amueblado para hacerlo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todos
 * copias o partes sustanciales del software.
 *
 * EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O
 * IMPLÍCITO, INCLUYENDO PERO NO LIMITADO A LAS GARANTÍAS DE COMERCIABILIDAD,
 * APTITUD PARA UN PROPÓSITO PARTICULAR Y NO INFRACCIÓN. EN NINGÚN CASO EL
 * LOS AUTORES O LOS TITULARES DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES POR CUALQUIER RECLAMACIÓN, DAÑO U OTRO
 * RESPONSABILIDAD, EN CASO DE ACCIÓN DE CONTRATO, TORTURA O DE OTRA MANERA, DERIVADA DE,
 * FUERA DE O EN CONEXIÓN CON EL SOFTWARE O EL USO U OTRAS OFERTAS EN EL
 * SOFTWARE.
 ******************************************************************************/

package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Control del paciente
 *
 * @author Julio Cerna Medina
 */
public class Control {

    /**
     * The Fecha of the control
     */
    private final ZonedDateTime fecha;

    /**
     * The fecha of the next control
     */
    private final ZonedDateTime fechaProximoControl;

    /**
     * The temperatura (°C)
     *
     * Min: ... // TODO: Establecer parámetros de la temperatura
     * Max: ...
     */
    private final float temperatura;

    /**
     * The peso (kg)
     *
     * Min: ... // TODO: Establecer parámetros del peso
     * Max: ...
     */
    private final float peso;

    /**
     * The altura (cm)
     *
     * Min: 1
     * Max: ... // TODO: verificar la altura máxima
     */
    private final float altura;

    /**
     * The diagnóstico
     */
    private final String diagnostico;

    /**
     * The veterinario
     *
     * Nota: Vet can perform many checks
     */
    private final Persona veterinario;

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
