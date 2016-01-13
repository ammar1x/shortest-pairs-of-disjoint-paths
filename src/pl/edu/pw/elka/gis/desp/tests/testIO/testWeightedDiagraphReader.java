package pl.edu.pw.elka.gis.desp.tests.testIO;

import pl.edu.pw.elka.gis.desp.io.WeightedDiagraphException;
import pl.edu.pw.elka.gis.desp.io.WeightedDiagraphReader;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.io.FileNotFoundException;

/**
 * test weighted diagraph reader.
 */
public class testWeightedDiagraphReader {

    public static boolean shouldRunOk(String file) {
        WeightedDiagraphReader weightedDiagraphReader = new WeightedDiagraphReader();
        try {
            WeightedDiagraph weightedDiagraph = weightedDiagraphReader.read("dataset/parsing/example1");
        } catch (WeightedDiagraphException e) {
            return false;
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static boolean shouldThrowException(String filepath) {
        WeightedDiagraphReader weightedDiagraphReader = new WeightedDiagraphReader();
        try {
            WeightedDiagraph weightedDiagraph = weightedDiagraphReader.read(filepath);
        } catch (WeightedDiagraphException e) {
            System.out.println(e.getMessage());
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }




    public static void main(String[] args) {
        String testDir = "dataset/parsing/";
        System.out.println("first test: " + (shouldRunOk(testDir+"example1") ? "OK" : "Failed"));
        System.out.println("second test: " + (shouldThrowException(testDir+"example2") ? "OK" : "Failed"));
        System.out.println("second test: " + (shouldThrowException(testDir+"example3") ? "OK" : "Failed"));
    }

}
