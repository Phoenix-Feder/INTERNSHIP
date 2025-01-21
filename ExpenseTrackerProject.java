import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Expense implements Serializable {
    private Date date;
    private String category;
    private double amount;

    public Expense(Date date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "date=" + date +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Date date, String category, double amount) {
        Expense expense = new Expense(date, category, amount);
        expenses.add(expense);
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalByCategory(String category) {
        return expenses.stream()
                .filter(expense -> expense.getCategory().equals(category))
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}

class FileHandler {
    public static void saveToFile(ArrayList<Expense> expenses, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Expense> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

 class ExpenseTrackerUI {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        boolean running = true;

        while (running) {
            System.out.println("Welcome to Expense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Save Expenses");
            System.out.println("4. Load Expenses");
            System.out.println("5. Calculate Total by Category");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addExpense(expenseTracker);
                    break;
                case 2:
                    viewExpenses(expenseTracker);
                    break;
                case 3:
                    saveExpenses(expenseTracker);
                    break;
                case 4:
                    loadExpenses(expenseTracker);
                    break;
                case 5:
                    calculateTotalByCategory(expenseTracker);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addExpense(ExpenseTracker expenseTracker) {
        System.out.println("Enter date (yyyy-MM-dd):");
        String dateString = scanner.nextLine();
        try {
            Date date = dateFormat.parse(dateString);

            System.out.println("Enter category:");
            String category = scanner.nextLine();

            System.out.println("Enter amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            expenseTracker.addExpense(date, category, amount);
            System.out.println("Expense added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void viewExpenses(ExpenseTracker expenseTracker) {
        ArrayList<Expense> expenses = expenseTracker.getExpenses();
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void saveExpenses(ExpenseTracker expenseTracker) {
        System.out.println("Enter filename to save:");
        String filename = scanner.nextLine();
        FileHandler.saveToFile(expenseTracker.getExpenses(), filename);
        System.out.println("Expenses saved successfully.");
    }

    private static void loadExpenses(ExpenseTracker expenseTracker) {
        System.out.println("Enter filename to load:");
        String filename = scanner.nextLine();
        ArrayList<Expense> loadedExpenses = FileHandler.loadFromFile(filename);
        expenseTracker.getExpenses().clear();
        expenseTracker.getExpenses().addAll(loadedExpenses);
        System.out.println("Expenses loaded successfully.");
    }

    private static void calculateTotalByCategory(ExpenseTracker expenseTracker) {
        System.out.println("Enter category:");
        String category = scanner.nextLine();
        double total = expenseTracker.getTotalByCategory(category);
        System.out.println("Total for category '" + category + "': " + total);
    }
}
