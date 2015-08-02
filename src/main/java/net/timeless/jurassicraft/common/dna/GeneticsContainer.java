package net.timeless.jurassicraft.common.dna;

public class GeneticsContainer
{
    private String genetics;

    private static final int DINOSAUR = 0;
    private static final int GENDER = 1;
    private static final int COLOR = 2;
    private static final int SCALE = 3;
    private static final int CAUTIOUSNESS = 5;
    private static final int AGRESSION = 6;
    private static final int GENETIC_VARIATION = 4;

    public GeneticsContainer(String genetics)
    {
        this.genetics = genetics;
    }

    public GeneticsContainer(int dinosaur, boolean gender, int colorOffset, int scaleOffset, int cautiousness, int agression, int geneticVariation)
    {
        genetics = defaultGeneticCode();
        set(DINOSAUR, dinosaur);
        set(GENDER, gender ? 0 : 1);
        set(COLOR, colorOffset);
        set(SCALE, scaleOffset);
        set(CAUTIOUSNESS, cautiousness);
        set(AGRESSION, agression);
        set(GENETIC_VARIATION, geneticVariation);
    }

    private String defaultGeneticCode()
    {
        return "AAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    }

    public int getDinosaur()
    {
        return get(DINOSAUR);
    }

    public boolean isMale()
    {
        return get(GENDER) == 0;
    }

    public boolean isFemale()
    {
        return !isMale();
    }

    public int getColorOffset()
    {
        return get(COLOR);
    }

    public int getScaleOffset()
    {
        return get(SCALE);
    }

    public int getCautiousness()
    {
        return get(CAUTIOUSNESS);
    }

    public int getAgression()
    {
        return get(AGRESSION);
    }

    public int getGeneticVariation()
    {
        return get(GENETIC_VARIATION);
    }

    public int get(int id)
    {
        int charStart = id * 4;

        if(charStart >= 0 && charStart + 3 < genetics.length())
        {
            return convert(genetics.charAt(charStart), genetics.charAt(charStart + 1), genetics.charAt(charStart + 2), genetics.charAt(charStart + 3));
        }

        return -1;
    }

    public void set(int id, int value)
    {
        int charStart = id * 4;

        if(charStart >= 0 && charStart + 3 < genetics.length())
        {
            char[] chars = convert(value);

            StringBuilder sb = new StringBuilder(genetics);

            for (int i = 0; i < chars.length; i++)
            {
                sb.setCharAt(i + charStart, chars[i]);
            }

            genetics = sb.toString();
        }
    }

    public int convert(char... chars)
    {
        int value = 0;

        int i = 0;

        for (char c : chars)
        {
            if(c == 'C')
                value += 1 * Math.pow(4, i);
            else if(c == 'G')
                value += 2 * Math.pow(4, i);
            else if(c == 'T')
                value += 3 * Math.pow(4, i);

            i++;
        }

        return value;
    }

    public char[] convert(int value)
    {
        char[] chars = new char[4];

        int next = value;

        int index = 0;

        while(next > 0)
        {
            int r = next % 4;
            next = (int) Math.floor(next / 4);

            switch (r)
            {
                case 0:
                {
                    chars[index] = 'A';
                    break;
                }
                case 1:
                {
                    chars[index] = 'C';
                    break;
                }
                case 2:
                {
                    chars[index] = 'G';
                    break;
                }
                case 3:
                {
                    chars[index] = 'T';
                    break;
                }
            }

            index++;
        }

        for (int i = index; i < 4; i++)
        {
            chars[i] = 'A';
        }

        return chars;
    }

    public String toString()
    {
        return genetics;
    }
}
