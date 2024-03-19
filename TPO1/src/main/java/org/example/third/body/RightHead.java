package org.example.third.body;

public class RightHead extends BodyPart {

    public RightHead() {
        super("right head", BodyPartType.HEAD);
    }

    @Override
    public String render() {
        return this.getName() + " is busy\n";
    }

}
