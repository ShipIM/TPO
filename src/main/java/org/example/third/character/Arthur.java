package org.example.third.character;

import org.example.third.body.Eyes;
import org.example.third.body.Jaw;

import java.util.List;

public class Arthur extends Human {

    public Arthur() {
        super("Anton",
                List.of(new Eyes(), new Jaw()));
    }

}
