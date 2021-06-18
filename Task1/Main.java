import java.util.Scanner;
class Main {
	private static int convert (int min) {
		return min*60;
	}
	private static int points(int p2, int p3) {
		return p2*2+p3*3;
	}
	private static int fPoints (int pob, int n) {
		return pob*3+n;
	}
	private static boolean del5 (int a) {
		if (a%5==0) 
			return true;
		else
			return false;
	}
	public static boolean and (boolean a, boolean b) {
		if (a==true && b==true)
			return true;
		else
			return false;
	}
	public static int kraska (int s, int a, int b) {
		return s/(a*b);
	}
	public static int squaed (int a) {
		return a*a;
	}
	public static boolean profit (double prob, double prize, double pay) {
		if (prob*prize>pay)
			return true;
		else
			return false;
	}
	public static int frames (int min, int fps) {
		return min*fps*60;
	} 
	public static int mod (int big, int small) {
		while (big>=0)
			big = big-small;
		return big+small;
	}
	public static void main (String[] args) {
	Scanner scan = new Scanner (System.in);
	int min = 5;
	int rez1=convert(min);
	System.out.println (rez1);

	int p2 = 13;
	int p3 = 12;
	int rez2 = points(p2,p3);
	System.out.println (rez2);
	
	int pob = 3;
	int n = 4;
	int pr = 2;
	int rez3 = fPoints(pob, n);
	System.out.println (rez3);
	
	int l1 = -55;
	boolean rez41 = del5 (l1);
	System.out.println (rez41);

	int l2 = 37;
	boolean rez42 = del5 (l2);
	System.out.println (rez42);

	boolean al1 = true;
	boolean bl1 = true;
	boolean rez51 = and(al1,bl1);
	System.out.println (rez51);

	boolean al2 = false;
	boolean bl2 = true;
	boolean rez52 = and(al2,bl2);
	System.out.println (rez52);

	int s = 54;
	int k = 1;
	int b = 43;
	int rez6 = kraska(s,k,b);
	System.out.println(rez6);

	int a = 5;
	int rez7 = squaed (a);
	System.out.println(rez7);

	double prob = 0.2;
	double prize = 50;
	double pay = 9;
	boolean rez8 = profit (prob, prize, pay);
	System.out.println(rez8);

	int mins = 10;
	int fps = 1;
	int rez9 = frames(mins, fps);
	System.out.println(rez9);

	int big = 5;
	int small = 2;
	int rez10 = mod(big, small);
	System.out.println(rez10);
}
}