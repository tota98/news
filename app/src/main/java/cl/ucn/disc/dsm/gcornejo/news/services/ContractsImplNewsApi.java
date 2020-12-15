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

import com.kwabenaberko.newsapilib.models.Article;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.gcornejo.news.model.News;
import cl.ucn.disc.dsm.gcornejo.news.utils.Validation;

/**
 * The NewsApi implementation of Contracts.
 *
 * @author Gonzalo Cornejo Araya
 */

public class ContractsImplNewsApi implements Contracts {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ContractsImplNewsApi.class);

    /**
     * The connection to NewsApi.
     */
    private final NewsApiService newsApiService;

    /**
     * The Constructor.
     * @param apiKey to use.
     */
    public ContractsImplNewsApi(String apiKey) {
        Validation.notNull(apiKey, "ApiKey");

        this.newsApiService = new NewsApiService(apiKey);
    }

    /**
     * Get the list News.
     *
     * @param size size of the list.
     * @return the List of News.
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        try{
            // Request to NewsApi
            List<Article> articles = this.newsApiService.getTopHeadlines("general", size);

            // The final list of News.
            List<News> news = new ArrayList<>();

            // ... iterate over the articles
            for (Article article : articles){
                // article -> News
                news.add(article2news(article));
            }

            // ... return the list of News.
            return news;
        } catch (IOException e){
            log.error("Error", e);
            // Inner exception
            throw new RuntimeException(e);
        }
    }

    private static News article2news(Article article) {
        return new News(
                article.getTitle(),
                article.getSource().getName(),
                article.getAuthor(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getDescription(),
                article.getDescription(),
        );
    }

    /**
     * Save one News into the System.
     *
     * @param news news of the list.
     */
    @Override
    public void saveNews(News news) {
        throw new NotImplementedException("Can't save news in NewsAPI");
    }
}
