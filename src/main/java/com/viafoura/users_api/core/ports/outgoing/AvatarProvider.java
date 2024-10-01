package com.viafoura.users_api.core.ports.outgoing;

import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import org.springframework.cache.annotation.Cacheable;

public interface AvatarProvider {

    @Cacheable("avatars")
    String getAvatar() throws AvatarServiceException;
}
