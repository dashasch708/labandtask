public class Primes {
	public static void main(String[] args) {
		for (int i=2; i<=100; i++) {
			boolean rez = isPrime (i);
			if (rez==true)
				System.out.println (i);
		} 
	}
	public static boolean isPrime(int n) {
		int f = 0;
		for (int i=2; i<n; i++) {
			if (n%i==0) {
				f=1;
				break;
			}
		}
		return (f==0);
	}
}