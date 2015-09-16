package net.reuxertz.ecoapi.ecology.genetics;


import java.util.List;

public interface IGeneticOrganism
{
    List<IGene> generateRequiredGenesForLife();

    List<GeneEntitySkin> getSkinGenes();

    GeneEntitySkin getBaseSkinGene();
}
