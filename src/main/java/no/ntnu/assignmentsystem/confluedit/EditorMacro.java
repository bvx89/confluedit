package no.ntnu.assignmentsystem.confluedit;

import java.util.Map;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.GeneralUtil;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.webresource.api.assembler.PageBuilderService;
import org.apache.velocity.VelocityContext;


public class EditorMacro implements Macro {
    private static final String EDITOR_CODE = "editorCode";
    private static final String TEMPLATE = "templates/editor-macro.vm";
    private PageBuilderService pageBuilderService;

    public EditorMacro(PageBuilderService pageBuilderService) {
        this.pageBuilderService = pageBuilderService;
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }

    @Override
    public String execute(Map<String, String> params, String body, ConversionContext conversionContext)
            throws MacroExecutionException {
        // Template context
        VelocityContext contextMap = new VelocityContext(MacroUtils.defaultVelocityContext());

        // TODO: Get real data to insert to the page
        contextMap.put(EDITOR_CODE, body);
        return VelocityUtils.getRenderedTemplate(TEMPLATE, contextMap);

    }
}
