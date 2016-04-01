package cz.mikealdo.tests;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestUsingMultipleAssertClasses2 {

    @Test
    public void shouldPassWithMultipleAssertions() throws Exception {
        assertTrue(1 > 0);
        com.google.common.truth.Truth.assertThat(1).isGreaterThan(0);
        org.assertj.core.api.AssertionsForInterfaceTypes.assertThat(1).isGreaterThan(0);
    }
}
