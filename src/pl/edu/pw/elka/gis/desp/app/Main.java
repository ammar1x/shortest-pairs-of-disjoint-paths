package pl.edu.pw.elka.gis.desp.app;

import pl.edu.pw.elka.gis.desp.comp.*;
import pl.edu.pw.elka.gis.desp.io.CompOutputFormatter;
import pl.edu.pw.elka.gis.desp.io.WeightedDiagraphException;
import pl.edu.pw.elka.gis.desp.io.WeightedDiagraphReader;
import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The main entry for this app.
 */
public class Main {


    public static void main(String[] args)  {
        try {
            run(new String[]{"1", "2", "N", "dataset/parsing/example1"});
        }  catch (WeightedDiagraphException e) {
            System.out.println("error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("error: " + "cannot find given file");
        }
    }




    public static void run(String[] args) throws FileNotFoundException, WeightedDiagraphException {
        int src = new Integer(args[0]);
        int dst = new Integer(args[1]);
        String algorithm = args[2];
        String filepath = args[3];
        CompMethod compMethod = algorithm.equalsIgnoreCase("U") ? CompMethod.Better : CompMethod.Naive;

        WeightedDiagraph weightedDiagraph = null;
        WeightedDiagraphReader weightedDiagraphReader = new WeightedDiagraphReader();
        weightedDiagraph = weightedDiagraphReader.read(filepath);

        KShortestDisjoint algo = null;
        if(compMethod == CompMethod.Naive)
            algo = new KShortestDisjointNaive(weightedDiagraph, src, dst);
        else
            algo = new KShortestDisjointBetter(weightedDiagraph, src, dst);

        algo.run();
        long start = System.currentTimeMillis();
        EdgedDisjointedPaths paths = algo.getPaths();
        long time = (System.currentTimeMillis() - start);

        CompOutputFormatter compOutputFormatter = new CompOutputFormatter();
        String output = compOutputFormatter.formatComp(compMethod, paths, time);
        System.out.println(output);
    }



}
