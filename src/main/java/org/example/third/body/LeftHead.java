package org.example.third.body;

public class LeftHead extends BodyPart {

    public LeftHead() {
        super("left head", BodyPartType.HEAD);
    }

    @Override
    public String render() {
        return this.getName() + " is smiling\n";
    }

}
