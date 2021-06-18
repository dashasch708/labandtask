import java.util.Scanner;

public class Lab2 {
	public static void main (String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Enter x1, y1, z1: ");
		double x1 = scan.nextDouble();
		double y1 = scan.nextDouble();
		double z1 = scan.nextDouble();

		System.out.println("Enter x2, y2, z2: ");
		double x2 = scan.nextDouble();
		double y2 = scan.nextDouble();
		double z2 = scan.nextDouble();

		System.out.println("Enter x3, y3, z3: ");
		double x3 = scan.nextDouble();
		double y3 = scan.nextDouble();
		double z3 = scan.nextDouble();

		Point3d coord1 = new Point3d (x1,y1,z1);
		Point3d coord2 = new Point3d (x2,y2,z2);
		Point3d coord3 = new Point3d (x3,y3,z3);
		if (coord1.CompareCoord3d(coord2) || coord2.CompareCoord3d(coord3) || coord3.CompareCoord3d(coord1))
			System.out.println ("Error! Try again please.");
		else
			System.out.println("S = " + computeArea(coord1, coord2, coord3));
	}

	public static double computeArea (Point3d object1, Point3d object2, Point3d object3) {
		double a = object1.distanceTo(object2);
        	double b = object2.distanceTo(object3);
	        double c = object3.distanceTo(object1);
	        double p = (a + b + c)/2;
        	return Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}
}