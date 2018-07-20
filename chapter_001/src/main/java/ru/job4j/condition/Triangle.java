package ru.job4j.condition;

import java.lang.Math;
/**
 * @author Igor Khmelevskiy (igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула
     *
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return p Полупериметр
     */
    public double period(double ab, double bc, double ac) {
        ab = this.a.distanceTo(this.b);
        ac = this.a.distanceTo(this.c);
        bc = this.b.distanceTo(this.c);
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод вычисления площади треугольника
     *
     * @return Вернуть площадь, если треугольник существует, или -1, если его нет
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            return Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        } else {
            return rsl;
        }
    }

    /**
     * Метод проверяет, можно ли построить треугольник с такими длинами сторон
     *
     * @param ab Длина от точки a до b
     * @param ac Длина от точки a до c
     * @param bc Длина от точки b до c
     */
    private boolean exist(double ab, double ac, double bc) {
        return ((ab + ac) > bc) && ((ac + bc) > ab) && ((ab + bc) > ac);
    }
}
