import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Book  {
    int pages, price,id,year,stock;
    double rating;
    String name, genre,  author,   language;
    Book(String name, String genre, int pages, String author,double rating, int price, int year, String language, int id, int stock) {
        this.name = name;
        this.genre = genre;
        this.pages = pages;
        this.author = author;
        this.rating = rating;
        this.price = price;
        this.year = year;
        this.language = language;
        this.id = id;
        this.stock = stock;
    }

    public String toCSV() {
        return name + "," + genre + "," + pages + "," + author + "," + rating + "," + price + "," + year + "," + language + "," + id + "," + stock;
    }

    public void display(){
        System.out.println("-----------------------------\n"+"Название: "+name + "\n" + "Жанр: "+genre + "\n" + "Кол-во страниц: "+pages + "\n" + "Автор: "+ author + "\n" +"Рейтинг: " +rating + "\n" +"Цена: "+ price + "\n" + "Год: "+ year + "\n" +"Язык: "+ language + "\n" +"ID:"+ id+"\n" + "В начичии: " + stock+"\n");

    }

    public static void Average() {
        ArrayList<Book> booksShow = readData("data.csv");

        Scanner input = new Scanner(System.in);
        System.out.println("Найти среднее арифметическое..");
        System.out.println("1 - страниц");
        System.out.println("2 - рейтинга");
        System.out.println("3 - цен");
        System.out.println("4 - годов выпуска");
        System.out.println("5 - кол-ва в наличии");
        System.out.println("Введите число: ");
        int prompt = readIntInRange(input,1,5);
        switch (prompt) {
            case 1:
                System.out.printf("%.0f",booksShow.stream().mapToInt(s -> s.pages).average().orElse(0));
                System.out.println();
                break;
            case 2:
                System.out.printf("%.1f",booksShow.stream().mapToDouble(s -> s.rating).average().orElse(0));
                System.out.println();
                break;
            case 3:
                System.out.printf("%.2f",booksShow.stream().mapToDouble(s -> s.price).average().orElse(0));
                System.out.println();
                break;
            case 4:
                System.out.printf("%.0f",booksShow.stream().mapToInt(s -> s.year).average().orElse(0));
                System.out.println();
                break;
            case 5:
                System.out.printf("%.0f",booksShow.stream().mapToInt(s -> s.stock).average().orElse(0));
                System.out.println();
                break;
        }
    }

    public static void SortByID () {
        ArrayList<Book> booksShow = readData("data.csv");
        List<Book> booksSorted = booksShow.stream()
                .sorted(Comparator.comparingInt((Book b)->b.id))
                .collect(Collectors.toUnmodifiableList());

        for(Book b: booksSorted) {
            b.display();
        }
        Scanner input = new Scanner(System.in);

        System.out.println("Сохранить сортировку в файл?");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        System.out.println("Введите число: ");
        int ch = readIntInRange(input,1,2);
        if(ch == 1) {
            try {
                FileWriter writer = new FileWriter("data.csv");
                for (Book b : booksSorted) {
                    writer.write(b.toCSV() + "\n");
                }
                writer.close();
                System.out.println("Данные успешно внесены");
            } catch (IOException e) {
                System.out.println("Не получилось обновить " + e.getMessage());
            }
        }
    }

    public static void SortByName () {
        ArrayList<Book> booksShow = readData("data.csv");
        List<Book> booksSorted = booksShow.stream()
                .sorted(Comparator.comparing((Book b)->b.name))
                .collect(Collectors.toUnmodifiableList());

        for(Book b: booksSorted) {
            b.display();
        }
        Scanner input = new Scanner(System.in);

        System.out.println("Сохранить сортировку в файл?");
        System.out.println("1 - да");
        System.out.println("2 - нет");
        System.out.println("Введите число: ");
        int ch = readIntInRange(input,1,2);
        if(ch == 1) {
            try {
                FileWriter writer = new FileWriter("data.csv");
                for (Book b : booksSorted) {
                    writer.write(b.toCSV() + "\n");
                }
                writer.close();
                System.out.println("Данные успешно внесены");
            } catch (IOException e) {
                System.out.println("Не получилось обновить " + e.getMessage());
            }
        }
    }

    public static void FindByRating() {
        ArrayList<Book> booksShow = readData("data.csv");
        ArrayList<Book> ratingBooks = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Введите Рейтинг (skip - для пропуска): ");
        double prompt = readDouble(input);
        System.out.println("1 - Больше него. ");
        System.out.println("2 - Меньше него. ");
        System.out.println("3 - Ровно он. ");
        System.out.println("Введите число:  ");
        int option = readIntInRange(input,1,3);

        int cnt = 0;

        switch (option) {
            case 1:
                for (Book s : booksShow) {
                    if (s.rating > prompt) {
                        s.display();
                        cnt++;

                    }
                }
            case 2:
                for (Book s : booksShow) {
                    if(s.rating < prompt) {
                        s.display();
                        cnt++;

                    }
                }
            case 3:
                for (Book s : booksShow) {
                    if(s.rating == prompt) {
                        s.display();
                        cnt++;

                    }
                }
        }



        if(cnt==0) {
            System.out.println("Книг с таким рейтингом не существует");
        }

    }

    public static int Search(Scanner input) {
        ArrayList<Book> booksShow = readData("data.csv");
        ArrayList<Book> matchingBooks = new ArrayList<>();

        System.out.print("Введите часть названия книги: ");
        String prompt = input.nextLine();

        int index = 1;
        for (Book s : booksShow) {
            if (s.name.toLowerCase().contains(prompt.toLowerCase())) {
                System.out.println(index + ". " + s.name + " — " + s.author);
                matchingBooks.add(s);
                index++;
            }
        }

        if (matchingBooks.isEmpty()) {
            System.out.println("Книги с таким названием не найдены.");
            return -1;
        }

        System.out.print("Введите номер книги, чтобы выбрать её: ");
        int choice = readIntInRange(input,1,matchingBooks.size());

        Book selected = matchingBooks.get(choice - 1);
        return selected.id;
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int readInt(Scanner input) {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("ОШИБКА. ВВЕДЕНО НЕ ЦЕЛОЕ ЧИСЛО: ");
            }
        }
    }

    public static int readIntInRange(Scanner input, int min, int max) {
        while (true) {
            int value = readInt(input);
            if (value >= min && value <= max) return value;
            System.out.print("Число должно быть от " + min + " до " + max + ": ");
        }
    }

    public static double readDouble(Scanner input) {
        while (true) {
            try {
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("ОШИБКА. ВВЕДЕНО НЕ ЧИСЛО: ");
            }
        }
    }

    public static void saveToCSV(String filename, ArrayList<Book> booksList){
        try {
            FileWriter writer = new FileWriter(filename,true);

            for(Book s: booksList){
                writer.write(s.toCSV() + '\n');
            }
            writer.close();
            System.out.println("ДАННЫЕ УСПЕШНО ВНЕСЕНЫ");
        } catch (IOException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }





    public static void deleteFromCSVbyID(String filename){
        ArrayList<Book> booksShow= readData("data.csv");
        Scanner input = new Scanner(System.in);
        System.out.println("Выбрать по Айди или названию?:");
        System.out.println("1 - ID.");
        System.out.println("2 - Название.");

        int choice = readIntInRange(input,1,2);
        int delID=-1;  //-1 чтобы ничего само не удалилось
        System.out.println("Введите число: ");
        if(choice == 1) {
            delID = readInt(input);
        } else {
            delID = Search(input);
            if (delID == -1) {
                System.out.println("Удаление отменено.");
                return; // ничего не делать
            }
        }

        try {
            FileWriter writer = new FileWriter(filename);
            int cnt=0;
            for(Book s: booksShow){
                if(s.id==delID) {
                    cnt++;

                }
            }
            if(cnt==0) {
                System.out.println("Файла с таким ID не существует");
            } else {
                for(Book s: booksShow){
                    if(!(s.id==delID)) {
                        writer.write(s.toCSV() + '\n');

                    }

                }
                writer.close();
                System.out.println("ФАЙЛ(Ы) УСПЕШНО УДАЛЕН(Ы)");
            }



        } catch (IOException e) {
            System.out.println("ОШИБКА " + e.getMessage());
        }
    }

    public static ArrayList<Book> readData(String filename) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String name = data[0];
                String genre = data[1];
                int pages = Integer.parseInt(data[2]);
                String author = data[3];
                double rating = Double.parseDouble(data[4]);
                int price = Integer.parseInt(data[5]);
                int year = Integer.parseInt(data[6]);
                String language = data[7];
                int id = Integer.parseInt(data[8]);
                int stock = Integer.parseInt(data[9]);


                Book book = new Book(name, genre, pages, author, rating, price, year, language, id, stock);
                books.add(book);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ФАЙЛ НЕ БЫЛ НАЙДЕН");
            e.printStackTrace();
        }

        return books;
    }

    public static Book createBookFromInput(Scanner input) {

        System.out.print("Введите название книги: ");
        String name = input.nextLine();
        System.out.print("Введите жанр: ");
        String genre = input.nextLine();
        System.out.print("Введите количество страниц: ");
        int pages = readInt(input);
        System.out.print("Введите автора: ");
        String author = input.nextLine();
        System.out.print("Введите рейтинг: ");
        double rating = readDouble(input);
        System.out.print("Введите цену: ");
        int price = readInt(input);
        System.out.print("Введите год: ");
        int year = readInt(input);

        System.out.print("Введите язык: ");
        String language = input.nextLine();
        System.out.println("Введите кол-во: ");
        int stock = readInt(input);

        ArrayList<Book> currentBooks = readData("data.csv");
        int maxId = 0;
        for (Book b : currentBooks) {
            if (b.id > maxId) {
                maxId = b.id;
            }
        }
        int id = maxId + 1;

        return new Book(name, genre, pages, author, rating, price, year, language, id,stock);
    }

    public static void updateBook() {
        ArrayList<Book> booksShow= readData("data.csv");

        Scanner input = new Scanner(System.in);
        System.out.println("Выбрать по Айди или названию?:");
        System.out.println("1 - ID.");
        System.out.println("2 - Название.");

        int choice = readIntInRange(input,1,2);
        int delID=-1;  //-1 чтобы ничего само не удалилось
        System.out.println("Введите число: ");
        if(choice == 1) {
            delID = readInt(input);
        } else {
            delID = Search(input);
            if (delID == -1) {
                System.out.println("Удаление отменено.");
                return; // ничего не делать
            }
        }

        int id = delID;
        for(Book s: booksShow){
            if(s.id==id) {
                System.out.print("Введите название книги: ");
                s.name = input.nextLine();
                System.out.print("Введите жанр: ");
                s.genre = input.nextLine();
                System.out.print("Введите количество страниц: ");
                s.pages = readInt(input);
                System.out.print("Введите автора: ");
                s.author = input.nextLine();
                System.out.print("Введите рейтинг: ");
                s.rating = readDouble(input);
                System.out.print("Введите цену: ");
                s.price = readInt(input);
                System.out.print("Введите год: ");
                s.year = readInt(input);

                System.out.print("Введите язык: ");
                s.language = input.nextLine();
                System.out.println("Введите кол-во: ");
                s.stock= readInt(input);

            }
        }
        try {
            FileWriter writer = new FileWriter("data.csv");
            for (Book b : booksShow) {
                writer.write(b.toCSV() + "\n");
            }
            writer.close();
            System.out.println("Книга успешно обновлена!");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }

    }
    public static void updateStock() {
        ArrayList<Book> booksShow= readData("data.csv");

        Scanner input = new Scanner(System.in);
        System.out.println("Выбрать по Айди или названию?:");
        System.out.println("1 - ID.");
        System.out.println("2 - Название.");

        int choice = readIntInRange(input,1,2);
        int delID=-1;  //-1 чтобы ничего само не удалилось
        System.out.println("Введите число: ");
        if(choice == 1) {
            delID = readInt(input);
        } else {
            delID = Search(input);
            if (delID == -1) {
                System.out.println("Удаление отменено.");
                return; // ничего не делать
            }
        }

        int id = delID;
        for(Book s: booksShow){
            if(s.id==id) {
                System.out.println("Введите кол-во: ");
                s.stock= readInt(input);
            }
        }
        try {
            FileWriter writer = new FileWriter("data.csv");
            for (Book b : booksShow) {
                writer.write(b.toCSV() + "\n");
            }
            writer.close();
            System.out.println("Книга успешно обновлена!");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }

    }

}