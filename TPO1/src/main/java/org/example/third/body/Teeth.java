package org.example.third.body;

public class Teeth extends BodyPart {

    public Teeth() {
        super("teeth", BodyPartType.TEETH);
    }

    @Override
    public String render() {
        return this.getName() + " are being cleaned\n";
    }

}
