package fr.amu.iut.prototype1.appli_my_seismes.utilitaries;

import fr.amu.iut.prototype1.appli_my_seismes.datas.Seisme;
import fr.amu.iut.prototype1.appli_my_seismes.utilitaries.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    @Test
    void CSVFileReader() {
        ArrayList<String> listExpected = SisFrance_CSV_String_Test();
        ArrayList<String> listTestCsv = null;
        try {
            listTestCsv = CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/utilitaries/SisFrance_seismes_TEST.csv");
        } catch (FileNotFoundException e) {
            listTestCsv = new ArrayList<>();
        }
        assertEquals(listExpected.size(), listTestCsv.size());
        assertEquals(listExpected, listTestCsv);
    }

    @Test
    void stringArrayToSeismeArrayList() {
        ArrayList<Seisme> listExpected = SisFrance_CSV_Seisme_Test();
        ArrayList<Seisme> listTestCsv = null;
        try {
            listTestCsv = CSVReader.StringArrayToSeismeArrayList(
                            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/utilitaries/SisFrance_seismes_TEST.csv"));
        } catch (FileNotFoundException e) {
            listTestCsv = new ArrayList<>();
        }
        assertEquals(listExpected.size(), listTestCsv.size());
        assertEquals(listExpected, listTestCsv);

    }

    private ArrayList<String> SisFrance_CSV_String_Test(){
        ArrayList<String> stringTest = new ArrayList<>();
        stringTest.add("Identifiant,Date (AAAA/MM/JJ),Heure,Nom,Région épicentrale,Choc,X RGF93/L93,Y RGF93/L93,Latitude en WGS 84,Longitude en WGS 84,Intensité épicentrale,Qualité intensité épicentrale");
        stringTest.add("160001,1014/,,ANGOUMOIS (ANGOULEME),CHARENTES,,478075.43,6509611.52,45.65,0.15,7,INFORMATION ISOLÉE");
        stringTest.add(",1079/07/17,,PUISAYE ?,NIVERNAIS,,716280.87,6722202.41,47.60,3.22,6,ARBITRAIRE");
        stringTest.add("490007,1098/10/04,,LOUDUNOIS ?,POITOU,,464575.03,6662003.94,47.02,-0.10,6,ARBITRAIRE");
        stringTest.add("760009,,6 h,BASSE-VALLEE DE LA SEINE (ROUEN),NORMANDIE,,550202.53,6933511.42,49.48,0.93,5,INCERTAINE");
        stringTest.add("30001,1155/01/18,2 h,,FRANCHE-COMTE,,915580.89,6650143.40,46.92,5.83,7,ARBITRAIRE");
        stringTest.add("490013,1169/10/09,6 h,VAL D'ANJOU (ANGERS),,,432668.50,6713378.05,47.47,-0.55,5.5,INFORMATION ISOLÉE");
        stringTest.add("260201,1173/01/20,,TRICASTIN (CLANSAYES),DAUPHINE,SECOUSSE INDIVIDUALISEE D UN ESSAIM,844757.94,6364735.60,44.37,4.82,3,INFORMATION ISOLÉE");
        stringTest.add("300001,1186/03/22,,GARRIGUES (UZES),CEVENNES,,,6327128.64,44.03,4.47,7.5,ASSEZ SURE");
        stringTest.add("870001,1216/03/03,1 h,RIBERACOIS (S-W. LA ROCHEBEAUCOURT ?),GUYENNE,,492463.02,,45.33,0.35,6.5,ARBITRAIRE");
        stringTest.add("680002,1239/09/18,,PLAINE DE HAUTE-ALSACE (RIBEAUVILLE ?),ALSACE,,1020560.50,6797613.84,,7.32,7,INCERTAINE");
        stringTest.add("140002,1241/09/23,20 h,PLAINE DE CAEN ? (CAEN),NORMANDIE,,454598.67,6903409.22,49.18,,5,INCERTAINE");
        stringTest.add("560001,1286/08/30,12 h,VANNETAIS (VANNES),BRETAGNE,,267469.01,6743535.99,47.65,-2.77,7,");
        stringTest.add("670005,1289/09/24,,PLAINE DE BASSE-ALSACE (STRASBOURG),ALSACE,GROUPE DE SECOUSSES D UN ESSAIM,1050141.67,6841992.57,48.58,7.75,6,INCERTAINE");
        stringTest.add("648999,,,,BONJOUR,");

        return stringTest;
    }

    private ArrayList<Seisme> SisFrance_CSV_Seisme_Test(){
        ArrayList<Seisme> seismesTest = new ArrayList<>();
        seismesTest.add(new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE"));
        seismesTest.add(new Seisme(null, "1079/07/17", "", "PUISAYE ?", "NIVERNAIS", "",
                716280.87, 6722202.41, 47.60, 3.22, 6.0, "ARBITRAIRE"));
        seismesTest.add(new Seisme(490007, "1098/10/04", "", "LOUDUNOIS ?", "POITOU", "",
                464575.03, 6662003.94, 47.02, -0.10, 6.0, "ARBITRAIRE"));
        seismesTest.add(new Seisme(760009, "", "6 h", "BASSE-VALLEE DE LA SEINE (ROUEN)", "NORMANDIE", "",
                550202.53, 6933511.42, 49.48, 0.93, 5.0, "INCERTAINE"));
        seismesTest.add(new Seisme(30001, "1155/01/18", "2 h", "", "FRANCHE-COMTE", "",
                915580.89, 6650143.40, 46.92, 5.83, 7.0 , "ARBITRAIRE"));
        seismesTest.add(new Seisme(490013, "1169/10/09", "6 h", "VAL D'ANJOU (ANGERS)", "", "",
                432668.50, 6713378.05, 47.47, -0.55, 5.5, "INFORMATION ISOLÉE"));
        seismesTest.add(new Seisme(260201, "1173/01/20", "", "TRICASTIN (CLANSAYES)", "DAUPHINE", "SECOUSSE INDIVIDUALISEE D UN ESSAIM",
                844757.94, 6364735.60, 44.37, 4.82, 3.0, "INFORMATION ISOLÉE"));
        seismesTest.add(new Seisme(300001, "1186/03/22", "", "GARRIGUES (UZES)", "CEVENNES", "",
                null, 6327128.64, 44.03, 4.47, 7.5, "ASSEZ SURE"));
        seismesTest.add(new Seisme(870001, "1216/03/03", "1 h", "RIBERACOIS (S-W. LA ROCHEBEAUCOURT ?)", "GUYENNE", "",
                492463.02, null, 45.33, 0.35, 6.5, "ARBITRAIRE"));
        seismesTest.add(new Seisme(680002, "1239/09/18", "", "PLAINE DE HAUTE-ALSACE (RIBEAUVILLE ?)", "ALSACE", "",
                1020560.50, 6797613.84, null, 7.32, 7.0, "INCERTAINE"));
        seismesTest.add(new Seisme(140002, "1241/09/23", "20 h", "PLAINE DE CAEN ? (CAEN)", "NORMANDIE", "",
                454598.67, 6903409.22, 49.18, null, 5.0, "INCERTAINE"));
        seismesTest.add(new Seisme(560001, "1286/08/30", "12 h", "VANNETAIS (VANNES)", "BRETAGNE", "",
                267469.01, 6743535.99, 47.65, -2.77, 7.0, ""));
        seismesTest.add(new Seisme(670005, "1289/09/24", "", "PLAINE DE BASSE-ALSACE (STRASBOURG)", "ALSACE", "GROUPE DE SECOUSSES D UN ESSAIM",
                1050141.67, 6841992.57, 48.58, 7.75, 6.0, "INCERTAINE"));

        return seismesTest;
    }

}