package <%=packageName%>.security.entitlements.inmemoryprovider.builder;

import <%=packageName%>.security.entitlements.HatchEntitlement;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EntitlementsBuilder {

    private List<PermissionBuilder> permissionBuilders = new ArrayList<>();

    public PermissionBuilder forRole(String roleName) {
        if (permissionBuilders.stream().anyMatch(builder -> builder.getRoleName().equals(roleName))) {
            throw new IllegalArgumentException("Role with name " + roleName + " is already defined.");
        }
        PermissionBuilder builder = new PermissionBuilder(roleName, this);
        permissionBuilders.add(builder);
        return builder;
    }

    public List<HatchEntitlement> build() {
        return permissionBuilders.stream()
            .map(PermissionBuilder::build)
            .flatMap(List::stream)
            .collect(toList());
    }
}
