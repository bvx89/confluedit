package no.ntnu.assignmentsystem.confluedit.rest;

import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.UserAccessor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A resource of message.
 */
@Path("/connect")
public class EditorRestResource {
    /*
    private final UserAccessor userAccessor;

    public EditorRestResource(UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }
    */

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("key") String key) {

        if(key!=null) {
            return Response.ok(new EditorRestResourceModel(key, getMessageFromKey(key))).build();
        } else {
            /*
            ConfluenceUser confluenceUser = AuthenticatedUserThreadLocal.get();
            String outputString = "Hello " + confluenceUser.getName() + ", with key: " + confluenceUser.getKey();
            return Response.ok(new EditorRestResourceModel("default", outputString)).build();
            */
            return Response.ok(new EditorRestResourceModel("default", "asda")).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{key}")
    public Response getMessageFromPath(@PathParam("key") String key) {
        return Response.ok(new EditorRestResourceModel(key, getMessageFromKey(key))).build();
    }

    private String getMessageFromKey(String key) {
        // In reality, this data would come from a database or some component
        // within the hosting application, for demonstration purpopses I will
        // just return the key
        return key;
    }
}
