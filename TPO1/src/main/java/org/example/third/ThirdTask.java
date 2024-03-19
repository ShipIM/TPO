package org.example.third;

import org.example.third.body.BodyPartType;
import org.example.third.character.Arthur;
import org.example.third.character.EmotionalState;
import org.example.third.character.Unknown;
import org.example.third.furniture.Armchair;
import org.example.third.furniture.ControlPanel;

public class ThirdTask {

    public static void main(String[] args) {
        Arthur arthur = new Arthur();
        Unknown unknown = new Unknown();

        Armchair armchair = new Armchair();
        armchair.setUser(unknown);

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setUser(unknown);

        System.out.println(
                arthur.changeState(EmotionalState.NERVOUS) +
                arthur.perform(() -> "followed in") +
                arthur.changeState(EmotionalState.OVERWHELMED) +
                arthur.perform(() -> "sees unknown") +
                unknown.changeState(EmotionalState.CHILL) +
                unknown.perform(() -> "is sitting") +
                armchair.use() +
                unknown.trigger(BodyPartType.LEGS) +
                controlPanel.use() +
                unknown.trigger(BodyPartType.HAND) +
                unknown.trigger(BodyPartType.TEETH) +
                unknown.changeState(EmotionalState.BUSY) +
                unknown.trigger(BodyPartType.HEAD) +
                unknown.changeState(EmotionalState.HAPPY) +
                arthur.trigger(BodyPartType.EYES) +
                arthur.changeState(EmotionalState.SURPRISED) +
                arthur.trigger(BodyPartType.JAW)
        );
    }

}
