package impl;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class RolePropertyAccessor1Test {

    @Test
    void testProperties() {
        RolePropertyAccessor1 rpa = new RolePropertyAccessor1();
        for (int i = 0; i < rpa.getProperties().length; i++) {
            assertTrue(rpa.getProperties()[i].getIndex() == i);
        }
    }
}