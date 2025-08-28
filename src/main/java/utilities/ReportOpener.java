package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Utility class to handle opening and cleaning test reports.
 * Supports Allure, Extent, and Cucumber reports.
 */
public class ReportOpener {
  private static final Logger logger = LogManager.getLogger(ReportOpener.class);

            /**
            * Generates and opens the Allure report using the configured batch file path.
            * It first generates the report from results, then opens it in a new terminal window.
            */
    public static void openAllureReport() {
        try {
            String allurePath = ConfigReader.getAllureBatPath();
            String resultsDir = "target/allure-results";
            String reportDir = "target/allure-report";

            // Step 1: Generate the Allure report
            ProcessBuilder generate = new ProcessBuilder(
                    "cmd.exe", "/c", allurePath, "generate", resultsDir, "--clean", "-o", reportDir
            );
            generate.inheritIO();   // Inherit I/O for visibility
            Process genProcess = generate.start();
            genProcess.waitFor();    // Wait for generation to complete

            // Step 2: Open the Allure report in a new terminal window
            String command = String.format("start cmd.exe /k %s open %s", allurePath, reportDir);
            ProcessBuilder open = new ProcessBuilder("cmd.exe", "/c", command);
            open.start();

            logger.info("Allure report server started in a separate terminal.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * Opens the latest Extent report from the "test-output" directory.
     * It looks for folders starting with "JustDailReport " and opens the most recently modified one.
     */
    public static void openLatestExtentReport() {
        // Parent directory where timestamped folders are stored
        File parentDir = new File("test-output");

        if (!parentDir.exists() || !parentDir.isDirectory()) {
            logger.error("Parent directory not found: " + parentDir.getAbsolutePath());
            return;
        }

        // Filter folders that start with "JustDailReport "
        File latestReportFolder = Arrays.stream(parentDir.listFiles(File::isDirectory))
                .filter(f -> f.getName().startsWith("JustDailReport "))
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        if (latestReportFolder == null) {
            logger.info("No timestamped report folders found.");
            return;
        }

        // Construct the path to the Extent report HTML
        File reportFile = new File(latestReportFolder, "Report/CucumberExtentReport.html");

        if (!reportFile.exists()) {
            logger.error("Extent report not found at: " + reportFile.getAbsolutePath());
            return;
        }

        // Open the report in the default browser
        try {
            System.out.println("Opening Extent report: " + reportFile.getAbsolutePath());
            java.awt.Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Opens the Cucumber report located at "target/cucumber-report.html".
    public static void openCucumberReport() {
        File reportFile = new File("target/cucumber-report.html");

        if (!reportFile.exists()) {
            logger.error("Cucumber report not found at: " + reportFile.getAbsolutePath());
            return;
        }

        try {
            logger.info("Opening Cucumber report: " + reportFile.getAbsolutePath());
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //Deletes all files in the "target/allure-results" directory to clean up previous test results.

    public static void cleanAllureResults() {
        File resultsDir = new File("target/allure-results");
        if (resultsDir.exists() && resultsDir.isDirectory()) {
            for (File file : Objects.requireNonNull(resultsDir.listFiles())) {
                file.delete();
            }
        }
        logger.info("Allure Result was cleared");
    }
}








//
//package utilities;
//
//import java.awt.Desktop;
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.util.Objects;
//
//public class AllureReportOpener {
//
//    public static void openAllureReport() {
//        try {
//            // Step 1: Generate the Allure report with --clean
//            ProcessBuilder generate = new ProcessBuilder(
//                    "C:\\Users\\2421228\\Downloads\\allure-commandline-2.29.0 2\\allure-2.29.0\\bin\\allure.bat",
//                    "generate",
//                    "target/allure-results",
//                    "--clean",
//                    "-o",
//                    "target/allure-report"
//            );
//            generate.inheritIO(); // Show output in the console
//            Process genProcess = generate.start();
//            int exitCode = genProcess.waitFor();
//            if (exitCode != 0) {
//                System.err.println("Allure report generation failed.");
//                return;
//            }
//
//            // Step 2: Open the generated report's index.html in default browser
//            File reportIndex = new File("target/allure-report/index.html");
//            if (!reportIndex.exists()) {
//                System.err.println("Report index.html not found!");
//                return;
//            }
//
//            if (Desktop.isDesktopSupported()) {
//                Desktop.getDesktop().browse(reportIndex.toURI());
//                System.out.println("Allure report opened in the default browser.");
//            } else {
//                System.err.println("Desktop is not supported. Please open the report manually at: " + reportIndex.getAbsolutePath());
//            }
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void cleanAllureResults() {
//        File resultsDir = new File("target/allure-results");
//        if (resultsDir.exists() && resultsDir.isDirectory()) {
//            for (File file : Objects.requireNonNull(resultsDir.listFiles())) {
//                file.delete();
//            }
//        }
//    }
//}


//public static void openAllureReport2() {
//    try {
//        // Step 1: Generate the Allure report with --clean
//        ProcessBuilder generate = new ProcessBuilder(
//                "C:\\Users\\2421185\\Documents\\allure-commandline-2.29.0 2\\allure-2.29.0\\bin\\allure.bat",
//                "generate",
//                "target/allure-results",
//                "--clean",
//                "-o",
//                "target/allure-report"
//        );
//        generate.inheritIO(); // To show output in the console
//        Process genProcess = generate.start();
//        genProcess.waitFor();
//
//        // Step 2: Open the generated report
//        ProcessBuilder open = new ProcessBuilder(
//                "C:\\Users\\2421185\\Documents\\allure-commandline-2.29.0 2\\allure-2.29.0\\bin\\allure.bat",
//                "open",
//                "target/allure-report"
//        );
//        open.inheritIO();
//        Process openProcess = open.start();
//        openProcess.waitFor();
//
//    } catch (IOException | InterruptedException e) {
//        e.printStackTrace();
//    }
//}