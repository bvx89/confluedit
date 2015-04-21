package no.hal.eclipsky.confluedit;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import org.apache.velocity.VelocityContext;

import java.util.Map;

public class EditorMacro implements Macro {
    public static final String PARAM_ID = "task-id";
    public static final String PARAM_DIFFICULTY = "difficulty";
    public static final String PARAM_EFFORT = "effort";
    public static final String PARAM_EDITABLE = "editable";
    public static final String BODY_EMFS = "editorCode";
    private static final String TMPL_EDITOR = "templates/editor-macro.vm";

    public EditorMacro() {
    }

    @Override
    public String execute(Map<String, String> parameters, String body, ConversionContext conversionContext) throws MacroExecutionException {
        // Template context
        VelocityContext contextMap = new VelocityContext(MacroUtils.defaultVelocityContext());

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            contextMap.put(entry.getKey(), entry.getValue());
        }

        // Find an appropriate user id
        ConfluenceUser confluenceUser = AuthenticatedUserThreadLocal.get();
        String userId = null;
        if (confluenceUser != null) {
            userId = confluenceUser.getKey().getStringValue();
        }

        contextMap.put("user-id", userId);
        contextMap.put(BODY_EMFS, body);

        return VelocityUtils.getRenderedTemplate(TMPL_EDITOR, contextMap);

    }

    @Override
    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}