package game.util;

public class CoordinateTranslator {
    private double confW;
    private double confH;
    private double viewW;
    private double viewH;

    public CoordinateTranslator(double confW, double confH, double viewW, double viewH) {
        this.confW = confW;
        this.confH = confH;
        this.viewW = viewW;
        this.viewH = viewH;
    }

    public Coordinate translatePoint(double x, double y, double camX, double camY)
    {
        double dx = x/confW * viewW;
        double dy = y/confH * viewH;

        double c0x = camX - viewW/2;
        double c0y = camY + viewH/2;

        double mapX = c0x + dx;
        double mapY = c0y - dy;

        return new Coordinate(mapX, mapY);
    }

    public class Coordinate{
        public double x;
        public double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
