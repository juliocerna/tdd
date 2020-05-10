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

package cl.ucn.disc.pdbp.utils;

import java.util.regex.Pattern;

/**
 * The validations
 *
 * @author Julio Cerna Medina
 */
public class Validation {

    /**
     * @param email to validate
     * @return true if email is valid
     */
    public static boolean isEmailValid(String email) {
        // https://howtodoinjava.com/regex/java-regex-validate-email-address/

        // TODO: Revisar https://tools.ietf.org/html/rfc5322
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        return email.matches(regex);

    }

    /**
     * @param rut to validate
     * @return true if the rut is valid
     */
    public static boolean isRutValid(String rut) {

        // Not null
        if (rut == null) {
            return false;
        }

        // Wrong size
        if (rut.length() < 2) {
            return false;
        }

        // Last char
        char dv = rut.charAt(rut.length() - 1);

        // Only numbers
        String numbers = rut.substring(0, rut.length() - 1);
        if (!Pattern.matches("[0-9]+", numbers)) {
            return false;
        }

        // The validation
        return validarRut(Integer.parseInt(numbers), dv);

    }

    /**
     * @return true if the rut is valid
     */
    private static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }
}
