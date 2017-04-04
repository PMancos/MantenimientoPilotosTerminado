
package mantenimientopilotos;

import java.util.Comparator;


public class OrdenarPorNumeroPremios implements Comparator <Piloto>{

    @Override
    public int compare(Piloto o1, Piloto o2) {
        return o2.getNumPremios()-o1.getNumPremios();
    }
    
}
