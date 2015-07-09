package no.hal.eclipsky.confluedit.rest;

import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * A resource of message.
 */
@Path("/connect")
public class EditorRestResource {
    private static final Logger log = LogManager.getLogger();
    private final static String PARAM_NAME = "projectName";
    private final static String PARAM_FORWARD = "forward";
    private final static String PARAM_XEMFS = "xemfs";
    private final static String PARAM_START_INDEX = "startIndex";

    private final static String FORWARD_PATH = "sourceEditor";

    @Context
    private HttpHeaders headers;

    public EditorRestResource() {
    }

    protected String constructProjectName(String userId, String taskId, String xtext) {
        return userId + "-" + taskId;
    }

    @POST
    @Path("/address")
    @AnonymousAllowed
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getMessage(EditorRestResourceModel model) {
        HeaderParser hp = new HeaderParser(headers);

        // Find an appropriate user id
        ConfluenceUser confluenceUser = AuthenticatedUserThreadLocal.get();
        String userId;
        if (confluenceUser == null) {
            log.warn("Confluence User is null, must use session id");
            userId = hp.getSessionId();
        } else {
            userId = confluenceUser.getKey().getStringValue();
        }

        // Check for existing startIndex
        String startIndex = hp.getStartIndexCookie();

        //TODO: Connect to broker to get appropriate IP/URL
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()){
            final URI eclipseURI = new URI("http://localhost:8080/ensureProject");
            HttpPost httpPost = new HttpPost(eclipseURI);
            // Add params to the getter
            List<NameValuePair> params = new ArrayList<>(3);
            params.add(new BasicNameValuePair(PARAM_NAME, constructProjectName(userId, model.getTaskId(), model.getDifficulty())));
            params.add(new BasicNameValuePair(PARAM_FORWARD, FORWARD_PATH));
            params.add(new BasicNameValuePair(PARAM_XEMFS, model.getXEMFS()));
            params.add(new BasicNameValuePair(PARAM_START_INDEX, (startIndex == null ? "0" : startIndex)));


            httpPost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse response = httpclient.execute(httpPost);


            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 302 && statusLine.getStatusCode() != 200) {
                return Response.ok(statusLine.getStatusCode()).build();
            }

            Header locationHeader = response.getFirstHeader("location");
            if (locationHeader != null) {
                String redirectLocation = locationHeader.getValue();
                return Response.ok(redirectLocation).build();
            } else {
                String error = "\"location\" in HTTP response is missing";
                log.error(error, new IllegalArgumentException(error));
                return Response.ok(error).build();
            }
        } catch (UnsupportedEncodingException e){
            log.error("Error in params", e);
            return Response.ok(e.toString()).build();
        } catch (IOException e) {
            log.error("Error in Input/output", e);
            return Response.ok(e.toString()).build();
        } catch (URISyntaxException e) {
            log.error("Error in URL syntax", e);
            return Response.ok(e.toString()).build();
        }

    }

    private void writeToFile(String text) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("testFile.txt", "UTF-8");
        writer.println(text);
        writer.close();
    }

    /*
    try (InputStream content = response.getEntity().getContent();
         InputStreamReader isr = new InputStreamReader(content);
         BufferedReader reader = new BufferedReader(isr)){

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }



        return Response.ok(builder.toString()).build();
        // return Response.ok(new EditorRestResourceModel(userId)).build();
    } catch (IOException e) {
        log.error("Error in parsing response", e);
        return Response.serverError().build();
    }*/
}
