package io.jenkins.plugins.endpoints;

import io.jenkins.plugins.datastore.DatastoreService;
import io.jenkins.plugins.models.Label;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/labels")
@Produces(MediaType.APPLICATION_JSON)
public class LabelsEndpoint {

  private Logger logger = LoggerFactory.getLogger(LabelsEndpoint.class);

  @Inject
  private DatastoreService datastoreService;

  @GET
  public String getLabels() {
    try {
      final List<Label> labels = datastoreService.getLabels();
      final JSONObject result = new JSONObject();
      result.put("labels", labels);
      return result.toString(2);
    } catch (Exception e) {
      logger.error("Problem getting labels", e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  }

}