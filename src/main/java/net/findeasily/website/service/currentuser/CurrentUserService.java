package net.findeasily.website.service.currentuser;

import net.findeasily.website.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, String userId);

}
