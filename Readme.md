# Book Management System

## Student Name
**[Your Full Name Here]**

---

## Description

This is a console-based Java application that manages a library of books. It uses CSV files to store and retrieve data about books. The application provides various features for interacting with the book records including adding, updating, deleting, sorting, and searching books.

---

## Objectives

- Implement a persistent book storage system using CSV.
- Provide a user-friendly CLI (Command Line Interface) for interacting with the system.
- Allow sorting and filtering of books by various criteria.
- Enable users to retrieve statistics and random selections from the dataset.
- Practice object-oriented programming principles in Java.

---

## Project Requirement List

1. Show all books stored in the CSV file.
2. Add a new book with user input.
3. Delete a book by its ID.
4. Update an existing book’s full details or only its quantity.
5. Search for a book by a keyword or ID.
6. Sort the book list by ID.
7. Sort the book list alphabetically by title.
8. Find books by a given rating.
9. Calculate and display average ratings or quantities.
10. Show books with the highest/lowest ratings or quantities.
11. Display top authors based on the number of books.
12. Show a random book from the database.

---

## Documentation

### Algorithms & Data Structures

- **ArrayList<Book>** is used for dynamic storage and manipulation of books.
- **CSV File** is used for persistent storage (`data.csv`).
- **Scanner** is used for input collection.
- **Book class** encapsulates all logic related to book data and operations (add, update, delete, sort, etc.).

### Functions/Modules

- `Book.createBookFromInput()` – collects and returns a new Book instance.
- `Book.readData(String filename)` – reads books from a CSV file.
- `Book.saveToCSV(String filename, List<Book>)` – saves books to the CSV file.
- `Book.deleteFromCSVbyID()` – removes a book entry based on ID.
- `Book.SortByID()` / `Book.SortByName()` – sorts books using Comparator.
- `Book.FindByRating()` – filters books by their rating.
- `Book.showExtremes()` – displays the highest and lowest rated/stocked books.
- `Book.topAuthors()` – analyzes and shows most frequent authors.

### Challenges Faced

- Ensuring consistent CSV file reading and writing while maintaining data integrity.
- Validating user input for safe and reliable operation.
- Implementing intuitive and flexible sorting and searching mechanisms.

---

## ✅ Test Cases and Expected Outputs

### Case 1: Adding a Book

**Input:**
Название: Война и мир
Автор: Лев Толстой
Рейтинг: 4.8
Количество: 3



**Expected Output:**
Книга успешно добавлена!



---

### Case 2: Deleting a Book

**Input:**
ID книги для удаления: 3



**Expected Output:**
Книга с ID 3 удалена.



---

### Case 3: Searching by Rating

**Input:**
Введите рейтинг: 4.5



**Expected Output:**
Найдено 2 книги с рейтингом >= 4.5 [Книга 1] [Книга 2]



---

### Case 4: Show Average

**Expected Output:**
Средний рейтинг: 4.3 Среднее количество: 2.6



---

## Files

- `Main.java` – Entry point of the application, manages the menu and user interaction.
- `Book.java` – Handles all book-related logic.
- `data.csv` – Stores book data persistently.

---

## Screenshots

> *(Paste your screenshots here showing all 12 functionalities implemented in action, including add, delete, sort, etc.)*

---

## ✅ Code Requirements

- ✅ Fully functional Java application.
- ✅ Clean and modular code.
- ✅ Follows Java best practices.
- ✅ Minimal inline comments for readability.

---

## 🔚 Conclusion

This Book Management System project demonstrates the use of file handling, object-oriented programming, and user interaction through a command-line interface. It serves as a basic yet effective model for managing structured data in Java.

Let me know if you want the README.md saved as a file or if you’d like help generating the screenshots or editing Book.java to match the new features perfectly.