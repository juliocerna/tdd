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

import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.util.DefinedBy;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

/**
 * The Main Application
 *
 * @author Julio Cerna Medina
 */
public class Application {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Private constructor
     */
    private Application() {
        // Nothing
    }

    /**
     * The main entry
     *
     * @param args from console
     */
    public static void main(String[] args) {

        // Persona <-> Json via Libreria Json
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);

        // The Javalin webserver
        log.debug("Starting Javalin ..");
        Javalin javalin = Javalin.create(config -> {

            // enable extensive deveploment logging for http and websocket
            //config.enableDevLogging();

            // Logger configuration
            config.requestLogger(((ctx, executionTimeMs) -> {
                log.info("Served{ } in {} ms", ctx.fullUrl(), executionTimeMs);
                ctx.header("Server-Timing", "total;dur=" + executionTimeMs);
            }));

            // Enable routes helper
            config.registerPlugin(new RouteOverviewPlugin("/routes"));

        // Define the routes
        }).routes(() -> {

            // The version
            ApiBuilder.path("v1", () -> {

                // /fichas
                ApiBuilder.path("fichas", () -> {

                    // 1. Get -> /fichas
                    ApiBuilder.get(ApiRestEndpoints::getAllFichas);

                    // 2. Get -> /fichas/find/{query}
                    ApiBuilder.path("find/:query", () -> {
                        ApiBuilder.get(ApiRestEndpoints::findFichas);
                    });

                });

                // 3. /personas
                ApiBuilder.path("personas", () -> {

                    // Get -> personas
                    ApiBuilder.get(ApiRestEndpoints::getAllPersonas);

                });
            });

        // Start the server at port 7000
        }).start(7000);


    }

}
