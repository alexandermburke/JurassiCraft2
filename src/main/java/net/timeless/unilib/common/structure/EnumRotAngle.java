package net.timeless.unilib.common.structure;

public enum EnumRotAngle {
    DEGREES_90(90, 1),
    DEGREES_180(180, 2),
    DEGREES_270(270, 3);

    private final int angle;
    private final int turnsCount;

    EnumRotAngle(int angle, int turnsCount) {
        this.angle = angle;
        this.turnsCount = turnsCount;
    }

    public int value() {
        return angle;
    }

    public int turnsCount() {
        return turnsCount;
    }
}
