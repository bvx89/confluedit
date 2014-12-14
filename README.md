<h1>Confluedit</h1>

<p>
This is a plugin for Atlassian Confluence, created as part of a research in integrating online web editors. The focus
of the research is towards a novice course in object-oriented programming at NTNU (Norwegian University of Science and
Technology), where the students can use a web editor to solve example problems or assignments.
</p>

<hr/>

<h2>Setup</h2>
<p>
In order to run this project, the Atlassian Plugin SDK must be configured. Go through the
<a href="https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project">
Atlassian Plugin SDK tutorial</a> to configure the environment and select an IDE to use for your development. When
you have configured your IDE to use the Maven provided in the Atlassian Plugin SDK, you are ready to build using one of
atlas* commands.
</p>

<hr/>

<h2>Alter code modules</h2>
<h5>Macro</h5>
<p>
In order to alter the macro, look at the
<a href="src/main/java/no/ntnu/assignmentsystem/confluedit/EditorMacro.java">EditorMacro.java</a>-file.
It uses the template manager and <a href="src/main/resources/templates/editor-macro.vm">editor-macro.vm</a>
as template for rendering the content on the web page. The rendered content will be altered by the
 <a href="src/main/resources/js/confluedit.js">confluedit.js</a>-file to show content using the Ace editor.
</p>

<h5>REST</h5>
<p>
In order to connect to the backend, the
<a href="src/main/java/no/ntnu/assignmentsystem/confluedit/rest/EditorRestResource.java">EditorRestResource</a>
is used. It handles requests at specific path(s), and responds with a
<a href="src/main/java/no/ntnu/assignmentsystem/confluedit/rest/EditorRestResourceModel.java">EditorRestResourceModel</a>
in either XML- or JSON-format.
</p>

<hr/>

*The following information is taken from Confluence regarding how to run a plugin.*

<ul>
<li>atlas-run   -- installs this plugin into the product and starts it on localhost</li>
<li>atlas-debug -- same as atlas-run, but allows a debugger to attach at port 5005</li>
<li>atlas-cli   -- after atlas-run or atlas-debug, opens a Maven command line window:
    <ul>
        <li> 'pi' re-installs the plugin into the running product instance</li>
    </ul>
<li>atlas-help  -- prints description for all commands in the SDK</li>
</ul>

Full documentation is always available at:

https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK
