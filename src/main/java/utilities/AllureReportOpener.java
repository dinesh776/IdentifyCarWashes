package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AllureReportOpener {

    public static void openAllureReport() {
        try {
            // Step 1: Generate the Allure report with --clean
            ProcessBuilder generate = new ProcessBuilder(
                    "C:\\Users\\2421228\\Downloads\\allure-commandline-2.29.0 2\\allure-2.29.0\\bin\\allure.bat",
                    "generate",
                    "target/allure-results",
                    "--clean",
                    "-o",
                    "target/allure-report"
            );
            generate.inheritIO(); // To show output in the console
            Process genProcess = generate.start();
            genProcess.waitFor();

            // Step 2: Open the generated report
            ProcessBuilder open = new ProcessBuilder(
                    "C:\\Users\\2421228\\Downloads\\allure-commandline-2.29.0 2\\allure-2.29.0\\bin\\allure.bat",
                    "open",
                    "target/allure-report"
            );
            open.inheritIO();
            Process openProcess = open.start();
            openProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void cleanAllureResults() {
        File resultsDir = new File("target/allure-results");
        if (resultsDir.exists() && resultsDir.isDirectory()) {
            for (File file : Objects.requireNonNull(resultsDir.listFiles())) {
                file.delete();
            }
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
