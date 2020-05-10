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

import cl.ucn.disc.pdbp.utils.Validation;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model test.
 */
public final class ModelTest {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(ModelTest.class);

    /**
     * Test the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut debe ser valido.
     */
    @Test
    public void testPersona() {

        log.debug("Testing Persona ..");

        // The data!
        log.debug(".. valid ..");
        String nombre = "Andrea";
        String apellido = "Contreras";
        String nombreApellido = nombre + " " + apellido;
        String rutOk = "152532873";
        String rutError = "15253287K";
        String direccion = "";
        Integer telefonoFijo = 0;
        long telefonoMovil = 0;
        String email = "";

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk, direccion, telefonoFijo, telefonoMovil, email);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, null, direccion, telefonoFijo, telefonoMovil, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, rutOk, direccion, telefonoFijo, telefonoMovil, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, null, direccion, telefonoFijo, telefonoMovil, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, rutOk, direccion, telefonoFijo, telefonoMovil, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, null, direccion, telefonoFijo, telefonoMovil, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, rutOk, direccion, telefonoFijo, telefonoMovil, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, null, direccion, telefonoFijo, telefonoMovil, email));

        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutError, direccion, telefonoFijo, telefonoMovil, email));

        // TODO: Add the size of nombre y apellido.

        log.debug("Done.");

    }

    /**
     * Test email
     */
    @Test
    public void email() {
        // TODO: Agregar mas casos de prueba
        Assertions.assertTrue(Validation.isEmailValid("julio.cerna@gmail.cl"));
        Assertions.assertFalse(Validation.isEmailValid("julio.c  erna@gmail.cl"));
        Assertions.assertTrue(Validation.isEmailValid("@gmail.cl"));
    }

    /**
     * Test the digito verificador.
     */
    @Test
    public void testDigitoVerificador() {

        Assertions.assertFalse(Validation.isRutValid(null));

        Assertions.assertTrue(Validation.isRutValid("152532873"));
        Assertions.assertTrue(Validation.isRutValid("21195194K"));
        Assertions.assertTrue(Validation.isRutValid("121244071"));
        Assertions.assertTrue(Validation.isRutValid("198127949"));
        Assertions.assertTrue(Validation.isRutValid("202294316"));

        Assertions.assertFalse(Validation.isRutValid("1525A2873"));
        Assertions.assertFalse(Validation.isRutValid("15253287K"));
        Assertions.assertFalse(Validation.isRutValid("15253287-"));

    }


}

