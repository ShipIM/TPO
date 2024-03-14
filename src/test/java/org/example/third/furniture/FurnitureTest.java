package org.example.third.furniture;

import org.example.third.character.Human;
import org.example.third.exception.MissingOwnerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FurnitureTest {

    @ParameterizedTest
    @MethodSource("provideFurniture")
    public void useReturnsString(Furniture furniture) {
        Assertions.assertEquals(furniture.getUser().getName() + " uses " + furniture.getName(),
                furniture.use().trim());
    }

    @ParameterizedTest
    @MethodSource("provideFurniture")
    public void useWithOwnerChangeReturnsString(Furniture furniture) {
        furniture.setUser(new Human("new owner", null));

        Assertions.assertEquals(furniture.getUser().getName() + " uses " + furniture.getName(),
                furniture.use().trim());
    }

    @Test
    public void useWithNullUserThrowsException() {
        Furniture furniture = new Furniture("thing");

        Assertions.assertThrows(MissingOwnerException.class,
                furniture::use);
    }

    private static Stream<Arguments> provideFurniture() {
        Human human = new Human("someone", null);

        Armchair armchair = new Armchair();
        armchair.setUser(human);

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setUser(human);

        Furniture furniture = new Furniture("thing");
        furniture.setUser(human);

        return Stream.of(
                Arguments.of(armchair),
                Arguments.of(controlPanel),
                Arguments.of(furniture)
        );
    }

}
