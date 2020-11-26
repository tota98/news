/*
 * Copyright 2020 Gonzalo Cornejo Araya, gonzalo.cornejo@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.gcornejo.news.model;

import net.openhft.hashing.LongHashFunction;

import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.gcornejo.news.utils.Validation;

/**
 * The Domain model: News.
 *
 * @author Gonzalo Cornejo Araya.
 */
public final class News {

    /**
     * Unique id.
     */
    private final Long id;

    /**
     * The Title.
     * Restrictions: not null, size > 2.
     */
    private final String title;

    /**
     * The source.
     */
    private final String source;

    /**
     * The Author.
     */
    private final String author;

    /**
     * The URL.
     */
    private final String url;

    /**
     * The URL of image.
     */
    private final String urlImage;

    /**
     * The Description.
     */
    private final String description;

    /**
     * The Content.
     */
    private final String content;

    /**
     * The Date of publish
     */
    private final ZonedDateTime publishedAt;

    /**
     * The Constructor.
     *
     * @param title         can't be null.
     * @param source        can't be null.
     * @param author        can't be null.
     * @param url           to the main article.
     * @param urlImage      to the image.
     * @param description   ~full article.
     * @param content       can't be null.
     * @param publishedAt   can't be null.
     */
    public News(String title, String source, String author, String url, String urlImage, String description, String content, ZonedDateTime publishedAt) {

        Validation.minSize(title, 2, "title");
        this.title = title;

        Validation.minSize(source, 2, "source");
        this.source = source;

        Validation.minSize(author, 3, "author");
        this.author = author;

        // Hashing unique! https://github.com/Cyan4973/xxHash
        this.id = LongHashFunction.xx().hashChars(title + "|" + source + "|" + author);

        // Can't be null
        this.url = url;
        this.urlImage = urlImage;

        Validation.minSize(description, 10, "description");
        this.description = description;

        Validation.notNull(content, "content");
        this.content = content;

        Validation.notNull(publishedAt, "publishedAt");
        this.publishedAt = publishedAt;
    }

    /**
     * @return the Id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the Source
     */
    public String getSource() {
        return source;
    }

    /**
     * @return the Author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the Url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the UrlImage
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the Content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the PublishedAt
     */
    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}
