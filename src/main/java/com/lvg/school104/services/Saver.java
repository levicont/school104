package com.lvg.school104.services;

import com.lvg.school104.entities.ActEntity;
import com.lvg.school104.utils.Formatter;
import com.lvg.school104.utils.OpenOfficeUtils;
import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XStorable;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.uno.UnoRuntime;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.lvg.school104.utils.ApplicationProperties.*;

/**
 * Created by Victor Levchenko LVG Corp. on 13.12.2020.
 */
public class Saver {

    private ActEntity actReport;

    public Saver(ActEntity actReport) {
        this.actReport = actReport;
    }


    protected String getRootPath() {
        return getProperty(USER_HOME_PATH_PROPERTY_NAME);
    }

    public void save(XSpreadsheetDocument xSpreadsheetDocument) {

        try {
            XStorable xStorable = UnoRuntime.queryInterface(XStorable.class, xSpreadsheetDocument);
            String path = getPath();
            xStorable.storeAsURL(path, new PropertyValue[0]);
            System.out.println("Stored at url: " + path);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void exportPDF(XSpreadsheetDocument xSpreadsheetDocument) {
        try {
            XStorable xStorable = UnoRuntime.queryInterface(XStorable.class, xSpreadsheetDocument);
            String path = getPdfPath();

            PropertyValue[] propertyValues = new PropertyValue[1];
            propertyValues[0] = new PropertyValue();
            propertyValues[0].Name = "FilterName";
            propertyValues[0].Value = "writer_pdf_Export";
            System.out.println("PDF creating at url: " + path);
            xStorable.storeToURL(path, propertyValues);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getPdfPath() {
        return OPEN_OFFICE_FILE_PATH_PREFIX +
                OpenOfficeUtils.getFileNameAccordingOS(
                        getPdfFileName());
    }

    private String getPdfFileName() {
        String pathToPdfDir;
        pathToPdfDir = getProperty(USER_HOME_PATH_PROPERTY_NAME) + getProperty("ActReportPdfPath");
        checkStoreDirectory(pathToPdfDir);
        return pathToPdfDir +
                getProperty("ActReportPdfFilePrefix") +
                actReport.getFormatNumber(2).replace('/', '-') + "_" +
                Formatter.formatDate(actReport.getActDate()).replace('.', '-') +
                getProperty("PdfFileSuffix");


    }

    private static void checkStoreDirectory(String path) {
        Path pathDir = Paths.get(path);
        if (Files.exists(pathDir))
            return;
        try {
            Files.createDirectories(pathDir);
        } catch (FileAlreadyExistsException ex) {
            System.out.println("Directory: " + path + "already exists");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private String getPath() {
        return OPEN_OFFICE_FILE_PATH_PREFIX +
                OpenOfficeUtils.getFileNameAccordingOS(getRootPath() +
                        getProperty("ActReportPath") +
                        getProtocolFileName() +
                        getProperty("OdsFilesSuffix"));
    }


    private String getProtocolFileName() {
        return getProperty("ActReportFilePrefix") + actReport.getStudentName();
    }

    private String getProtocolFileNameWithDate() {
        return getProperty("ActReportFilePrefix") + actReport.getStudentName() + actReport.getFormatNumber(4).replace('/', '-') + "_" +
                Formatter.formatDate(actReport.getActDate()).replace('.', '-');
    }


}
