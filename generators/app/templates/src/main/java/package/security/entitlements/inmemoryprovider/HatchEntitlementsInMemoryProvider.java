package <%=packageName%>.inmemoryprovider;

import com.mycompany.myapp.security.hatch.HatchEntitlement;
import com.mycompany.myapp.security.hatch.HatchEntitlementProvider;
import com.mycompany.myapp.security.hatch.HatchPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class HatchEntitlementsInMemoryProvider implements HatchEntitlementProvider {

    private final List<HatchEntitlement> hatchEntitlements;

    @Autowired
    public HatchEntitlementsInMemoryProvider(HatchEntitlementsInMemoryStore entitlementsInMemoryStore) {
        hatchEntitlements = entitlementsInMemoryStore.getEntitlements();
    }

    HatchEntitlementsInMemoryProvider(List<HatchEntitlement> hatchEntitlements) {
        this.hatchEntitlements = hatchEntitlements;
    }

    @Override
    public List<String> getAllowedRoles(String accessedEntityClass, HatchPermission entityPermission) {
        return hatchEntitlements.stream()
            .filter(hatchEntitlement -> hatchEntitlement.getProtectedResource().equals(accessedEntityClass))
            .filter(hatchEntitlement -> hatchEntitlement.getEntityPermission().equals(entityPermission))
            .map(HatchEntitlement::getRoleName)
            .collect(toList());
    }

    @Override
    public List<HatchEntitlement> getForRole(String role) {
            return hatchEntitlements.stream()
                .filter(hatchEntitlement -> hatchEntitlement.getRoleName().equals(role))
                .collect(toList());
    }
}
