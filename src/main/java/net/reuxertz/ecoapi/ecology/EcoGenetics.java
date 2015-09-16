package net.reuxertz.ecoapi.ecology;

import net.reuxertz.ecoapi.ecology.genetics.IGene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EcoGenetics
{
    private static HashMap<Class, List<IGene>> entityRequiredGenes = new HashMap<Class, List<IGene>>();
    private static HashMap<Class, List<IGene>> entityAvailableGenes = new HashMap<Class, List<IGene>>();

    public static void addClassRequiredGene(Class entityClass, IGene gene)
    {
        if (EcoGenetics.entityRequiredGenes.containsKey(entityClass))
            EcoGenetics.entityRequiredGenes.put(entityClass, new ArrayList<IGene>());

        List<IGene> l = EcoGenetics.entityRequiredGenes.get(entityClass);
        l.add(gene);

        EcoGenetics.addClassAvailableGene(entityClass, gene);
    }
    public static void addClassAvailableGene(Class entityClass, IGene gene)
    {
        if (EcoGenetics.entityAvailableGenes.containsKey(entityClass))
            EcoGenetics.entityAvailableGenes.put(entityClass, new ArrayList<IGene>());

        List<IGene> l = EcoGenetics.entityAvailableGenes.get(entityClass);
        l.add(gene);
    }
}
