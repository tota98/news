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
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.ucn.disc.dsm.gcornejo.news.utils.Validation;
import retrofit2.Response;

/**
 * Naive syncronic NewsApi implementation.
 * @author Gonzalo Cornejo Araya.
 */
public final class NewsApiService {

    /**
     * The Key.
     */
    private final String apiKey;

    /**
     * The sub-service.
     */
    private final APIService apiService;

    /**
     * The Constructor.
     *
     * @param apiKey to use.
     */
    public NewsApiService(String apiKey){
        Validation.notNull(apiKey, "apiKey");
        this.apiKey = apiKey;
        this.apiService = APIClient.getAPIService();
    }

    /**
     * The getTopHeadLines adaptor.
     *
     * @param category to search.
     * @return the List of Article.
     * @throws IOException in case of error.
     */
    public List<Article> getTopHeadlines(final String category, final Integer pageSize) throws IOException {
        Validation.notNull(category, "category");
        Validation.notNull(pageSize, "pageSize");

        if (pageSize < 1) {
            throw new IllegalArgumentException("Error: pageSize need to be >0");
        }

        // TODO: Implements the correct map to request parameters.
        // https://nwesapi.org/docs/endpoints/top-headlines

        // The map of parameters.
        Map<String, String> query = new HashMap<>();
        query.put("apiKey", this.apiKey);

        // query.put("country", topHeadlinesRequest.getCountry());
        // query.put("language", topHeadlinesRequest.getLanguage());
        query.put("category", category);
        // query.put("sources", topHeadlinesRequest.getSources());
        // query.put("q", topHeadlinesRequest.getSources());
        query.put("pageSize", pageSize.toString());
        // query.put("page", topHeadlinesRequest.getPage());

        // The response (sincronic!)
        Response<ArticleResponse> response = apiService.getTopHeadlines(query).execute();

        // ALL ok, return the data
        if (response.isSuccessful()){
            return response.body().getArticles();
        }

        throw new RuntimeException("Error: " + response.code() + " --> " + response.errorBody().string());
    }

}
