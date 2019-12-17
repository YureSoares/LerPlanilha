package estudos;

import java.util.Scanner;

public class loop {
	public static void main(String[] args) {
		//Criar Scanner
		Scanner sc = new Scanner(System.in);
		int linhas;
		String asterisco = "";

		System.out.println("Digite a quantiade de linhas que voce precisa:");
		linhas = sc.nextInt();
		String printf = "%" + linhas + "s%n";

		for (int i = 0; i < linhas; i++) {
			asterisco += "*";
			System.out.printf(printf, asterisco);
		}

	}
}
