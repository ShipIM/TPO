package org.example.third.character;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.third.action.Action;
import org.example.third.body.BodyPart;
import org.example.third.body.BodyPartType;
import org.example.third.exception.MissingActionException;
import org.example.third.exception.MissingBodyPartsException;
import org.example.third.exception.MissingEmotionalStateException;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class Human implements Performer {

    private final String name;

    private final List<BodyPart> bodyParts;

    public String trigger(final BodyPartType type) {
        if (Objects.isNull(bodyParts)) {
            throw new MissingBodyPartsException("body parts can't be null");
        }

        StringBuilder builder = new StringBuilder();

        bodyParts.stream()
                .filter(part -> part.getType().equals(type))
                .map(BodyPart::render)
                .forEach(str -> builder.append(name).append("'s ").append(str));

        return builder.toString();
    }

    public String perform(Action action) {
        if (Objects.isNull(action.act())) {
            throw new MissingActionException("action can't be null");
        }

        return name + " " + action.act() + "\n";
    }

    public String changeState(EmotionalState state) {
        if (Objects.isNull(state)) {
            throw new MissingEmotionalStateException("emotional state can't be null");
        }

        return this.name + " is feeling " + state + "\n";
    }

    public String getName() {
        return name;
    }

}
