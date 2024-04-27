package com.key.service;

import com.key.entity.User;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {

    @Autowired
    private Keycloak keycloak;

    public void createUser(User userEntity) {
        RealmResource realmResource = keycloak.realm("ekkoai");
        UsersResource usersResource = realmResource.users();

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setEnabled(true);
//        usersResource.create(user);

        try (Response response = usersResource.create(user)) {
            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed to create user");
            }
        }
    }
}

