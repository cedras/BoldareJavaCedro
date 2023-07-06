import java.util.*;

public class Exchanger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> nominals = new HashMap<>();
        nominals.put("5 zl", 1);
        nominals.put("2 zl", 3);
        nominals.put("1 zl", 5);
        nominals.put("50 gr", 10);
        nominals.put("20 gr", 20);
        nominals.put("10 gr", 200);
        nominals.put("5 gr", 100);
        nominals.put("2 gr", 100);
        nominals.put("1 gr", 10000);

        System.out.print("Podaj reszte w zl: ");
        double change = scanner.nextDouble();

        List<Map.Entry<String, Integer>> changedCoins = giveChange(change, nominals);

        for (Map.Entry<String, Integer> entry : changedCoins) {
            String nominal = entry.getKey();
            int value = entry.getValue();
            System.out.println("Wydaj: " + value + " monet " + nominal);
        }
    }

    public static List<Map.Entry<String, Integer>> giveChange(double change, Map<String, Integer> nominals) {
        List<Map.Entry<String, Integer>> changedCoins = new ArrayList<>();

        String[] largerNominals = {"5 zl", "2 zl", "1 zl"};
        for (String nominal : largerNominals) {
            double nominalDouble = Double.parseDouble(nominal.split(" ")[0]);
            int value = nominals.get(nominal);

            if (change >= nominalDouble) {
                int moneyToChange = (int) Math.min(change / nominalDouble, value);
                if (moneyToChange > 0) {
                    changedCoins.add(new AbstractMap.SimpleEntry<>(nominal, moneyToChange));
                    change -= moneyToChange * nominalDouble;
                }
            }
        }

        int[] smallerNominals = {50, 20, 10, 5, 2, 1};
        for (int nominal : smallerNominals) {
            if (change >= nominal / 100.0) {
                String nominalStr = nominal + " gr";
                int value = nominals.get(nominalStr);

                int moneyToChange = (int) Math.min(change / (nominal / 100.0), value);
                if (moneyToChange > 0) {
                    changedCoins.add(new AbstractMap.SimpleEntry<>(nominalStr, moneyToChange));
                    change -= moneyToChange * (nominal / 100.0);
                }
            }
        }


        return changedCoins;
    }
}
