package com.company;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair
{

    /**Задаем переменную для проверки адреса (должен начинаться с http://)*/
    public static final String URL_PREFIX =  "http://";
    public String URL;
    public int depth;

    /** С помощью заданной переменной URL_PREFIX проверяем правильность ссылки.*/
    public static boolean isUrlValid(String url)
    {
        if (url == null)
            return false;
        /**Задаем класс Pattern на поиск совпадений, а затем возвращаем true или false
         * в зависимости от соответствия найденного адреса шаблону.*/
        Pattern urlValidationPattern = Pattern.compile(URL_PREFIX);
        return urlValidationPattern.matcher(url).find();
    }

    /**Если адрес не начинается с http://, выдаем исключение, если же адрес подходит
     * сохраняем его и глубину.*/
    public URLDepthPair (String URL, int depth) throws MalformedURLException
    {
        if (!isUrlValid(URL))
        {
            throw new MalformedURLException();
        }
        this.URL=URL;
        this.depth=depth;
    }

    /**Возвращает веб-хост для текущего адреса*/
    public String getHost() throws MalformedURLException
    {
        URL host = new URL(URL);
        return host.getHost();
    }

    /***/
    public String getPath() throws MalformedURLException
    {
        URL path = new URL(URL);
        return path.getPath();
    }

    /**Возвращает глубину*/
    public int getDepth()
    {
        return depth;
    }

    /**Возвращает URL*/
    public String getURL()
    {
        return URL;
    }

    /**Метод для проверки очередной ссылки, если она уже находится в списке*/
    public static boolean check(LinkedList<URLDepthPair> resultLink, URLDepthPair pair)
    {
        boolean isAlready = true;
        for (URLDepthPair c : resultLink)
        {
            if (c.getURL().equals(pair.getURL()))
            {
                isAlready=false;
            }
        }
        return isAlready;
    }
}
