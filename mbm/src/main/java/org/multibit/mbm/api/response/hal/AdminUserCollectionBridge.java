package org.multibit.mbm.api.response.hal;

import com.google.common.base.Optional;
import com.theoryinpractise.halbuilder.ResourceFactory;
import com.theoryinpractise.halbuilder.spi.Resource;
import org.multibit.mbm.db.dto.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * <p>Bridge to provide the following to {@link org.multibit.mbm.db.dto.User}:</p>
 * <ul>
 * <li>Creates representations of multiple Users for an administrator</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class AdminUserCollectionBridge extends BaseBridge<List<User>> {

  /**
   * @param uriInfo The {@link javax.ws.rs.core.UriInfo} containing the originating request information
   * @param principal    An optional {@link org.multibit.mbm.db.dto.User} to provide a security principal
   */
  public AdminUserCollectionBridge(UriInfo uriInfo, Optional<User> principal) {
    super(uriInfo, principal);
  }

  public Resource toResource(List<User> users) {
    ResourceFactory resourceFactory = getResourceFactory();

    Resource userList = resourceFactory.newResource("/user");

    // TODO Fill this in for all fields
    for (User user: users) {
      String rel = "/user/"+user.getId();
      Resource userResource = resourceFactory
        .newResource(rel)
        .withProperty("id", user.getId())
        .withProperty("name", user.getPublicName())
        // End of build
        ;
      userList.withSubresource(rel, userResource);
    }

    return userList;

  }

}
