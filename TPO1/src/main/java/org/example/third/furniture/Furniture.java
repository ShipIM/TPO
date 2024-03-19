package org.example.third.furniture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.third.character.Human;
import org.example.third.exception.MissingOwnerException;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
public class Furniture {

    private final String name;

    private Human user;

    public String use() {
        if (Objects.isNull(user)) {
            throw new MissingOwnerException("user can't be null");
        }

        return user.getName() + " uses " + name + "\n";
    }

}
