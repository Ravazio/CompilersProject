import java.util.Scanner;

public class meuPrograma {
	public static void main(String args[]) {
		Scanner _scTrx = new Scanner(System.in);
		int a;
		int b;
		int c;
		int d;
		String x;
		String y;
		String z;
		double m;
		a = 3 - 2 * 2 + 1 / 1 / 1;
		b = _scTrx.nextInt();
		c = 22;
		m = 3.1415;
		m = c;
		y = "Hello World";
		m = _scTrx.nextDouble();
		c = _scTrx.nextInt();
		if (a > 5) {
			System.out.println("maior que 5");
		} else {
			System.out.println("menor que 5");
		}
		System.out.print("oi agora vamos testar outro");
		if (b >= 0) {
			System.out.println("b positivo");
		}
		if (c >= 0) {
			System.out.println("c positivo");
			if (b >= 0) {
				System.out.println("b e c positivo");
			}
		}
		while (b > 4) {
			System.out.println("2");
		}
		while (b > 5) {
			while (b > 6) {
				System.out.println("2");
			}
		}
		x = _scTrx.nextLine();
		System.out.print("2");
		do {
			b = b + 1;
		} while (b < 3);
		do {
			b = b + 1;
			do {
				System.out.print("imprimindo");
			} while (b < 3);
		} while (b < 3);
	}
}
