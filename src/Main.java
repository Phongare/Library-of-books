import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filename = "data.csv";
        boolean on = true;


        while (on) {
            boolean valid = true;
            while(valid) {
                System.out.println("\nВыберите действие:");
                System.out.println("1 - Показать все книги");
                System.out.println("2 - Добавить книгу");
                System.out.println("3 - Удалить книгу");
                System.out.println("4 - Обновить книгу");
                System.out.println("5 - Найти книгу");
                System.out.println("6 - Сортировать по ID");
                System.out.println("7 - Сортировать по алфавиту");
                System.out.println("0 - Выйти");
                System.out.print("Введите число: ");


                String choice = input.nextLine();
                if(Book.isDigit(choice)) {
                    valid = false;
                }
                clearConsole();
                switch (choice) {
                    case "1":
                        ArrayList<Book> booksShow = Book.readData(filename);
                        if (booksShow.isEmpty()) {
                            System.out.println("На данный момент библиотека пуста.");
                        } else {
                            for (Book s : booksShow) {
                                s.display();
                            }
                            System.out.println("-----------------------------\n");
                        }
                        break;
                    case "2":
                        ArrayList<Book> newBooks = new ArrayList<>();
                        Book newBook = Book.createBookFromInput(input);
                        newBooks.add(newBook);
                        Book.saveToCSV(filename, newBooks);
                        break;
                    case "3":
                        Book.deleteFromCSVbyID(filename);
                        break;
                    case "0":
                        on = false;
                        System.out.println("Выход из программы.");
                        break;
                    case "4":

                        System.out.println("Выберите действие:");
                        System.out.println("1 - Обновить книгу.");
                        System.out.println("2 - Обновить кол-во книги.");
                        int updChoice = Book.readIntInRange(input,1,2);

                        if (updChoice == 1) {
                            Book.updateBook();
                        } else {
                            Book.updateStock();
                        }

                        break;
                    case "5":
                        int choosingBookID = Book.Search(input);
                        ArrayList<Book> bookstoShow = Book.readData(filename);
                        for(Book s: bookstoShow) {
                            if(s.id == choosingBookID) {
                                s.display();
                            }
                        }
                        break;
                    case "6":
                        Book.SortByID();
                        break;
                    case "7":
                        Book.SortByName();
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        valid = true;
                }


            }

            if (on) {
                System.out.print("Хотите выполнить еще одно действие? (да/нет): ");
                String answer = input.nextLine().trim().toLowerCase();
                if (!answer.equals("да")) {
                    on = false;
                    System.out.println("До свидания!");
                } else {
                    clearConsole();
                }
            }


        }

    }
}