package org.example.third.body;

public class LeftHand extends BodyPart {

    public LeftHand() {
        super("left hand", BodyPartType.HAND);
    }

    @Override
    public String render() {
        return this.getName() + " picking\n";
    }

}
