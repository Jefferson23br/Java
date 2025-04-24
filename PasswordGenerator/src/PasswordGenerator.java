
import java.security.SecureRandom;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gerador de Senhas Seguras ===");
        System.out.print("Comprimento da senha: ");
        int length = scanner.nextInt();

        System.out.print("Incluir letras maiúsculas? (s/n): ");
        boolean useUpper = scanner.next().equalsIgnoreCase("s");

        System.out.print("Incluir letras minúsculas? (s/n): ");
        boolean useLower = scanner.next().equalsIgnoreCase("s");

        System.out.print("Incluir números? (s/n): ");
        boolean useDigits = scanner.next().equalsIgnoreCase("s");

        System.out.print("Incluir símbolos? (s/n): ");
        boolean useSymbols = scanner.next().equalsIgnoreCase("s");

        String password = generatePassword(length, useUpper, useLower, useDigits, useSymbols);
        System.out.println("\nSenha gerada: " + password);

        System.out.print("Deseja salvar a senha em um arquivo? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {
            saveToFile(password);
        }

        scanner.close();
    }

    public static String generatePassword(int length, boolean upper, boolean lower, boolean digits, boolean symbols) {
        StringBuilder pool = new StringBuilder();
        if (upper) pool.append(UPPER);
        if (lower) pool.append(LOWER);
        if (digits) pool.append(DIGITS);
        if (symbols) pool.append(SYMBOLS);

        if (pool.isEmpty()) return "Selecione ao menos um tipo de caractere.";

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(pool.length());
            password.append(pool.charAt(index));
        }
        return password.toString();
    }

    public static void saveToFile(String password) {
        try (FileWriter writer = new FileWriter("output/senhas_salvas.txt", true)) {
            writer.write(password + "\n");
            System.out.println("Senha salva em 'output/senhas_salvas.txt'");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
