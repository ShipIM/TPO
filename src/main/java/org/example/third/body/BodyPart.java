package org.example.third.body;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class BodyPart {

    private final String name;

    private final BodyPartType type;

    public abstract String render();

}
