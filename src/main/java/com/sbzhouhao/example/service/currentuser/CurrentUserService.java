package com.sbzhouhao.example.service.currentuser;

import com.sbzhouhao.example.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
