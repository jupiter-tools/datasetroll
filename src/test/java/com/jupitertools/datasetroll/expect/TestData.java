package com.jupitertools.datasetroll.expect;

import com.jupitertools.datasetroll.DataSet;
import com.jupitertools.datasetroll.importdata.ImportFile;
import com.jupitertools.datasetroll.importdata.JsonImport;

import java.util.List;
import java.util.Map;


/**
 * Created on 15.12.2018.
 *
 * @author Korovin Anatoliy
 */
public class TestData {

    public Map<String, List<Map<String, Object>>> read(String fileName) {
        return new JsonImport(new ImportFile(fileName)).read();
    }

    public DataSet jsonDataSet(String fileName) {
        return new JsonImport(new ImportFile(fileName));
    }
}
