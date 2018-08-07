package ru.job4j.pseudo;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Khmelevskiy(mailto(igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(), is(new StringBuilder().append("++++").append("+     +").append("+     +").append("++++").toString())
        );
    }
}