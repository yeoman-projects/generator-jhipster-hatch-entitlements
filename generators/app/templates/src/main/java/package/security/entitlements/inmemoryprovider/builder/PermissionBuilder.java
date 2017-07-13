package <%=packageName%>.inmemoryprovider.builder;

import com.mycompany.myapp.security.hatch.HatchEntitlement;
import com.mycompany.myapp.security.hatch.HatchPermission;

import java.util.*;
import java.util.stream.Stream;

public class PermissionBuilder {
    private String roleName;
    private EntitlementsBuilder entitlementsBuilder;
    private Map<Class, List<HatchPermission>> permissions = new HashMap<>();

    PermissionBuilder(String roleName, EntitlementsBuilder entitlementsBuilder) {
        this.roleName = roleName;
        this.entitlementsBuilder = entitlementsBuilder;
    }

    public <T> PermissionBuilder allowAll(Class<T> userClass) {
        Stream.of(HatchPermission.values()).forEach(permission-> allow(userClass,permission));
        return this;
    }

    public <T> PermissionBuilder allow(Class<T> userClass, HatchPermission permission) {
        List<HatchPermission> entityPermissions = this.permissions.computeIfAbsent(userClass, aClass -> new ArrayList<>());
        entityPermissions.add(permission);
        return this;
    }

    public EntitlementsBuilder and() {
        return entitlementsBuilder;
    }


    List<HatchEntitlement> build() {
        ArrayList<HatchEntitlement> entitlements = new ArrayList<>();
        for (Map.Entry<Class, List<HatchPermission>> classListEntry : permissions.entrySet()) {
            for (HatchPermission hatchEntitlement : classListEntry.getValue()) {
                entitlements.add(new HatchEntitlement(roleName, classListEntry.getKey().getSimpleName(), hatchEntitlement));
            }
        }
        return entitlements;
    }

    String getRoleName() {
        return roleName;
    }
}
