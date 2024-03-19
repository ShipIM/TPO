package org.example.third.body;

public class Jaw extends BodyPart{

    public Jaw() {
        super("jaw", BodyPartType.JAW);
    }

    @Override
    public String render() {
        return this.getName() + " dropped\n";
    }

}
