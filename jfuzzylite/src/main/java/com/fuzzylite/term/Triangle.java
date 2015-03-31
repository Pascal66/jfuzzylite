/*
 Author: Juan Rada-Vilela, Ph.D.
 Copyright (C) 2010-2014 FuzzyLite Limited
 All rights reserved

 This file is part of jfuzzylite.

 jfuzzylite is free software: you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation, either version 3 of the License, or (at your option)
 any later version.

 jfuzzylite is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with jfuzzylite.  If not, see <http://www.gnu.org/licenses/>.

 fuzzylite™ is a trademark of FuzzyLite Limited.
 jfuzzylite™ is a trademark of FuzzyLite Limited.

 */
package com.fuzzylite.term;

import com.fuzzylite.Op;
import java.util.Iterator;
import java.util.List;

public class Triangle extends Term {

    private double vertexA, vertexB, vertexC;

    public Triangle() {
        this("");
    }

    public Triangle(String name) {
        this(name, Double.NaN, Double.NaN, Double.NaN);
    }

    public Triangle(String name, double vertexA, double vertexC) {
        this(name, vertexA, (vertexA + vertexC) / 2.0, vertexC);
    }

    public Triangle(String name, double vertexA, double vertexB, double vertexC) {
        this.name = name;
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = vertexC;
    }

    @Override
    public String parameters() {
        return Op.join(" ", vertexA, vertexB, vertexC)
                + (!Op.isEq(height, 1.0) ? " " + Op.str(height) : "");
    }

    @Override
    public void configure(String parameters) {
        if (parameters.isEmpty()) {
            return;
        }
        List<String> values = Op.split(parameters, " ");
        int required = 3;
        if (values.size() < required) {
            throw new RuntimeException(String.format(
                    "[configuration error] term <%s> requires <%d> parameters",
                    this.getClass().getSimpleName(), required));
        }
        Iterator<String> it = values.iterator();
        setVertexA(Op.toDouble(it.next()));
        setVertexB(Op.toDouble(it.next()));
        setVertexC(Op.toDouble(it.next()));
        if (values.size() > required) {
            setHeight(Op.toDouble(it.next()));
        }
    }

    @Override
    public double membership(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        if (Op.isLE(x, vertexA) || Op.isGE(x, vertexC)) {
            return 0.0;
        } else if (Op.isEq(x, vertexB)) {
            return 1.0;
        } else if (Op.isLt(x, vertexB)) {
            return (x - vertexA) / (vertexB - vertexA);
        } else {
            return (vertexC - x) / (vertexC - vertexB);
        }
    }

    public double getVertexA() {
        return vertexA;
    }

    public void setVertexA(double vertexA) {
        this.vertexA = vertexA;
    }

    public double getVertexB() {
        return vertexB;
    }

    public void setVertexB(double vertexB) {
        this.vertexB = vertexB;
    }

    public double getVertexC() {
        return vertexC;
    }

    public void setVertexC(double vertexC) {
        this.vertexC = vertexC;
    }

    @Override
    public Triangle clone() throws CloneNotSupportedException {
        return (Triangle) super.clone();
    }

}
