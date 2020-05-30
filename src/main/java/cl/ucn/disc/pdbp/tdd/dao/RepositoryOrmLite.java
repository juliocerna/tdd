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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * The {@link Repository} implementation with ORMLite
 *
 * @param <T> the type of model domain to use
 * @param <K> the type of id
 */
@SuppressWarnings("DesignForExtension")
public final class RepositoryOrmLite<T, K> implements Repository<T, K> {

    /**
     * The Generic Dao
     */
    private final Dao<T, K> theDao;

    /**
     * The constructor
     *
     * @param connectionSource to connect to ORM
     * @param theClass         to use as source
     */
    public RepositoryOrmLite(ConnectionSource connectionSource, Class<T> theClass) {

       // Nullity test
        if (theClass == null) {
            throw new IllegalArgumentException("Can't create Repository without the class");
        }

        try{
            theDao = DaoManager.createDao(connectionSource, theClass);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @return a List of T
     */
    @Override
    public List<T> findAll() {
       try{
           return theDao.queryForAll();
       } catch (SQLException throwables) {
           throw new RuntimeException(throwables);
       }
    }

    /**
     * @param key   to search
     * @param value to search
     * @return the List of T filtered by key
     */
    @Override
    public List<T> findAll(String key, Object value) {
        try{
            return theDao.queryForEq(key, value);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @return the {@link QueryBuilder}
     */
    public QueryBuilder<T, K> getQuery() {
        return theDao.queryBuilder();
    }

    /**
     * @param id to search
     * @return the T with id
     */
    @Override
    public T findByID(K id) {

        // Nullity test
        if (id == null) {
            throw new IllegalArgumentException("Can't find nulls");
        }

        try {
            return theDao.queryForId(id);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @param t to save
     * @return true
     */
    @Override
    public boolean create(T t) {

        // Nullity
        if (t == null) {
            throw new IllegalArgumentException("Can't create a null");
        }

        try {
            return theDao.create(t) == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @param t to update
     * @return true
     */
    @Override
    public boolean update(T t) {

        // Nullity
        if (t == null) {
            throw new IllegalArgumentException("Can't update a null");
        }

        try {
            return theDao.update(t) == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @param id to delete
     * @return true
     */
    @Override
    public boolean delete(K id) {

        // Nullity
        if (id == null) {
            throw new IllegalArgumentException("Can't delete a null");
        }

        try {
            return theDao.deleteById(id) == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

}
