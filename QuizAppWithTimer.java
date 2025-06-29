import java.util.*;

public class QuizAppWithTimer {
    // Question model
    static class Question {
        String questionText;
        String[] options;
        int correctOption;

        Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // Adding sample questions
        questions.add(new Question("Which language is used for Android development?",
                new String[]{"Java", "Swift", "Python", "C#"}, 1));
        questions.add(new Question("What does HTML stand for?",
                new String[]{"Hyper Trainer Marking Language", "Hyper Text Markup Language", "High Text Machine Language", "None"}, 2));
        questions.add(new Question("Which company created Java?",
                new String[]{"Microsoft", "Google", "Sun Microsystems", "Apple"}, 3));
        questions.add(new Question("What is the extension of Java bytecode files?",
                new String[]{".java", ".class", ".exe", ".js"}, 2));

        int score = 0;
        List<String> resultSummary = new ArrayList<>();
        int timeLimitSeconds = 15;

        System.out.println("🎓 Welcome to the Java Quiz!");
        System.out.println("⏱ You have " + timeLimitSeconds + " seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.questionText);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\n⏰ Time's up! Moving to the next question.");
                    System.exit(0); // For demo; use flag in real app
                }
            };

            timer.schedule(task, timeLimitSeconds * 1000);

            System.out.print("Enter your choice (1-4): ");
            int userAnswer = -1;

            try {
                userAnswer = sc.nextInt();
                timer.cancel();
            } catch (InputMismatchException e) {
                timer.cancel();
                sc.next(); // clear buffer
                System.out.println("❌ Invalid input.");
            }

            if (userAnswer == q.correctOption) {
                System.out.println("✅ Correct!\n");
                score++;
                resultSummary.add("Q" + (i + 1) + ": ✅ Correct");
            } else {
                System.out.println("❌ Incorrect. Correct answer: " + q.correctOption + ". " + q.options[q.correctOption - 1] + "\n");
                resultSummary.add("Q" + (i + 1) + ": ❌ Incorrect");
            }
        }

        // Final result
        System.out.println("🎉 Quiz Finished!");
        System.out.println("📊 Your Score: " + score + " / " + questions.size());
        System.out.println("🧾 Summary:");
        for (String s : resultSummary) {
            System.out.println(s);
        }

        sc.close();
    }
}
