package ntbea.params;

import evodef.AnnotatedFitnessSpace;

public class Report {
    public static String report(AnnotatedFitnessSpace afs, int[] solution) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<afs.getParams().length; i++) {
            Param p = afs.getParams()[i];
            sb.append(p.getName() + " : " + p.getValue(solution[i]) + "\n");
        }
        return sb.toString();
    }


}
