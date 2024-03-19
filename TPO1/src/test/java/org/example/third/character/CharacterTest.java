package org.example.third.character;

import org.example.third.body.*;
import org.example.third.exception.MissingActionException;
import org.example.third.exception.MissingBodyPartsException;
import org.example.third.exception.MissingEmotionalStateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class CharacterTest {

    private static Human human;
    private static String action;

    @BeforeAll
    static void init() {
        human = new Human("test human",
                List.of(new Eyes(), new Jaw(), new LeftHand(), new LeftHead(),
                        new Legs(), new RightHead(), new Teeth()));
        action = "test action";
    }

    @ParameterizedTest
    @EnumSource(EmotionalState.class)
    public void changeStateReturnsString(EmotionalState emotionalState) {
        Assertions.assertEquals(human.getName() + " is feeling " + emotionalState.name(),
                human.changeState(emotionalState).trim());
    }

    @Test
    public void changeStateOnNull() {
        Assertions.assertThrows(MissingEmotionalStateException.class,
                () -> human.changeState(null));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "EYES, eyes are looking",
            "JAW, jaw dropped",
            "HAND, left hand picking",
            "HEAD, left head is smilingtest human's right head is busy",
            "LEGS, legs are lying",
            "TEETH, teeth are being cleaned"
    })
    public void triggerReturnsString(BodyPartType type, String expected) {
        Assertions.assertEquals(human.getName() + "'s " + expected,
                human.trigger(type).replace("\n", ""));
    }

    @Test
    public void triggerOnNullBodyParts() {
        Human test = new Human("another test human", null);

        Assertions.assertThrows(MissingBodyPartsException.class,
                () -> test.trigger(BodyPartType.JAW));
    }

    @ParameterizedTest
    @MethodSource("provideHuman")
    public void performReturnsString(Human human) {
        Assertions.assertEquals(human.getName() + " " + action ,
                human.perform(() -> action).trim());
    }

    @Test
    public void performOnNullThrowsException() {
        Assertions.assertThrows(MissingActionException.class,
                () -> human.perform(() -> null));
    }

    public static Stream<Arguments> provideHuman() {
        Arthur arthur = new Arthur();
        Unknown unknown = new Unknown();

        return Stream.of(
                Arguments.of(arthur),
                Arguments.of(unknown),
                Arguments.of(human)
        );
    }

}
