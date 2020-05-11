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
