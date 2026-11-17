/*
 * Name: Manuel Greco
 * Course: ITSE 2321
 */

public class CourseLab {
 
    @FunctionalInterface
    interface ScoreRule {
        boolean accepts(int score);
    }
 
    static class PassingRule implements ScoreRule {
        @Override
        public boolean accepts(int score) {
            return score >= 70; 
        }
    }
 
    static int countMatching(int[] scores, ScoreRule rule) {
        int count = 0;

        for (int score : scores) {
            if (rule.accepts(score)) {
                count++;
            }
        }

        return count;
    }
 
    public static void main(String[] args) {
        int[] scores = {62, 70, 88, 95};
        int[] boundaryScores = {69, 70, 89, 90};
        int[] emptyScores = {};
        
        int threshold = 80;
 
        // Create courses and their reports.
        Course javaCourse = new Course("Java", 24);
        Course cppCourse = new Course("C++", 18);


        // Create one report for each course.
        Course.RosterReport javaReport = javaCourse.new RosterReport();
        Course.RosterReport cppReport = cppCourse.new RosterReport();


        System.out.println();
        System.out.println("COURSE REPORTS");
        System.out.println("------------");

        // Print the Java report.
        javaReport.print();

        // Print the C++ report.
        cppReport.print();

        // Update Java enrollment.
        javaCourse.setEnrollment(25);

        // Print the existing Java report again.
        javaReport.print();
        System.out.println();

        // Get and print enrollment values.
        System.out.println("Java report enrollment: "
        + javaReport.getEnrollment());

        System.out.println("C++ report enrollment: "
        + cppReport.getEnrollment());

 

        // Create and print a catalog entry.
        
        Catalog.Entry entry = new Catalog.Entry("ITSE 2321");
        entry.print();
 

        // Create the required score rules.

        // Named class
        ScoreRule passing = new PassingRule();

        // Anonymous class
        ScoreRule honors = new ScoreRule() {

            @Override

            public boolean accepts(int score) {
                return score >= 90;
            }
        };

        // Lambda expression
        ScoreRule belowPassing = score -> score < 70;

        // honorsLambda variable
        ScoreRule honorsLambda = score -> score >= 90;

        // meetsThreshold class
        ScoreRule meetsThreshold = score -> score >= threshold;



        // ScoreRule broken = score -> { score >= 70; }; -> invalid declaration

        // fixedExpression declaration with expression body 
        ScoreRule fixedExpression = score -> score >= 70;

        // fixedBlock declaration with block body
        ScoreRule fixedBlock = score -> {
            return score >= 70;
        };

 

        // Run and label all required tests.

        System.out.println();
        System.out.println("----------");
        System.out.println("SCORE CATEGORIES");
        System.out.println("----------");

        //Original scores
        System.out.println();
        System.out.println("Original scores, passing count: "
            + countMatching(scores, passing));

        System.out.println("Original scores, honors count: "
            + countMatching(scores, honors));


        System.out.println("Original scores, below passing count: "
            + countMatching(scores, belowPassing));


        // Boundary scores
        System.out.println();
        System.out.println("Boundary scores, passing count: "
            + countMatching(boundaryScores, passing));


        System.out.println("Boundary scores, honors count: "
            + countMatching(boundaryScores, honors));


        System.out.println("Boundary scores, below passing count: "
            + countMatching(boundaryScores, belowPassing));


        // Empty scores
        System.out.println();
        System.out.println("Empty scores, passing count: "
            + countMatching(emptyScores, passing));


        System.out.println("Empty scores, honors count: "
            + countMatching(emptyScores, honors));


        System.out.println("Empty scores, below passing count: "
            + countMatching(emptyScores, belowPassing));



        System.out.println();
        System.out.println("Honors using anonymous class: "
            + countMatching(scores, honors));

        System.out.println("Honors using lambda: "
            + countMatching(scores, honorsLambda));

        // If statement to compare between two variables whether they match or don't
        if (countMatching(scores, honorsLambda) == countMatching(scores, honors)) {
            System.out.println("The two honors implementations match.");
        } else {
            System.out.println("The two honors implementations do not match.");
        }


        // Part 6B; capture local variable testing
        System.out.println();
        System.out.println("Scores meeting threshold of 80: "
            + countMatching(scores, meetsThreshold));


        //Part 7; testing corrected versions
        // Test the corrected rules
        
        System.out.println();
        System.out.println("Expression body count: "
            + countMatching(scores, fixedExpression));

        System.out.println("Block body count: "
            + countMatching(scores, fixedBlock));
        
    }
}
 
class Course {
    private String title;
    private int enrollment;

    public Course(String title, int enrollment) {
        this.title = title;
        this.enrollment = enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public class RosterReport {

        public void print() {
            System.out.println(title + ": " + enrollment + " students");
        }

        public int getEnrollment() {
            return enrollment;
        }
    }
}
 
class Catalog {
 
    static class Entry {
        private String code;
 
        Entry(String code) {
        
            this.code = code;
        }
 
        void print() {
        
            System.out.println(code);
        }
    }
}