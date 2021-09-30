package com.lvg.school104.services;

import com.lvg.school104.entities.ActEntity;
import com.lvg.school104.utils.Formatter;
import com.lvg.school104.utils.OpenOfficeUtils;
import com.lvg.school104.utils.PdfPrinter;
import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.uno.UnoRuntime;

import java.util.Objects;

import static com.lvg.school104.utils.OpenOfficeUtils.*;

public class Filler {


    private static final String ACT_TEMPLATE_PATH = "file://"+ Objects.requireNonNull(Filler.class
            .getClassLoader().getResource("templates/act-template.ods")).getPath();

    private XSpreadsheetDocument xSpreadsheetDocument;
    private XComponent xComponent;
    private ActEntity actEntity;
    private String currentTemplatePath;




    public Filler(ActEntity actEntity){
        if (null == actEntity)
            throw new NullPointerException("Act entity cannot be null!");
        this.actEntity = actEntity;
        initCurrentOptions();

        PropertyValue[] loadProperties = getHiddenAsTemplateProperties();
        initXComponent(loadProperties);
        this.xSpreadsheetDocument = getXSpreadsheetDocument();

    }

    private void initCurrentOptions(){
            currentTemplatePath = ACT_TEMPLATE_PATH;
    }


    private void initXComponent(PropertyValue[] loadProperties){
        try{
            XMultiComponentFactory xRemoteContextServiceManager = getXComponentContext().getServiceManager();
            Object desktop = xRemoteContextServiceManager.createInstanceWithContext(DESKTOP_SERVICE,getXComponentContext());
            XComponentLoader xComponentLoader =
                    UnoRuntime.queryInterface(XComponentLoader.class,desktop);
            this.xComponent = xComponentLoader.loadComponentFromURL(currentTemplatePath,BLANK_STR,0,loadProperties);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private XSpreadsheetDocument getXSpreadsheetDocument(){
        try {
            if (this.xSpreadsheetDocument == null) {
                this.xSpreadsheetDocument = UnoRuntime.queryInterface(XSpreadsheetDocument.class,
                        this.xComponent);
            }
            return this.xSpreadsheetDocument;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void print(){

      PdfPrinter.print(actEntity,xSpreadsheetDocument);

    }

    public void save(){
        new Saver(actEntity).save(xSpreadsheetDocument);

    }

    public void exportPDF(){
        new Saver(actEntity).exportPDF(xSpreadsheetDocument);
    }



    public void close(){
        try {
           OpenOfficeUtils.close(xComponent);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void fillUpActReport(){
        System.out.println("Starting filling act report");
        try {
            XSpreadsheets xSpreadsheets = this.xSpreadsheetDocument.getSheets();
            Object sheet = xSpreadsheets.getByName(xSpreadsheets.getElementNames()[0]);
            XSpreadsheet xSpreadsheet = UnoRuntime.queryInterface(XSpreadsheet.class, sheet);
            System.out.println("Number: "+ actEntity.getActNumber());
            System.out.println("Name: "+ actEntity.getStudentName());

            setCellTextByPosition(xSpreadsheet,0,2,
                    actEntity.getStudentName()+", "+Formatter.formatDate(actEntity.getBirthDate())+"р.");
            setCellTextByPosition(xSpreadsheet,2,5, actEntity.getStudentAddress());
            setCellTextByPosition(xSpreadsheet,1, 7, actEntity.getHeadCommission());
            setCellTextByPosition(xSpreadsheet,1, 8, actEntity.getFirstCommissionMember());
            setCellTextByPosition(xSpreadsheet,1, 9, actEntity.getSecondCommissionMember());
            setCellTextByPosition(xSpreadsheet,1, 10, actEntity.getThirdCommissionMember());
            setCellTextByPosition(xSpreadsheet,2, 12, actEntity.getActForRequest());
            setCellTextByPosition(xSpreadsheet,1, 13, actEntity.getForCause());
            setCellTextByPosition(xSpreadsheet,2, 15, actEntity.getChildLivesWith());
            setCellTextByPosition(xSpreadsheet,2, 17, actEntity.getSocialStatusOfFamily());
            setCellTextByPosition(xSpreadsheet,2, 18, actEntity.getFlatRoomCount());
            setCellTextByPosition(xSpreadsheet,2, 19, actEntity.getFlatArea());
            setCellTextByPosition(xSpreadsheet,2, 20, actEntity.getCleanConditions());
            setCellTextByPosition(xSpreadsheet,2, 23, actEntity.getPresentFamilyDuringInspection());
            setCellTextByPosition(xSpreadsheet,0, 26, actEntity.getFamilyProfit());
            setCellTextByPosition(xSpreadsheet,0, 28, actEntity.getChildHas());
            setCellTextByPosition(xSpreadsheet,0, 31, actEntity.getChildHasSuchThings());
            setCellTextByPosition(xSpreadsheet,0, 34, actEntity.getFamilyNeed());
            setCellTextByPosition(xSpreadsheet,0, 36, actEntity.getCommissionConclusion());
            setCellTextByPosition(xSpreadsheet,1, 38, actEntity.getHeadCommission());
            setCellTextByPosition(xSpreadsheet,1, 39, actEntity.getFirstCommissionMember());
            setCellTextByPosition(xSpreadsheet,1, 40, actEntity.getSecondCommissionMember());
            setCellTextByPosition(xSpreadsheet,1, 41, actEntity.getThirdCommissionMember());
            setCellTextByPosition(xSpreadsheet,5, 43, Formatter.formatDate(actEntity.getActDate())+"р.");

        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }





}
