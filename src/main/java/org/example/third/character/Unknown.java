package org.example.third.character;

import org.example.third.body.*;

import java.util.List;

public class Unknown extends Human {

    public Unknown() {
        super("Unknown",
                List.of(new LeftHand(), new Legs(), new RightHead(), new LeftHead(), new Teeth()));
    }

}
