package pl.edu.pw.elka.gis.desp.io;


import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.util.Scanner;

/**
 * Read a weighted diagraph from input stream.
 */
public class WeightedDiagraphReader {

    /**
     * Parse input stream and return weighted diagraph. Throws an exception if the src is invalid.
     *
     * @param readable input stream
     * @return weighted diagraph object
     * @throws WeightedDiagraphException when format of the input stream is invalid
     */
    public WeightedDiagraph read(Readable readable) throws WeightedDiagraphException{
        WeightedDiagraph weightedDiagraph = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(readable);
            int v = scanner.nextInt();
            if(v <= 0)
                throw new WeightedDiagraphException("number of vertices must be an integer > 0");
            weightedDiagraph = new WeightedDiagraph(v);
            while(scanner.hasNextLine()) {
                int src = scanner.nextInt();
                int dst = scanner.nextInt();
                double weight = scanner.nextDouble();
                checkConditions(src, dst, weight, v);
                weightedDiagraph.addEdge(src, dst, weight);
            }
        } catch (IOError e) {
            throw  new WeightedDiagraphException("error while parsing diagraph from stream", e);
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
        return weightedDiagraph;
    }

    /**
     * Read and parse weighted diagraph from given file.
     * @param filePath the path to the file
     * @return WeightedDiagraph object
     * @throws WeightedDiagraphException if format of the file is invalid
     * @throws FileNotFoundException if the file was not found
     */
    public WeightedDiagraph read(String filePath) throws WeightedDiagraphException, FileNotFoundException {
        return read(new FileReader(filePath));
    }

    private void checkConditions(int src, int dst, double weight, int v) throws WeightedDiagraphException {
        if(src < 0 || src >= v || dst < 0 || dst >= v)
            throw new WeightedDiagraphException("vertix id has to be in range [0, v)");
        if(weight < 0)
            throw new WeightedDiagraphException("weight of the directed edge has to nonnegative real number");
    }

    /**
     * test this class
     * @param args
     */
    public static void main(String[] args) {
        WeightedDiagraphReader weightedDiagraphReader = new WeightedDiagraphReader();
        CompOutputFormatter formatter = new CompOutputFormatter();
        try {
            WeightedDiagraph weightedDiagraph = weightedDiagraphReader.read("dataset/example1");
            System.out.println(formatter.format(weightedDiagraph));
        } catch (WeightedDiagraphException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

