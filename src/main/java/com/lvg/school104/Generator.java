package com.lvg.school104;

import com.lvg.school104.entities.ActEntity;
import com.lvg.school104.services.DataExtractor;
import com.lvg.school104.services.Filler;
import com.lvg.school104.utils.OpenOfficeUtils;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 28.09.2021.
 */
public class Generator {

    public static void main(String[] args) {
        List<ActEntity> acts = DataExtractor.getActEntities();
        acts.forEach(act -> {
            Filler filler = new Filler(act);
            filler.fillUpActReport();
            filler.save();
            filler.close();
        });
        OpenOfficeUtils.closeContext();
    }
}
