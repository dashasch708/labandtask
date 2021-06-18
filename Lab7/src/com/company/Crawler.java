package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 Этот класс реализует основные функции поиска.
 */
public class Crawler {

        /**Список для найденных пар адрес-глубина.*/
        static LinkedList <URLDepthPair> findLink = new LinkedList <URLDepthPair>();

        /**Список для просмотренных пар адрес-глубина.*/
        static LinkedList <URLDepthPair> viewedLink = new LinkedList <URLDepthPair>();

        /**Метод для вывода найденных адресов с их глубиной.*/
        public static void getSites(LinkedList<URLDepthPair> viewedLink)
        {
            for (URLDepthPair c : viewedLink)
            {
                System.out.println("Depth: "+c.getDepth() + "\tURL: "+c.getURL());
            }
        }

        /**Отправляет строку текста на другой конец соединения, а затем завершает поток вывода*/
        public static void request(PrintWriter out,URLDepthPair pair) throws MalformedURLException
        {
            out.println("GET " + pair.getPath() + " HTTP/1.1");
            out.println("Host: " + pair.getHost());
            out.println("Connection: close");
            out.println();
            out.flush();
        }

        public static void Process(String pair, int maxDepth) throws IOException
        {
            /**Добавление сайта в нобработанный список (глубина = 0)
             * Сканер завершает работу тогда, когда необработанный список пуст*/
            findLink.add(new URLDepthPair(pair, 0));
            while (!findLink.isEmpty())
            {
                /**Перебираем все сайты, которые не были обработаны, удаляя каждый сайт из
                 списка перед загрузкой его содержимого, и каждый раз, когда находится
                 новый URL-адрес, помещаем его в необработанный список. */
                URLDepthPair currentPair = findLink.removeFirst();
                if (currentPair.depth < maxDepth)
                {
                    /**Создаем новый сокет из полученной строки с
                     именем хоста и из целого числа с номером порта, и устанавливаем соединение*/
                    Socket socket = new Socket(currentPair.getHost(), 80);
                    /**Задаем время ожидания сокета в миллисекундах, чтобы он знал, сколько
                      нужно ждать передачи данных с другой стороны.*/
                    socket.setSoTimeout(1000);
                    try
                    {
                        /**Используем класс BufferedReader для чтения строки из сокета*/
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        request(out, currentPair);
                        String line;
                        /**Просматриваем каждую строку страницы*/
                        while ((line = in.readLine()) != null)
                        {
                            if (line.indexOf(currentPair.URL_PREFIX) != -1 && line.indexOf('"') != -1)
                            {
                                /***/
                                StringBuilder currentLink = new StringBuilder();
                                int i = line.indexOf(currentPair.URL_PREFIX);
                                while (line.charAt(i) != '"' && line.charAt(i) != ' ')
                                {
                                    if (line.charAt(i) == '<')
                                    {
                                        currentLink.deleteCharAt(currentLink.length() - 1);
                                        break;
                                    }
                                    else
                                    {
                                        currentLink.append(line.charAt(i));
                                        i++;
                                    }
                                }
                                /**Сознаем новую пару URL-глубина, увеличивая глубину на 1*/
                                URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.depth + 1);
                                /**Если найденная ссылка удовлетворяет всем условиям, добавляем ее в необработанный список*/
                                if (currentPair.check(findLink, newPair) && currentPair.check(viewedLink, newPair) && !currentPair.URL.equals(newPair.URL))
                                {
                                    findLink.add(newPair);
                                }
                            }
                        }
                        /**Закрываем поток после работы с ним или если вышло время ожидания*/
                        socket.close();
                    }
                    catch (SocketTimeoutException e)
                    {
                        socket.close();
                    }
                }
                /**Обработанную ссылку добавляем в список просмотренных.*/
                viewedLink.add(currentPair);
            }
            /**Вывод  URL-адреса и его глубины.*/
            getSites(viewedLink);
        }

        public static void main(String[] args)
        {
            /**Программа принимает в командной строке два параметра:
             1) Строку, которая представляет собой URL-адрес, с которого можно
             начать просмотр страницы.
             2) Положительное целое число, которое является максимальной глубиной
             поиска*/
            String[] arg = new String[]{"http://www.google.com/","2"};
            try
            {
                Process(arg[0], Integer.parseInt(arg[1]));
            }
            /**Если указаны некорректные аргументы, программа должна немедленно
             остановиться и выдать сообщение об используемых аргументах*/
            catch (NumberFormatException | IOException e)
            {
                System.out.println("usage: java Crawler " + arg[0] + " " + arg[1]);
            }
        }
    }
