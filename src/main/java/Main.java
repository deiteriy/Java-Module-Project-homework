import java.util.Scanner; // импорт сканера

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        Product product = new Product();

        System.out.println("На сколько человек нужно разделить счет?");
        int payingGuests = 0;// создаем переменную - число гостей
        boolean isGuestsNumberCorrect = false;
        while(!isGuestsNumberCorrect) {
            if(scanner.hasNextInt()) {
                payingGuests = scanner.nextInt();
                if(payingGuests > 1) {
                    isGuestsNumberCorrect = true;
                    scanner.nextLine();
                } else {
                    System.out.println("Я не могу разделить на такое число гостей. Введите значение больше 1");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Вы ввели некорректное значение. Введите числовое значение больше 1");
                scanner.nextLine();
            }
        }
        System.out.println("Ввод корректен. Число гостей установлено: их ровно " + payingGuests); // тут мы разобрались с гостями, обработав некорректный ввод

        while(true) {
            System.out.println("Введите название товара:");
            product.productName = scanner.nextLine();
            System.out.println("Введите стоимость товара в формате \"рубли,копейки\"");

            while(true) {
                if (scanner.hasNextDouble()) {
                    product.productPrice = scanner.nextDouble();
                        if(product.productPrice > 0) {
                            scanner.nextLine();
                            break;
                        }
                    System.out.println("Стоимость товара должна быть больше 0. Введите корректное значение");
                    scanner.nextLine();
                } else {
                    System.out.println("Вы ввели некорректное значение. Введите числовое значение больше нуля");
                    scanner.nextLine();
                }
            }
            calc.add(product);
            System.out.println("Добавить еще один товар? Введите \"Завершить\" чтобы узнать итоговую стоимость,\nили любой другой символ, чтобы добавить еще товары");
            String userChoice = scanner.nextLine();
            String enough = "Завершить";

            if(userChoice.equalsIgnoreCase(enough)) {
                break;
            }
        }

        double guestTotal = calc.total/payingGuests;
        String rubleTemplate ="%.2f";

        System.out.println("Ваш список покупок:\n" + calc.shoppingList);
        calc.findEnding(calc.total);
        System.out.println("Итоговая стоимость покупок составила: " + String.format(rubleTemplate, calc.total) + " " + calc.ending);
        calc.findEnding(guestTotal);
        System.out.println("Каждый гость должен заплатить " + String.format(rubleTemplate, guestTotal) + " " + calc.ending);
    }
}

class Calculator {
    double total = 0;
    String shoppingList = new String();
    String ending = new String();

    public void add(Product product) {
        total += product.productPrice;
        shoppingList += product.productName + " " + product.productPrice + "\n";

    }

    public void findEnding(double endingPrice) {

        int intPrice = (int) Math.floor(endingPrice);

        if(10 < intPrice % 100 && intPrice % 100 <= 19) {
            ending = "рублей";
        } else {
            switch(intPrice % 10) {

                case 1:
                    ending = "рубль";
                    break;
                case 2:
                case 3:
                case 4:
                    ending = "рубля";
                    break;
                default:
                    ending = "рублей";
            }


        }
    }

}

class Product {
    String productName;
    double productPrice;
}









