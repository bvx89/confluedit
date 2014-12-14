package ut.no.ntnu.assignmentsystem.confluedit;

import no.ntnu.assignmentsystem.confluedit.EditorPluginComponent;
import no.ntnu.assignmentsystem.confluedit.EditorPluginComponentImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Created by Lasse on 25.11.2014.
 */
public class EditorComponentUnitTest {
    @Test
    public void testMyName()
    {
        EditorPluginComponent component = new EditorPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent", component.getName());
    }
}
