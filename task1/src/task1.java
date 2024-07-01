
public class task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Передано неверное количество аргументов");
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] mas = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = i + 1;
        }

        int i = 0;
        int partSize = m - 1;
        do {
            System.out.print(mas[i]);
            i = (i + partSize) % n;
        } while (i != 0);
    }
}