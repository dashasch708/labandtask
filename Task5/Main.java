package com.company;

import java.util.*;
import java.text.SimpleDateFormat;


public class Main {

    public static void main(String[] args) {
	System.out.println("1.1 - "+sameLetterPattern("ABAB", "CDCD"));
	System.out.println("1.2 - " + sameLetterPattern("FFGG","CDCD"));
	System.out.println("2 - " + spiderVsFly("A4","B2"));
	System.out.println("3 - " + digitsCount(0));
	String[] mas={"cat","create","sat"};
    System.out.println("4 - " +totalPoints(mas, "caster"));
    int[] mas1 = {1,2,3,5,6,7,8,9};
    System.out.println("5 - "+longestRun(mas1));
    int[] mas2 = {95, 83, 90, 87, 88, 93};
    System.out.println("6 - "+takeDownAverage(mas2)+"%");
    System.out.println("7 - "+rerrange("4of Fo1r pe6ople g3ood th5e the2"));
    System.out.println("8 - "+maxPossible(523, 67));
    System.out.println("9 - "+timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
    System.out.println("10.1 - "+isNew(30));
    System.out.println("10.2 - "+isNew(321));
    }
    public static boolean sameLetterPattern (String str1, String str2) //одинаковый ли шаблон у двух разных строк
    {
        int index = 1;
        int[] array_char1 = new int[str1.length()]; //в массивах лежит числовой шаблон строки
        int[] array_char2 = new int[str2.length()];
        int[] flag1 = new int[str1.length()]; //проверка, что все буквы обработаны
        int[] flag2 = new int[str2.length()];

        for (int i = 0;i < str1.length();i++)
        {
            if (flag1[i] == 1)
                continue; //досрочное прекращение итерации чтобы не обрабатывать одну букву несколько раз
            {
                for (int j = 0;j < str1.length();j++)
                {
                    if (str1.charAt(i) == str1.charAt(j)) //сравниваем буквы на разных позициях
                    {
                        array_char1[j] = index;
                        flag1[j] = 1;
                    }
                }
                index = index + 1;
            }
        }
        index = 1;
        for (int i = 0;i < str2.length();i++)
        {
            if (flag2[i] == 1)
                continue;
            {
                for (int j = 0;j < str2.length();j++)
                {
                    if (str2.charAt(i) == str2.charAt(j))
                    {
                        array_char2[j] = index;
                        flag2[j] = 1;
                    }
                }
                index = index + 1;
            }
        }
        return Arrays.equals(array_char1, array_char2); //сравнение массивов с шаблонами строки
    }

    static class Point //получаем букву и цифру координаты
    {
        private String letter;
        private int rad;

        public Point(String letter, int rad) {
            this.letter = letter;
            this.rad = rad;
        }

        public Point(String p) {
            this.letter = String.valueOf(p.charAt(0));
            rad = Integer.valueOf(String.valueOf(p.charAt(1)));
        }

        public int getRad() {
            return rad;
        }

        public String getLetter() {
            return letter;
        }
    }

    public static double BetweenPoints(Point start, Point moveTo) //поиск расстояния между двумя координатами по теореме косинусов
    {
        if (start.getLetter().equals(moveTo.getLetter()))
            return 1;
        else {
            int a = start.getRad();
            int b = moveTo.getRad();
            return Math.sqrt(a*a + b*b - 2*a*b*Math.cos(Math.toRadians(45)));
        }
    }

    public static double calculateS(Point start, Point moveTo, Point fly)
    {
        Map<String, Integer> web = new HashMap<String,Integer>();
        web.put("A",0);
        web.put("B",1);
        web.put("C",2);
        web.put("D",3);
        web.put("E",4);
        web.put("F",5);
        web.put("G",6);
        web.put("H",7);

        int angle;
        int a;
        int b;
        double distance;
        int difference = Math.abs(web.get(moveTo.getLetter())-web.get(fly.getLetter())); //разница в буквах (и их ключах), от нее зависит угол
        if (difference <= 4)
            angle = difference*45;
        else
            angle = (8 - difference)*45;
        a = moveTo.getRad();
        b = fly.getRad();
        if (difference!=0 && moveTo.getRad()!=0) //считаем расстояние
            distance = Math.sqrt(a*a + b*b - 2*b*a*Math.cos(Math.toRadians(angle)));
        else
            distance = Math.abs(a-b);
        distance += BetweenPoints(start,moveTo);
        return distance;
    }

    public static Point minimumDistance(Point spider,Point fly, Point moveTo1, Point moveTo2 )
    {
        if (calculateS(spider, moveTo1,fly)<calculateS(spider, moveTo2,fly)) //сравниваем расстояния между двумя точками, выбираем кратчайшее
            return moveTo1;
        else
            return moveTo2;
    }

    public static Point whereToGo(Point spider, Point fly)
    {
        Map<Integer, String> revWeb = new HashMap<Integer,String>();
        revWeb.put(0,"A");
        revWeb.put(1,"B");
        revWeb.put(2,"C");
        revWeb.put(3,"D");
        revWeb.put(4,"E");
        revWeb.put(5,"F");
        revWeb.put(6,"G");
        revWeb.put(7,"H");

        Map<String, Integer> web = new HashMap<String,Integer>();
        web.put("A",0);
        web.put("B",1);
        web.put("C",2);
        web.put("D",3);
        web.put("E",4);
        web.put("F",5);
        web.put("G",6);
        web.put("H",7);

        Point first;
        Point second;
        Point third;
        Point fourth;
        Point fin;
        String nextLetter;
        String pastLetter;
        if (spider.getRad() == 0) //если цифра паука = 0, в fin записываем букву мухи и 1
        {
            return fin = new Point(fly.getLetter(),1);
        }
        if (!spider.getLetter().equals("H")) //получаем следующую и предыдущую буквы
            nextLetter = revWeb.get(web.get(spider.getLetter()) + 1);
        else
            nextLetter = "A";
        if (!spider.getLetter().equals("A"))
            pastLetter = revWeb.get(web.get(spider.getLetter()) - 1);
        else
            pastLetter = "H";

        third = new Point(nextLetter, spider.getRad());
        fourth = new Point(pastLetter, spider.getRad());
        fin = minimumDistance(spider, fly, third, fourth); //находим расстояние между точками с соседними для буквы паука буквами

        if (spider.getRad() < 4 && spider.getRad() > 0) //расстояние между точками с соседними для цифры паука цифрами
        {
            first = new Point(spider.getLetter(), spider.getRad() + 1);
            second = new Point(spider.getLetter(), spider.getRad() - 1);
            fin = minimumDistance(spider, fly, fin, minimumDistance(spider,fly,first,second));
        }
        else if (spider.getRad() == 4) //если паук на границе, остаемся на той же букве и отнимаем 1 от цифры
        {
            second = new Point(spider.getLetter(), spider.getRad() - 1);
            fin = minimumDistance(spider, fly, fin, second);
        }
        return fin;
    }

    public static String spiderVsFly(String spider, String fly) //паук+муха
    {
        Point begin = new Point(spider); //получаем коодинаты (буква+цифра) паука и мухи
        Point end = new Point(fly);
        String way = "";
        while (!begin.getLetter().equals(end.getLetter()) || begin.getRad()!=end.getRad()) //пока буква/цифра начала не равна букве/цифре конца
        {
            way += begin.getLetter() + String.valueOf(begin.getRad()) + "-"; //добавляем в путь координату начала
            begin = whereToGo(begin, end); //изменяем координаты начала
        }
        way += begin.getLetter() + String.valueOf(begin.getRad());
        return way;
    }

    public static int digitsCount (int ch) //рекурсивно посчитать кол-во цифр числа
    {
        if (ch < 10)
        {
            return 1;
        }
        else
        {
            return 1 + digitsCount(ch/10);
        }
    }
    public static int totalPoints (String[] mas, String slovo) //массив слов+длинное слово, подсчитать кол-во очков
    {
        int[] fl = new int[6];
        int[] flwords = new int[6];
        int rez = 0;
        int sum = 0;
        for (int i = 0; i < mas.length;i++)
        {
            for (int j = 0; j < 6;j++)
            {
                for (int k = 0; k < mas[i].length();k++)
                {
                    if (flwords[k] == 1)
                        continue;
                    {
                        if (slovo.charAt(j) == mas[i].charAt(k)) //проверка каждой буквы полученного из массива слова на соответствие
                        {
                            fl[j] = 1;
                            flwords[k] = 1;
                        }
                    }
                }
            }
            sum = Arrays.stream(fl).sum(); //подсчет количества соответсий исходному слову
            Arrays.fill(fl, 0); //обнуляем массивы
            Arrays.fill(flwords, 0);
            if (sum == mas[i].length())
            {
                switch(sum)
                {
                    case 3:
                        rez += 1;
                        break;
                    case 4:
                        rez += 2;
                        break;
                    case 5:
                        rez +=3;
                        break;
                    case 6:
                        rez += 54;
                        break;
                    default:
                        rez += 0;
                        break;
                }
            }
        }
        return rez;
    }
    public static int longestRun(int[] mas) //длина самой длинной последовательности чисел
    {
        int max=0; //макс длина последовательности
        int len=1; //длина текущей последовательности
        int num=0; //номер первого элемента последовательности
        for (int i=num;i< mas.length-1;i++)
        {
            if (mas[i + 1] - mas[i] == 1 || mas[i] - mas[i + 1] == 1) //проверка на последовательность
            {
                len++;
                if (len > max)
                    max = len;
            }
            else {
                num = i;
                len = 1;
            }
        }
        return max;
    }
    public static int takeDownAverage (int[] mas) //какое кол-во % нужно, чтобы средний балл понизился на 5%
    {
        int sum=0;
        for (int i=0;i<mas.length;i++) //поиск суммы всех %
            sum+=mas[i];
        int sr=sum/mas.length-5; //поиск среднего значения и занижение его на 5%
        return sr*(mas.length+1)-sum;
    }
    public static String rerrange (String str) //поставить слова в соответствии с цифрами внутри них
    {
        int count = 0;
        String[] sentence_array = str.split(" "); //делим строку в массив по отдельным словам
        String[] new_sentence_array = new String[sentence_array.length]; //пустой массив
        for (int i = 0;i <= sentence_array.length - 1;i++)
        {
            for (int j = 1;j <= sentence_array.length;j++)
            {
                String chislo = String.valueOf(j);
                int key = sentence_array[i].indexOf(chislo); //ищем число в слове с помощью метода indexOf
                if (key != -1)
                {
                    count = count +1;
                    new_sentence_array[Integer.parseInt(chislo) - 1] = sentence_array[i]; //записываем слово в массив
                    new_sentence_array[Integer.parseInt(chislo) - 1] = sentence_array[i].replace(chislo, ""); //удаляем число из слова
                }
            }
        }
        if (count == 0)
            return "";
        else
            return String.join(" ",new_sentence_array);
    }
    public static String maxPossible(int n1, int n2) //максимально возможное число из двух других
    {
        String str1 = Integer.toString(n1);
        String[] numb1 = str1.split(""); //разбиваем число на отдельные цифры
        String rez = "";
        int mas1[] = new int[numb1.length];
        for (int i = 0; i < numb1.length; i++) {
            mas1[i] = Integer.parseInt(numb1[i]); //записываем цифры из стрингового массива в интовый
        }

        String str2 = Integer.toString(n2); //то же самое для второго числа
        String[] numb2 = str2.split("");
        int mas2[] = new int[numb2.length];
        for (int i = 0; i < numb2.length; i++) {
            mas2[i] = Integer.parseInt(numb2[i]);
        }
        for (int i:mas1)
        {
            int max = 0;
            for (int k = 0; k < mas2.length; k++) //ищем максимальную цифру второго числа
            {
                if (mas2[k] >= max)
                    max = mas2[k];
            }
            for (int l = 0; l < mas1.length; l++) //вставляем max на первое подходящее место в первом числе
                if (mas1[l] < max) {
                    mas1[l] = max;
                    break;
                }
            for (int j = 0; j < mas2.length; j++) //удаляем max и соответствующую ему цифру
                if (mas2[j] == max) {
                    mas2[j] = 0;
                    max = 0;
                    break;
                }
        }
        for (int i=0; i <mas1.length; i++)
        {
            rez = rez + mas1[i];
        }
        return rez;
    }
    public static String timeDifference(String cityA,String date,String cityB) //вернуть время и дату в другом городе
    {
        String rez = "";
        date = date.replace(",", ""); //убираем запятую
        date = date.replace(":", " "); //земеняем : на пробел
        String[] dateS = date.split(" ");
        String monthS = dateS[0]; //записываем месяц, день, год, часы, минуты
        int day = Integer.parseInt(dateS[1]);
        int year = Integer.parseInt(dateS[2]);
        int hour = Integer.parseInt(dateS[3]);
        int minutes = Integer.parseInt(dateS[4]);
        int monthIndex = 0;
        String[] cities = {"Los Angeles","New York","Caracas","Buenos Aires","London","Rome","Moscow","Tehran","New Delhi","Beijing","Canberra"};
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        int[] citiesTime = {-480,-300,-270,-180,0,60,180,210,330,480,600}; //часы из таблицы, переведенные в минуты
        int indexOne = 0, indexTwo = 0;
        for (int i = 0;i < cities.length;i++) //получаем индексы стран
        {
            if (cities[i].equals(cityA))
                indexOne = i;
            if (cities[i].equals(cityB))
                indexTwo = i;
        }
        for (int i = 0;i < months.length;i++) //числовое значение месяца
        {
            if (monthS.equals(months[i]))
                monthIndex = i;
        }
        Calendar calendar = new GregorianCalendar(year, monthIndex , day);
        calendar.set(Calendar.HOUR,hour);
        calendar.set(Calendar.MINUTE,minutes);

        calendar.add(Calendar.MINUTE,Math.max(citiesTime[indexOne],citiesTime[indexTwo]) - Math.min(citiesTime[indexOne],citiesTime[indexTwo])); //добавляем нужное кол-во минут
        SimpleDateFormat form = new SimpleDateFormat("yyyy-M-d HH:mm"); //задаем шаблон для формата даты
        rez = form.format(calendar.getTime());
        return rez;
    }

    public static boolean isNew(int a) //проверка можно ли из цифр числа составить меньшее число
    {
        int fl = 0;
        String str = Integer.toString(a); //перевод числа в строковый формат
        str = str.replaceAll("0", ""); //убираем нули
        String[]mas = str.split(""); //создаем новый массив из чисел без нулей
        int numArr[] = new int[mas.length];
        for (int i = 0; i < mas.length; i++)
        {
            numArr[i] = Integer.parseInt(mas[i]); //преобразуем строковый массив в интовый
        }
        for (int i =0; i < numArr.length-1; i++)
        {
            if (numArr[i] >numArr[i+1]) //если находим число, которое больше следующего, прибавляем флаг
            {
                fl = fl+1;
                break;
            }
        }
        if (fl > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
