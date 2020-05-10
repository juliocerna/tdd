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
