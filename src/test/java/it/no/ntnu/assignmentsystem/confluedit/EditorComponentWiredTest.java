package it.no.ntnu.assignmentsystem.confluedit;

import no.ntnu.assignmentsystem.confluedit.EditorPluginComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class EditorComponentWiredTest {
    private final ApplicationProperties applicationProperties;
    private final EditorPluginComponent myPluginComponent;

    public EditorComponentWiredTest(ApplicationProperties applicationProperties, EditorPluginComponent myPluginComponent) {
        this.applicationProperties = applicationProperties;
        this.myPluginComponent = myPluginComponent;
    }

    @Test
    public void testMyName()
    {
        assertEquals("names do not match!", "myComponent:" + applicationProperties.getDisplayName(),myPluginComponent.getName());
    }
}
