package com.lvg.school104.services;

import com.lvg.school104.entities.ActEntity;
import com.sun.star.lang.XComponent;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.uno.UnoRuntime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.lvg.school104.utils.OpenOfficeUtils.*;


/**
 * Created by Victor Levchenko LVG Corp. on 20.11.2020.
 */
public class DataExtractor {
    private static final String TOTAL_STUDENTS_TABLE_PATH = "file://"+ Objects.requireNonNull(DataExtractor.class
            .getClassLoader().getResource("templates/total-students-table.xlsx")).getPath();

    private static List<ActEntity> actEntityList = new ArrayList<>();

    public static List<ActEntity> getActEntities(){
        if (!actEntityList.isEmpty())
            actEntityList.clear();
        XComponent xComponent = loadXComponent(TOTAL_STUDENTS_TABLE_PATH,getHiddenAsTemplateProperties());
        XSpreadsheets xSpreadsheets = getSpreadsheets(xComponent);

        try {
            Object sheet = xSpreadsheets.getByName(xSpreadsheets.getElementNames()[0]);
            XSpreadsheet xSpreadsheet = UnoRuntime.queryInterface(XSpreadsheet.class, sheet);

            int startRow = 2;
            int emptyRowsCount = 0;

            while(emptyRowsCount <2){
                String firstCellValue = getCellTextByPosition(xSpreadsheet,0,startRow);
                if(firstCellValue.isEmpty()){
                    emptyRowsCount++;
                }else{
                    actEntityList.add(getJournalWeldingEntityFromTableRow(startRow,xSpreadsheet));
                    startRow++;
                    emptyRowsCount=0;
                }
            }
            System.out.println(actEntityList.size()+" journal entities has found.");
            return actEntityList;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
       finally {
            close(xComponent);
        }

    }

    private static ActEntity getJournalWeldingEntityFromTableRow(int row, XSpreadsheet xSpreadsheet){
        ActEntity entity = new ActEntity();
        try {
            entity.setStudentName(getCellTextByPosition(xSpreadsheet,0,row));
            entity.setBirthDate(getLocalDateFromDoubleValue(xSpreadsheet.getCellByPosition(1,row).getValue()));
            entity.setStudentAddress(getCellTextByPosition(xSpreadsheet, 2, row));
            entity.setHeadCommission(getCellTextByPosition(xSpreadsheet,3, row));
            entity.setFirstCommissionMember(getCellTextByPosition(xSpreadsheet,4, row));
            entity.setSecondCommissionMember(getCellTextByPosition(xSpreadsheet,5, row));
            entity.setThirdCommissionMember(getCellTextByPosition(xSpreadsheet,6, row));
            entity.setActForRequest(getCellTextByPosition(xSpreadsheet,7, row));
            entity.setForCause(getCellTextByPosition(xSpreadsheet,8, row));
            entity.setChildLivesWith(getCellTextByPosition(xSpreadsheet,9, row));
            entity.setSocialStatusOfFamily(getCellTextByPosition(xSpreadsheet,10, row));
            entity.setFlatRoomCount(getCellTextByPosition(xSpreadsheet,11, row));
            entity.setFlatArea(getCellTextByPosition(xSpreadsheet,12, row));
            entity.setCleanConditions(getCellTextByPosition(xSpreadsheet,13, row));
            entity.setFlatCleanState(getCellTextByPosition(xSpreadsheet,14, row));
            entity.setPresentFamilyDuringInspection(getCellTextByPosition(xSpreadsheet,15, row));
            entity.setFamilyProfit(getCellTextByPosition(xSpreadsheet,16, row));
            entity.setChildHas(getCellTextByPosition(xSpreadsheet,17, row));
            entity.setChildHasSuchThings(getCellTextByPosition(xSpreadsheet,18, row));
            entity.setFamilyNeed(getCellTextByPosition(xSpreadsheet,19, row));
            entity.setCommissionConclusion(getCellTextByPosition(xSpreadsheet,20, row));
            entity.setActDate(getLocalDateFromDoubleValue(xSpreadsheet.getCellByPosition(21,row).getValue()));


        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return entity;
    }

    public static void main(String[] args) {
        getActEntities().forEach(actEntity -> {
            System.out.println(actEntity.toString());
        });
    }


}
