package org.example.third.body;

public class Eyes extends BodyPart {

    public Eyes() {
        super("eyes", BodyPartType.EYES);
    }

    @Override
    public String render() {
        return this.getName() + " are looking\n";
    }

}
