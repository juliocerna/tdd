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

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.dao.Repository;
import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Contratos
 *
 * @author Julio Cerna Medina
 */
public class ContratosImpl implements Contratos {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

    /**
     * The Source of connections
     */
    private ConnectionSource connectionSource;

    /**
     *  The {@link Repository} of {@link Ficha}
     */
    private Repository<Ficha, Long> repoFicha;

    /**
     *  The {@link Repository} of {@link Persona}
     */
    private Repository<Persona, Long> repoPersona;

    /**
     *  The {@link Repository} of {@link Control}
     */
    private Repository<Control, Long> repoControl;

    /**
     * The constructor
     *
     * @param databaseUrl jdbc string to connect to backend
     */
    public ContratosImpl(String databaseUrl) {

        // Nullity!
        if(databaseUrl == null) {
            throw new IllegalArgumentException("Can´t create Contratos with databaseUrl null");
        }

        try {
            // THe connection
            this.connectionSource = new JdbcConnectionSource(databaseUrl);

            // The creation of tables
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            // The repositories
            this.repoFicha = new RepositoryOrmLite<>(this.connectionSource, Ficha.class);
            this.repoPersona = new RepositoryOrmLite<>(this.connectionSource, Persona.class);
            this.repoControl = new RepositoryOrmLite<>(this.connectionSource, Control.class);

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }

    /**
     * Contrato: C01-Registrar los datos de un paciente
     *
     * @param ficha to save in the backend
     * @return the {@link Ficha} saved
     */
    @Override
    public Ficha registrarPaciente(Ficha ficha) {
       throw new NotImplementedException();
    }

    /**
     * Contrato: C02-Registrar los datos de una persona
     *
     * @param persona to save in the backend
     * @return the {@link Persona} saved
     */
    @Override
    public Persona registrarPersona(Persona persona) {
        throw new NotImplementedException();
    }

    /**
     * Contrato: C03-Buscar ficha
     *
     * @param query to search
     * @return the {@link List} of {@link Ficha}
     */
    @Override
    public List<Ficha> buscarFicha(String query) {

        // Nullity
        if (query == null) {
            throw new IllegalArgumentException("Query was null");
        }

        // The result: List of Ficha
        List<Ficha> fichas = new ArrayList<>();

        try {

            // 1. Find by number
            if (StringUtils.isNumeric(query)) {

                // All the fichas with number
                log.debug("Finding Fichas with numero ...");
                List<Ficha> fichasNumero = this.repoFicha.findAll("numero", query);

                if(!fichasNumero.isEmpty()) {
                    fichas.addAll(fichasNumero);
                }

                // 2. Find by rut of Duenio
                log.debug("Finding Fichas with rut of Duenio ...");
                QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
                queryPersona.where().like("rut", "%" + query + "%");

                // Run the join
                List<Ficha> fichasRut = this.repoFicha.getQuery().join(queryPersona).query();

                if (!fichasRut.isEmpty()) {
                    fichas.addAll(fichasRut);
                }
            }

            // 3. Find by name of Paciente
            log.debug("Finding Fichas with nombre of Paciente ...");
            List<Ficha> fichaNombre1 = this.repoFicha.getQuery().where().like("nombrePaciente", "%" + query + "%").query();

            if (!fichaNombre1.isEmpty()) {
                fichas.addAll(fichaNombre1);
            }

            // 4. Find by nombre of Duenio
            log.debug("Finding Fichas with nombre of Duenio ...");
            QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
            queryPersona.where().like("nombre", "%" + query + "%");

            List<Ficha> fichaNombre2 = this.repoFicha.getQuery().join(queryPersona).query();

            if (!fichaNombre2.isEmpty()) {
                fichas.addAll(fichaNombre2);
            }

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

        return fichas;
    }

}
