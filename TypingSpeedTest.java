import java.util.Scanner;
import java.util.Random;

public class TypingSpeedTest {
    private static final String[] sentences = {
            "The quick brown fox jumps over the lazy dog.",
            "Typing speed improves with practice and patience.",
            "Coding is an art that turns imagination into reality.",
            "Consistency beats intensity in skill development.",
            "Every expert was once a beginner, so keep learning."
    };

    private static final String[] paragraphs = {
            "Mastering typing is like mastering a musical instrument. It takes patience, rhythm, and constant practice. The more you type, the better your fingers adapt to the keyboard. With every keystroke, your brain builds muscle memory, making typing faster and smoother over time. Keep practicing, and soon, speed will follow naturally.",
            "In today's digital world, fast and accurate typing is an essential skill. Whether you're a programmer, writer, or student, improving your typing speed can boost your efficiency. Focus on accuracy first—speed will come later. Just like any skill, the key to improvement is consistent and mindful practice.",
            "A great typist is not born but made through practice. Typing quickly and accurately requires coordination, focus, and dedication. Think of it as a marathon rather than a sprint. Take it one step at a time, improve steadily, and soon, your fingers will glide effortlessly across the keyboard.",
            "The ability to think and type simultaneously is a skill that enhances productivity. Whether composing an email, writing an article, or coding a program, efficient typing reduces cognitive load. The secret lies in muscle memory and continuous practice. With enough effort, your fingers will dance across the keyboard effortlessly.",
            "Every keystroke brings you closer to mastery. Typing speed is not just about speed but also precision. The more you practice, the more natural it feels. Don't worry about making mistakes—learn from them. Over time, your brain and fingers will work in harmony, allowing you to type effortlessly."
    };

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("🚀 Welcome to the Typing Speed Test! 🚀");
        System.out.println();

        while (true) {
            // Prompt user to select text type
            System.out.println("📌 Select a text type:");
            System.out.println("1️⃣ Sentence");
            System.out.println("2️⃣ Paragraph");
            System.out.print("Enter your choice (1 or 2): ");

            int textTypeChoice;
            try {
                textTypeChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter 1 or 2.");
                continue;
            }

            String originalText;

            if (textTypeChoice == 1) {
                originalText = sentences[random.nextInt(sentences.length)];
            } else if (textTypeChoice == 2) {
                originalText = paragraphs[random.nextInt(paragraphs.length)];
            } else {
                System.out.println("❌ Invalid choice. Please try again.");
                continue;
            }

            System.out.println("\n✍️ Type the following text (it will remain visible):\n");
            System.out.println("📝 " + originalText);
            System.out.println("\n🔥 Start typing below! 🔥");

            long startTime = System.currentTimeMillis();
            String userInput = scanner.nextLine();
            long endTime = System.currentTimeMillis();

            // Calculate elapsed time in seconds
            long elapsedTime = endTime - startTime;
            double seconds = elapsedTime / 1000.0;

            int originalTextLength = originalText.length();
            int userInputLength = userInput.length();
            int correctCharacters = 0;

            // Count correct characters
            for (int i = 0; i < Math.min(originalTextLength, userInputLength); i++) {
                if (originalText.charAt(i) == userInput.charAt(i)) {
                    correctCharacters++;
                }
            }

            // Calculate accuracy correctly
            int maxLen = Math.max(originalTextLength, userInputLength);
            int accuracy = maxLen == 0 ? 0 : (int) (((double) correctCharacters / maxLen) * 100);

            // Calculate words per minute
            double wordsPerMinute = (userInputLength / 5.0) / (seconds / 60);

            // Display test results
            System.out.println("\n📊 Test Result:");
            System.out.println("----------------------");
            System.out.printf("⏳ Time elapsed: %.2f seconds%n", seconds);
            System.out.printf("🎯 Accuracy: %d%%%n", accuracy);
            System.out.printf("⚡ Words per minute: %.2f WPM%n", wordsPerMinute);

            // Check for extra or missing characters
            if (userInputLength > originalTextLength) {
                System.out.println("➕ Extra characters typed: " + (userInputLength - originalTextLength));
            } else if (userInputLength < originalTextLength) {
                System.out.println("➖ Missing characters: " + (originalTextLength - userInputLength));
            }

            System.out.println("----------------------");

            // Prompt user for another try
            System.out.print("🔁 Would you like to try again? (Y/N): ");
            String choice = scanner.nextLine().trim();
            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
            System.out.println("\nRestarting... 🎯\n");
            Thread.sleep(1000);
        }

        System.out.println("🙏 Thank you for using the Typing Speed Test! Goodbye! 👋");
        scanner.close();
    }
}
