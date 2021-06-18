package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
	int[] mas1={2,55,60,97,86};
	int[] mas2 = {8,6,33,100};
	System.out.println("1.1 - " + sevenBoom (mas1));
	System.out.println("1.2 - " + sevenBoom (mas2));
	int[] mas3={5,1,4,3,2};
	int[] mas4 = {5,1,4,3,2,8};
	System.out.println("2.1 - " + cons(mas3));
    System.out.println("2.2 - " + cons(mas4));
	System.out.println("3 - " + unmix("badce"));
    System.out.println("4.1 - " + noYelling("What went wrong?????????"));
    System.out.println("4.2 - " + noYelling("What??? went??? wrong?????????"));
    System.out.println("5 - " + xPronounce("OMG x xbox unboxing video x D"));
    int[] mas5 = {9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5};
    System.out.println("6 - " + largestGap(mas5));
    System.out.println("7 - "+idk(832));
    System.out.println("8 - "+commonLastVowel("OOI UUI EEI AAI"));
    System.out.println("9 - "+memeSum(26, 39));
    System.out.println("10 - "+unrepeated("teshahset"));
    }
    public static String sevenBoom (int[] mas) //если есть 7, то бум, если нет, то соотв вывод
    {
        String str = "";
        for (int i=0;i<mas.length;i++)
            str+=Integer.toString(mas[i]);
        int a = Integer.parseInt(str);
        while (a>0)
        {
            int b=a%10;
            if (b==7) {
                str = "Boom!";
                break;
            }
            a=a/10;
        }
        if (str!="Boom!")
            str="There is no 7 in the array";
        return str;
    }
    public static boolean cons (int[] mas) //упорядоченный список в кот элемент повторяется только 1 раз
    {
        int fl=0;
        int min=100000;
        for (int i=0;i<mas.length;i++)
            if (mas[i]<min)
                min=mas[i];
        min+=1;
        for (int i=0;i<mas.length;i++)
        {
            if (mas[i]==min)
                fl=1;
            else
                fl=0;
            if (fl==1)
                min+=1;
        }
        return(fl==1);
    }
    public static String unmix (String str) //пара символов поменялась местами, надо упорядочить
    {
        char[] arr = str.toCharArray();
        String rez="";
        String dop="";
        int len=arr.length;
        if (len%2!=0) {
            len = len - 1;
            dop = Character.toString(arr[len]); //если длина нечетная, записываем в доп посл символ строки
        }
        for (int i=0;i<len-1;i+=2)
        {
            char a = arr[i];
            arr[i]=arr[i+1];
            arr[i+1]=a;
            rez += Character.toString(arr[i])+Character.toString(arr[i+1]);
        }
        rez+=dop;
        return rez;
    }
    public static String noYelling(String str) //убрать повторяющиеся знаки препинания в конце
    {
        String[] words = str.split(" ");
        int len=words.length;
        String slovo = words[len-1]; //последнее слово
        char[] mas=slovo.toCharArray();
        int vopr=0;
        int voskl=0;
        int len1=mas.length;
        for (int i=0;i< mas.length;i++)
        {
         if(mas[i]=='?')
             vopr++;
         else if (mas[i]=='!')
             voskl++;
        }
        int len2;
        String rez_slovo="";
        if (vopr!=0) {
            len2 = len1 - vopr;
            for (int i = 0; i < len2; i++)
                rez_slovo += mas[i];
            rez_slovo+="?";
        }
        else if(voskl!=0) {
            len2 = len1 - voskl;
            for (int i = 0; i < len2; i++)
                rez_slovo += mas[i];
            rez_slovo+="!";
        }
        words[len-1]=rez_slovo;
        String rez="";
        for (int i=0;i<len;i++)
            rez+=words[i]+" ";
        return rez;
    }
    public static String xPronounce (String str) //заменять иксы в разных местах строки
    {
        char[] mas = str.toCharArray();
        String rez="";
        for (int i=0;i<mas.length;i++) {
            if (mas[i] != 'x') //не икс
                rez += Character.toString(mas[i]);
            else if (mas[i] == 'x' && mas[i - 1] == ' ' && mas[i + 1] == ' ') //просто буква икс
                rez += "ecks";
            else if (mas[i] == 'x' && mas[i - 1] == ' ' && mas[i + 1] != ' ') //в начале слова
                rez += "z";
            else //в конце слова
                rez += "cks";
        }
        return rez;
    }
    public static String largestGap (int[] mas) //наиб разрыв между отсортированными эл-тами массива
    {
        for (int i=1;i<mas.length;i++)
            for (int j= mas.length-1;j>=i;j--)
                if (mas[j-1]>mas[j])
                {
                    int x=mas[j];
                    mas[j]=mas[j-1];
                    mas[j-1]=x;
                }
        int max=0;
        int x1=0;
        int x2=0;
        for (int i=1;i<mas.length;i++)
            if(mas[i]-mas[i-1]>max)
            {
                max=mas[i]-mas[i-1];
                x1=mas[i-1];
                x2=mas[i];
            }
        return("Largest gap between "+ x1 +" and " + x2 +" is "+ max);
    }
    public static int idk (int a) //цифры числа располагаем в порядке возрастания и вычитаем это число из первого
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int i = -1;
        int newA = 0;
        int memory = a;
        while (a > 0){
            list.add(a % 10);
            a = a/10;
            i++;
        }
        Collections.sort(list); //сортировка по возрастанию
        for (int c:list) {
            newA += c * (Math.pow(10, i));//возводим 10 в степень i
            i--;
        }
        return memory-newA;
    }
    public static String commonLastVowel (String str) //наиболее частая последняя гласная
    {
        String glas="aeiouy";
        int[] kol={0,0,0,0,0,0};
        String[] mas=str.toLowerCase().split(" ");
        for (int i=1;i<mas.length;i++) {
            if (glas.indexOf(mas[i].charAt(mas[i].length()-1))>0)
                kol[glas.indexOf(mas[i].charAt(mas[i].length()-1))]+=1;
        }
        int maxInd=0;
        for (int i=0;i<kol.length;i++)
            if(kol[i]>kol[maxInd])
                maxInd=i;
        return Character.toString(glas.charAt(maxInd));
    }
    public static String memeSum (int a, int b)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (a>0 && b>0)
        {
            int sum=0;
            sum=a%10+b%10;
            a=a/10;
            b=b/10;
            list.add(sum);
        }
        if (a!=0)
            list.add(a);
        else if (b!=0)
            list.add(b);
        String str="";
        for(int i = list.size()-1; i>=0; i--) {
            str += Integer.toString(list.get(i));
        }
        return str;
    }
    public static String unrepeated(String str) //убираем повторяющиеся буквы в строке
    {
        char[] mas = str.toCharArray();
        String str1 = "";
        for (Character i:mas){
            if (!str1.contains(String.valueOf(i))) // метод чтобы проверить, содержит ли String указанную последовательность символов
            {
                str1 = str1 + String.valueOf(i);//возвращает строковое представление объекта аргумента
            }
        }
        return str1;
    }
}
