package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AllureReportOpener {

    private static final Logger logger = LoggerFactory.getLogger(AllureReportOpener.class);

    public static void openAllureReport() {
        try {
            logger.info("Starting Allure report generation...");

            ProcessBuilder generate = new ProcessBuilder(
                    "C:\\Users\\2421185\\Documents\\allure-commandline-2.29.0 2\\allure-2.29.0\\bin\\allure.bat",
                    "generate",
                    "target/allure-results",
                    "--clean",
                    "-o",
                    "target/allure-report"
            );
            generate.inheritIO();
            Process genProcess = generate.start();
            int exitCode = genProcess.waitFor();

            if (exitCode != 0) {
                logger.error("Allure report generation failed with exit code {}", exitCode);
                return;
            }

            logger.info("Allure report generated successfully.");

            File reportIndex = new File("target/allure-report/index.html");
            if (!reportIndex.exists()) {
                logger.error("Report index.html not found at {}", reportIndex.getAbsolutePath());
                return;
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportIndex.toURI());
                logger.info("Allure report opened in the default browser.");
            } else {
                logger.warn("Desktop is not supported. Please open the report manually at: {}", reportIndex.getAbsolutePath());
            }

        } catch (IOException | InterruptedException e) {
            logger.error("Exception occurred while generating or opening Allure report", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    public static void cleanAllureResults() {
        File resultsDir = new File("target/allure-results");
        if (resultsDir.exists() && resultsDir.isDirectory()) {
            logger.info("Cleaning Allure results directory: {}", resultsDir.getAbsolutePath());
            for (File file : Objects.requireNonNull(resultsDir.listFiles())) {
                if (file.delete()) {
                    logger.debug("Deleted file: {}", file.getName());
                } else {
                    logger.warn("Failed to delete file: {}", file.getName());
                }
            }
        } else {
            logger.warn("Allure results directory does not exist or is not a directory.");
        }
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
