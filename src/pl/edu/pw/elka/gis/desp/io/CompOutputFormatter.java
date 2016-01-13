package pl.edu.pw.elka.gis.desp.io;

import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.comp.EdgedDisjointedPaths;
import pl.edu.pw.elka.gis.desp.comp.Path;
import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.util.List;

/**
 * A formatter class for the output of the computation.
 */
public class CompOutputFormatter {

    /**
     * Format a graph and return a string representation of the output.
     * @param graph the graph to be formatted
     * @return string representation of the graph
     */
    public String format(WeightedDiagraph graph) {
        StringBuilder sb = new StringBuilder();
        sb.append(graph.getV()+"\n");
        for (int i = 0; i < graph.getV(); i++) {
            List<DirectedEdge> nodeNeighbors = graph.adjacentTo(i);
            if(!nodeNeighbors.isEmpty()) {
                for (int j = 0; j < nodeNeighbors.size(); j++) {
                    DirectedEdge dEdge = nodeNeighbors.get(j);
                    sb.append(String.format("%d %d %f\n", dEdge.getSrc(), dEdge.getDst(), dEdge.getWeight()));
                }
            }
        }
        return sb.toString();
    }

    /**
     * Format the output of the computation and return a string representation.
     * @param compMethod the method of the computation
     * @param msg message
     * @param edgedDisjointedPaths the result of the algorithm
     * @param time time that algorithm took to execute
     * @return approporiate string representation
     */
    public String formatComp(CompMethod compMethod, String msg, EdgedDisjointedPaths edgedDisjointedPaths,
                             long time) {
        StringBuilder sbuilder = new StringBuilder();
        if(compMethod == CompMethod.Naive)
            sbuilder.append("# naive algorithm\n");
        else
            sbuilder.append("# better algorithm\n");

        if(msg != null)
            sbuilder.append("# " + msg + " \n");

        if(edgedDisjointedPaths == null || (edgedDisjointedPaths.first() == null && edgedDisjointedPaths.second()==null))
            sbuilder.append("# algorithm did not find any path\n");
        else if (edgedDisjointedPaths.first() != null && edgedDisjointedPaths.second() == null)
            sbuilder.append("# algorithm found only one path\n");
        else if (edgedDisjointedPaths.first() == null && edgedDisjointedPaths.second() != null)
            sbuilder.append("# algorithm found only one path\n");
        else
            sbuilder.append("# algorithm found two paths\n");


        if(assessResult(edgedDisjointedPaths))
            sbuilder.append("S\n");
        else
            sbuilder.append("F\n");

        if(edgedDisjointedPaths != null) {
            sbuilder.append(formatPath(edgedDisjointedPaths.first()));
            sbuilder.append(formatPath(edgedDisjointedPaths.second()));
            sbuilder.append(edgedDisjointedPaths.getTotalDistance() + "\n");
        } else {
            sbuilder.append("--\n--\n--\n--\n");
        }

        sbuilder.append(time+"\n");
        return sbuilder.toString();
    }

    public String formatComp(CompMethod compMethod, EdgedDisjointedPaths edgedDisjointedPaths,
                             long time) {
        return formatComp(compMethod, null, edgedDisjointedPaths, time);
    }


    private String formatPath(Path path) {
        if(path == null) return "--\n--\n";
        StringBuilder sbuilder = new StringBuilder();
        for(DirectedEdge f: path.getEdges())
            sbuilder.append(String.format("(%d, %d) ", f.getSrc(), f.getDst()));
        sbuilder.append("\n");
        sbuilder.append(path != null ? path.getDistance() : "");
        sbuilder.append("\n");
        return sbuilder.toString();
    }

    private boolean assessResult(EdgedDisjointedPaths paths) {
        if(paths == null)
            return false;
        if(paths.first() == null || paths.second() == null)
            return false;
        if(paths.first().equals(paths.second()))
            return false;
        return true;
    }


}
