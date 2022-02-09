import java.util.Arrays;
import java.util.Scanner;

 class Radix {
	
	public static int[] counting(int[] a, int k, int nthDig) {
		
		// frequência
		int[] c = new int[k+1];
		for (int i = 0; i < a.length; i++) {
			int d = (int) (a[i] % Math.pow(10, nthDig));
			d = (int) (d / Math.pow(10, nthDig - 1));
			c[d] += 1;
		}
		
		// cumulativa
		for (int i = 1; i < c.length; i++)
			c[i] += c[i-1];
		
		int[] b = new int[a.length];
		for (int i = a.length - 1; i >= 0; i--) {
			int d = (int) (a[i] % Math.pow(10, nthDig));
			d = (int) (d / Math.pow(10, nthDig - 1));
			b[c[d] - 1] = a[i];
			c[d] -= 1;
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] entrada = sc.nextLine().split(" ");
		int[] a = new int[entrada.length];
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(entrada[i]);
		
        int d = Integer.parseInt(sc.nextLine());
        
        for (int i = 1; i <= d; i++) {
        		a = counting(a, 9, i);
        		System.out.println(Arrays.toString(a));
        }
        
		
		sc.close();
	}
	
}

