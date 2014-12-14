package no.ntnu.assignmentsystem.confluedit;

import com.atlassian.sal.api.ApplicationProperties;

/**
 * Created by Lasse on 25.11.2014.
 */
public class EditorPluginComponentImpl implements EditorPluginComponent {
    private final ApplicationProperties applicationProperties;

    public EditorPluginComponentImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String getName()
    {
        if(null != applicationProperties)
        {
            return "myComponent:" + applicationProperties.getDisplayName();
        }

        return "myComponent";
    }
}
