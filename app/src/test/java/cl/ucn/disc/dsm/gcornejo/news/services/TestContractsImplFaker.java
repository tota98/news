/*
 * Copyright 2020 Gonzalo Cornejo Araya, gonzalo.cornejo@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.gcornejo.news.services;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.List;

import cl.ucn.disc.dsm.gcornejo.news.model.News;

public final class TestContractsImplFaker {
    /**
     * The logger.
     */
    private static final Logger log = LoggerFactory.getLogger(TestContractsImplFaker.class);

    /**
     * The Test of Retrieve news.
     */
    @Test
    public void testRetrieveNews(){

        log.debug("Testing ..");

        // The concrete implementation
        Contracts contracts = new ContractsImplFaker();

        // Call the method ..
        List<News> news = contracts.retrieveNews(5);

        // .. the list can't be null ..
        Assertions.assertNotNull(news, "List was null -.-");

        // .. the list can't be empty ..
        Assertions.assertFalse(news.isEmpty(), "The list is Empty .-.");

        // .. the size(list) == 5 ..
        Assertions.assertEquals(5, news.size(), "List size is not 5 >:c");

        // debug to log
        for (News n : news){
            //TODO: Fix this
            // log.debug("News: {}", toStringBuilder.reflectionToString(n, ToStringStyle.MULTI_LINE_STYLE));
        }

        // size = 0
        Assertions.assertEquals(0,contracts.retrieveNews(0).size(),
                "List is not 0");

        // size = 3
        Assertions.assertEquals(3, contracts.retrieveNews(3).size(),
                "List is not 3");

        // size = 10
        Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10,
                "List is not 10");

        log.debug("Done.");
    }

    /**
     * The Test of Save News.
     */
    @Test
    public void testSaveNews(){

        log.debug("Testing ..");

        // Build the faker.
        Faker faker = Faker.instance();

        // The concrete implementation.
        Contracts contracts = new ContractsImplFaker();

        // Obtain list
        List<News> news = contracts.retrieveNews(5);

        // Create random New*
        News theNewsFake = new News(
                faker.book().title(),
                faker.name().username(),
                faker.name().fullName(),
                faker.internet().url(),
                faker.internet().avatar(),
                faker.harryPotter().quote(),
                faker.lorem().paragraph(3),
                ZonedDateTime.now(ZoneId.of("-3"))
        );

        // Save New* into the system.
        contracts.saveNews(theNewsFake);

        // size = 6
        Assertions.assertEquals(news.size(), news.size()+1, "List size is not 6");

        News newsNull = null;

        
    }
}