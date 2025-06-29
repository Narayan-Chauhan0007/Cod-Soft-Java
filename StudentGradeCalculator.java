import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("📚 Student Grade Calculator");

        // Input number of subjects
        System.out.print("Enter the number of subjects: ");
        int subjects = sc.nextInt();

        int[] marks = new int[subjects];
        int totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < subjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            marks[i] = sc.nextInt();

            // Validate input
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("❌ Invalid input. Please enter marks between 0 and 100: ");
                marks[i] = sc.nextInt();
            }

            totalMarks += marks[i];
        }

        // Calculate average
        double averagePercentage = (double) totalMarks / subjects;

        // Determine grade
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n📝 --- Result Summary ---");
        System.out.println("Total Marks: " + totalMarks + " / " + (subjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
