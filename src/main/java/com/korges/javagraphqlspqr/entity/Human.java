package com.korges.javagraphqlspqr.entity;

import io.leangen.graphql.annotations.types.GraphQLInterface;

@GraphQLInterface(name = "User", implementationAutoDiscovery = true)
public interface Human {

    String getFirstName();
    String getLastName();
}
