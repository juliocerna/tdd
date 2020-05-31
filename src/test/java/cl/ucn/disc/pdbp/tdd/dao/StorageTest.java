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

package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.Tipo;
import cl.ucn.disc.pdbp.utils.Entity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Storage Test
 *
 * @author Julio Cerna Medina
 */
public final class StorageTest {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing de ORMLite + H2 (database)
     */
    @Test
    public void testDataBase() throws SQLException {

        // The database to use (in RAM memory)
        String databaseURl = "jdbc:h2:mem:fivet_db";

        // Connection source: auto-close with the try/catch
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseURl)) {

            // Create the table from the Persona annotations
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            // The dao of Persona
            Dao<Persona, Long> daoPersona = DaoManager.createDao(connectionSource, Persona.class);

            // New Persona
            Persona persona = new Persona("julio","cerna","152532873", "diaz 123", 1L, 2L, "julio@asd.cl");

            // Insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("Id: {}", persona.getId());
            //
            Assertions.assertEquals(1, tuples,"Save tuples != 1");

            // Get from db
            Persona personaDb = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(), personaDb.getNombre(), "Nombre not equals!");
            Assertions.assertEquals(persona.getApellido(), personaDb.getApellido(), "Apellido not equals!");
            Assertions.assertEquals(persona.getRut(), personaDb.getRut(), "Rut not equals!");

            // Search by rut: SELECT * FROM 'persona' WHERE 'rut' = '152532873'
            List<Persona> personaList = daoPersona.queryForEq("rut", "152532873");
            Assertions.assertEquals(1, personaList.size(), "More than one person?!");

            // Not found by rut
            Assertions.assertEquals(0, daoPersona.queryForEq("rut", "19").size(), "Found something!?");

        } catch (IOException e) {
            log.error("Error", e);
        }

    }

    /**
     * Testing the repository of Persona
     */
    @Test
    public void testRepositoryPersona() {

        // The database to use (in RAM memory)
        String databaseURl = "jdbc:h2:mem:fivet_db";

        // Connection source: auto-close with the try/catch
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseURl)) {

            // FIXME: Deberia este metodo ser parte del constructor de un repository?
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            // Test: connection null
            Assertions.assertThrows(RuntimeException.class, () ->
                    new RepositoryOrmLite<>(null, Persona.class)
            );

            // Test: Class null
            Assertions.assertThrows(RuntimeException.class, () ->
                    new RepositoryOrmLite<>(connectionSource, null)
            );

            // The repo
            Repository <Persona, Long> theRepo = new RepositoryOrmLite<>(connectionSource, Persona.class);

            // Get the list of all. Size == 0.
            {
                List<Persona> personas = theRepo.findAll();
                // The size must be zero.
                Assertions.assertEquals(0, personas.size(), "Size != 0 !!");
            }

            // Test the insert v1: ok.
            {
                // New Persona
                Persona persona = new Persona("julio", "cerna", "152532873", "diaz 123", 1L, 2L, "julio@asd.cl");
                if (!theRepo.create(persona)) {
                    Assertions.fail("Can't insert !");
                }
                Assertions.assertNotNull(persona.getId(), "Id was null");
            }

            // Test the insert v2: error.
            {
                // New Persona
                Persona persona = new Persona("julio", "cerna", "152532873", "diaz 123", 1L, 2L, "julio@asd.cl");
                Assertions.assertThrows(RuntimeException.class, () -> theRepo.create(persona));
            }

            // Get the list of all. Size == 1.
            {
                List<Persona> personas = theRepo.findAll();
                // The size must be one.
                Assertions.assertEquals(1, personas.size(), "Size != 1 !!");
            }

            // Delete
            {
                if (!theRepo.delete(1L)) {
                    Assertions.fail("Can't delete !");
                }
                Assertions.assertEquals(0, theRepo.findAll().size(), "Size != 0");
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Test the repository of ficha
     */
    @Test
    public void testRepositoryFicha() {

        // The database to use (in RAM memory)
        String databaseURl = "jdbc:h2:mem:fivet_db";

        // Connection source: auto-close with the try/catch
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseURl)) {

            // Create the table in the backend
            // TODO: Include this call in the repository?
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);

            // The repository of the ficha
            Repository<Ficha, Long> repositoryFicha = new RepositoryOrmLite<>(connectionSource, Ficha.class);

            {
                // 1. Crear la persona desde un repository
                Persona duenio = new Persona("julio", "cerna", "152532873", "diaz 123", 1L, 2L, "julio@asd.cl");
                if (!new RepositoryOrmLite<Persona, Long>(connectionSource, Persona.class).create(duenio)) {
                    Assertions.fail("Can't insert Persona!");
                }

                // 2. Instanciar una ficha
                Ficha ficha = new Ficha((long) 123, "nena", "canino","pudul", ZonedDateTime.now(), Sexo.HEMBRA, "blanco", Tipo.INTERNO, duenio);

                // 3. Crear la ficha vía su repositorio
                if (!repositoryFicha.create(ficha)) {
                    Assertions.fail("Can't create the ficha");
                }
            }

            {
                // 4. Obtener una ficha y revisar si sus atributos son distintos de null
                Ficha ficha = repositoryFicha.findByID(1L);
                // La ficha no puede ser null
                Assertions.assertNotNull(ficha, "Ficha was null");
                Assertions.assertNotNull(ficha.getDuenio(), "Duenio of the ficha was null");
                Assertions.assertNotNull(ficha.getDuenio().getRut(), "Rut del duenio of the ficha was null");
                Assertions.assertNotNull(ficha.getFechaNacimiento(), "FechaNacimiento was null");

                // Imprimir los atributos de la ficha
                log.debug("Ficha {}", Entity.toString(ficha));

            }

        }  catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
