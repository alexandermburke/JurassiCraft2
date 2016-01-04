package org.jurassicraft.common.genetics;

public class GeneticsContainer
{
    private String genetics;

    public static final int DINOSAUR = 0; // Which dinosaur is this?
    private static final int OVERLAY_R = 1;
    private static final int OVERLAY_G = 2;
    private static final int OVERLAY_B = 3;
    private static final int OVERLAY_1 = 4;
    private static final int OVERLAY_2 = 5;
    private static final int OVERLAY_3 = 6;

    private static final int VAR_COUNT = 6;

    public GeneticsContainer(String genetics)
    {
        this.genetics = genetics;
    }

    public GeneticsContainer(int dinosaur, int overlayR, int overlayG, int overlayB, int overlay1, int overlay2, int overlay3)
    {
        genetics = defaultGeneticCode();
        set(DINOSAUR, dinosaur);
        set(OVERLAY_R, overlayR);
        set(OVERLAY_G, overlayG);
        set(OVERLAY_B, overlayB);
        set(OVERLAY_1, overlay1);
        set(OVERLAY_2, overlay2);
        set(OVERLAY_3, overlay3);
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

    public int getOverlayR()
    {
        return get(OVERLAY_R);
    }

    public int getOverlayG()
    {
        return get(OVERLAY_G);
    }

    public int getOverlayB()
    {
        return get(OVERLAY_B);
    }

    public int getOverlay1()
    {
        return get(OVERLAY_1);
    }

    public int getOverlay2()
    {
        return get(OVERLAY_2);
    }

    public int getOverlay3()
    {
        return get(OVERLAY_3);
    }

    private int get(int id)
    {
        int charStart = id * 4;

        if (charStart >= 0 && charStart + 3 < genetics.length())
        {
            return convert(genetics.charAt(charStart), genetics.charAt(charStart + 1), genetics.charAt(charStart + 2), genetics.charAt(charStart + 3));
        }

        return -1;
    }

    public void set(int id, int value)
    {
        int charStart = id * 4;

        if (charStart >= 0 && charStart + 3 < genetics.length())
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

    private int convert(char... chars)
    {
        int value = 0;

        int i = 0;

        for (char c : chars)
        {
            if (c == 'C')
            {
                value += 1 * Math.pow(4, i);
            }
            else if (c == 'G')
            {
                value += 2 * Math.pow(4, i);
            }
            else if (c == 'T')
            {
                value += 3 * Math.pow(4, i);
            }

            i++;
        }

        return value;
    }

    private char[] convert(int value)
    {
        char[] chars = new char[4];

        int next = value;

        int index = 0;

        while (next > 0)
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
