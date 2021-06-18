package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println ("1.1 - " + oppositeHouse (1,3));
        System.out.println ("1.2 - " + oppositeHouse (5,46));
        System.out.println ("2.1 - " + nameShuffle ("Donald Trump"));
        System.out.println ("2.2 - " + nameShuffle ("Rosie O'Donnell"));
        System.out.println ("3 - " + discount (89,20));
        int[] mass = {10, 4, 1, 4, -10, -50, 32, 21};
        System.out.println (maxMin (mass));
        System.out.println ("5.1 - " + equal (3,4,3));
        System.out.println ("5.2 - " + equal (3,4,1));
        System.out.println ("6 - " + reverse ("Hello World"));
        System.out.println ("7 - " + programmers (147, 33, 526));
        System.out.println ("8.1 - " + getXO ("ooxx"));
        System.out.println ("8.2 - " + getXO ("xooxx"));
        System.out.println ("8.3 - " + getXO ("zpzpz"));
        System.out.println ("9.1 - " + bomb ("Hey, did you think there is a BOMB ?"));
        System.out.println ("9.2 - " + bomb ("This goes boom !!!"));
        System.out.println ("10.1 - " + sameASCII ("AA", "B@"));
        System.out.println ("10.2 - " + sameASCII ("EdAbIt", "EDABIT"));
    }
    public static int oppositeHouse (int n, int l) {
        return l*2-(n-1);
    }
    public static String nameShuffle (String str) {
        String[] name = str.split(" ");
        String a="";
        for (int i = name.length-1; i>=0; i--)
            a = a + (name[i]) + " ";
        return a;
    }
    public static double discount (int c, int sk) {
        return (c-((double)c*sk/100));
    }
    public static int maxMin(int[] array) {
        int max=-100;
        int min=100;
        for (int i=0; i<array.length; i++) {
            if (array[i]<min)
                min=array[i];
            else if (array[i]>max)
                max=array[i];
        }
        System.out.println("4 - min = " + min);
        System.out.println("max = " + max);
        return (max-min);
    }
    public static int equal (int a, int b, int c) {
        int k=0;
        if (a==b) k++;
        if (a==c) k++;
        if (c==b) k++;
        return k;
    }
    public static String reverse (String str) {
        return new StringBuffer(str).reverse().toString();
    }

    public static int programmers (int a, int b, int c) {
        int[] mass = {a, b, c};
        int max=-10000;
        int min=10000;
        for (int i=0; i<3; i++) {
            if (mass[i]<min)
                min=mass[i];
            else if (mass[i]>max)
                max=mass[i];
        }
        return (max-min);
    }
    public static boolean getXO (String str) {
        char[] mass = str.toCharArray();
        int k1=0;
        int k2=0;
        for (int i = 0; i<mass.length; i++) {
            if (mass[i]=='x' || mass[i]=='X') k1++;
            else if (mass[i]=='o' || mass[i]=='O') k2++;
        }
        return (k1==k2);
    }
    public static String bomb (String str) {
        String[] b = str.split(" ");
        int fl=0;
        for (int i=0; i<b.length; i++)
            if (b[i].equalsIgnoreCase("bomb")) {
                fl=1;
                break;
            }
        if (fl == 1) return ("DUCK!");
        else return ("Relax, there's no bomb!");
    }
    public static boolean sameASCII (String s1, String s2) {
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        int k1=0;
        int k2=0;
        for (int i=0; i<a1.length; i++) {
            int ascii = (int) a1[i];
            k1+=ascii;
        }
        for (int j=0; j<a2.length; j++) {
            int ascii = (int) a2[j];
            k2+=ascii;
        }
        return (k1==k2);
    }
}

