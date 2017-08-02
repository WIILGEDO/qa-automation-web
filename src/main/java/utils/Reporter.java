package utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class Reporter {

    private static final Logger LOG = getLogger(Reporter.class);
    private static final String REPORT_FOLDER = "report";

    public static void generateReport() throws IOException {
        File reportOutputDirectory = new File("target/" + REPORT_FOLDER);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("./target/" + REPORT_FOLDER + "/results.json");
        String projectName = "auto";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
        String sep = File.separator;
        String pathToReportOverview = System.getProperty("user.dir") + sep + "target" + sep + REPORT_FOLDER;
        LOG.info("Report has been generated, path = " + pathToReportOverview);
        File targetFolder = new File(pathToReportOverview);
        List<File> reportFiles = new ArrayList<>();
        File[] files = targetFolder.listFiles();
        assertTrue("folder", files != null && files.length > 0);
        for (File entry : files) {
            if (entry.isFile()) {
                reportFiles.add(entry);
            }
        }
        for (File reportFile : reportFiles) {
            FileUtils.copyFile(reportFile, new File(Utils.getSessionFolderPath() + sep + REPORT_FOLDER + sep
                    + reportFile.getName()));
        }
    }

    public static void sendReport(List<String> recipients) throws IOException, ZipException {
        String sep = File.separator;
        File reportFolder = new File(Utils.getSessionFolderPath() + sep + REPORT_FOLDER);
        ZipFile zipFile = new ZipFile(Utils.getSessionFolderPath() + sep + REPORT_FOLDER + ".zip");
        File[] files = reportFolder.listFiles();
        assertTrue("Reports not found!", files != null && files.length > 0);
        ArrayList<File> filesToAdd = new ArrayList<>();
        filesToAdd.addAll(Arrays.asList(files));

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters.setPassword("test");
        zipFile.addFiles(filesToAdd, parameters);

        String subject = "Test report from " + Utils.getDateTime();
        String body = " Please find report of automation test execution in attachment . \n"
                + " Please download and unzip folder with password 'test'."
                + " To see the report open file feature-overview.html."
                + " More details you can find by switching between tabs in the upper right corner of the page.";
        try {
            EmailSender.sendEmail(subject, body, Utils.getSessionFolderPath() + sep + REPORT_FOLDER + ".zip", recipients);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            Assert.assertNull("Exception found while report sending!", e);
        }
    }
}
