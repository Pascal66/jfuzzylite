/*
 Copyright © 2010-2016 by FuzzyLite Limited.
 All rights reserved.

 This file is part of jfuzzylite™.

 jfuzzylite™ is free software: you can redistribute it and/or modify it under
 the terms of the FuzzyLite License included with the software.

 You should have received a copy of the FuzzyLite License along with 
 jfuzzylite™. If not, see <http://www.fuzzylite.com/license/>.

 fuzzylite® is a registered trademark of FuzzyLite Limited.
 jfuzzylite™ is a trademark of FuzzyLite Limited.

 */
package com.fuzzylite.factory;

import com.fuzzylite.activation.Activation;
import com.fuzzylite.activation.First;
import com.fuzzylite.activation.General;
import com.fuzzylite.activation.Highest;
import com.fuzzylite.activation.Last;
import com.fuzzylite.activation.Lowest;
import com.fuzzylite.activation.Proportional;
import com.fuzzylite.activation.Threshold;

public class ActivationFactory extends ConstructionFactory<Activation> {

    public ActivationFactory() {
        register("", null);
        register(First.class);
        register(General.class);
        register(Highest.class);
        register(Last.class);
        register(Lowest.class);
        register(Proportional.class);
        register(Threshold.class);
    }

    @Override
    public ActivationFactory clone() throws CloneNotSupportedException {
        return (ActivationFactory) super.clone();
    }
}
