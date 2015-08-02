package net.timeless.jurassicraft.common.dna;

public class GeneticsContainer
{
    private String genetics;

    private static final int DINOSAUR = 0; //Which dinosaur is this?
    private static final int GENDER = 1; //Gender 0 = male, 1 = female
    private static final int COLOR = 2; //Color offset
    private static final int SCALE = 3; //Scale offset
    private static final int CAUTIOUSNESS = 5; //How cautious is this dinosaur
    private static final int AGRESSION = 6; //How agressive is this dinosaur
    private static final int GENETIC_VARIATION = 4; //Skin + Model variants (Blue, Echo, Delta, Charlie)
    private static final int LIFE_TIME = 7; //How long to live after adulthood
    private static final int SPEED = 8; //Speed Modifier
    private static final int ADULT_TIME = 9; //How long to become an adult

    private static final int VAR_COUNT = 9;

    public GeneticsContainer(String genetics)
    {
        this.genetics = genetics;
    }

    public GeneticsContainer(int dinosaur, boolean gender, int colorOffset, int scaleOffset, int cautiousness, int agression, int geneticVariation, int adultTime, int lifeTime, int speed)
    {
        genetics = defaultGeneticCode();
        set(DINOSAUR, dinosaur);
        set(GENDER, gender ? 0 : 1);
        set(COLOR, colorOffset);
        set(SCALE, scaleOffset);
        set(CAUTIOUSNESS, cautiousness);
        set(AGRESSION, agression);
        set(GENETIC_VARIATION, geneticVariation);
        set(SPEED, speed);
        set(LIFE_TIME, lifeTime);
        set(ADULT_TIME, adultTime);
    }

    private String defaultGeneticCode()
    {
        String code = "";

        for (int i = 0; i < VAR_COUNT; i++)
        {
            code += "AAAA";
        }

        return code;
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

    public int getSpeed()
    {
        return get(SPEED);
    }

    public int getLifeTime()
    {
        return get(LIFE_TIME);
    }

    public int getAdultTime()
    {
        return get(ADULT_TIME);
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
