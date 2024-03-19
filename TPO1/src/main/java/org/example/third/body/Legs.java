package org.example.third.body;

public class Legs extends BodyPart{

    public Legs() {
        super("legs", BodyPartType.LEGS);
    }

    @Override
    public String render() {
        return this.getName() + " are lying\n";
    }

}
