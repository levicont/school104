package com.lvg.school104.utils;

import com.lvg.school104.entities.ActEntity;
import com.sun.star.beans.PropertyValue;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.view.XPrintable;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.lvg.school104.utils.ApplicationProperties.*;
import static com.lvg.school104.utils.OpenOfficeUtils.getFileNameAccordingOS;

public class PdfPrinter {

    private static PropertyValue[] getPdfPrinterOptions() {
        PropertyValue[] properties;
        properties = new PropertyValue[1];
        properties[0] = new PropertyValue();
        properties[0].Name = "Name";
        properties[0].Value = "5D PDF Creator";
        properties[0].Value = "PDF24";
        return properties;
    }

    public static void print(ActEntity actReport, XSpreadsheetDocument xSpreadsheetDocument) {
        try {
            XPrintable xPrintable = UnoRuntime.queryInterface(XPrintable.class,
                    xSpreadsheetDocument);

            xPrintable.setPrinter(getPdfPrinterOptions());

            PropertyValue[] propertyOptions = new PropertyValue[2];

            propertyOptions[0] = new PropertyValue();
            propertyOptions[0].Name = "FileName";
            propertyOptions[0].Value = OPEN_OFFICE_FILE_PATH_PREFIX + getPdfFileName(actReport);

            xPrintable.print(propertyOptions);
            System.out.println("Printed at: " + propertyOptions[0].Value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String getPdfFileName(ActEntity actReport) {
        String pathToPdfDir;
        pathToPdfDir = getProperty(USER_HOME_PATH_PROPERTY_NAME) + getProperty("ActReportPdfPath");
        checkStoreDirectory(pathToPdfDir);
        return getFileNameAccordingOS(pathToPdfDir +
                getProperty("ActReportPdfFilePrefix") +
                actReport.getFormatNumber(4).replace('/', '-') + "_" +
                Formatter.formatDate(actReport.getActDate()).replace('.', '-') +
                getProperty("PdfFileSuffix"));
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


}
