package net.reuxertz.ecoapi.ecology.genetics;

public abstract class BaseGene implements IGene
{
    public enum SexLinked { Null, Female, Male }

    protected Class entitySpecie;
    protected SexLinked sexLinked = SexLinked.Null;

    public boolean geneIsAnalog(IGene g)
    {
        return this.getClass().isInstance(g);
    }

    public BaseGene(Class entity)
    {
        this.entitySpecie = entity;
    }
}
