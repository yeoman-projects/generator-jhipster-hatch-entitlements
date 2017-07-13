package <%=packageName%>.inmemoryprovider;


import com.mycompany.myapp.domain.PersistentAuditEvent;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.hatch.HatchEntitlement;
import com.mycompany.myapp.security.hatch.HatchPermission;
import com.mycompany.myapp.security.hatch.inmemoryprovider.builder.EntitlementsBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mycompany.myapp.security.AuthoritiesConstants.ADMIN;

@Service
class HatchEntitlementsInMemoryStore {

    private List<HatchEntitlement> entitlements = new EntitlementsBuilder()
        .forRole(ADMIN)
        .allow(User.class, HatchPermission.READALL)
        .allow(PersistentAuditEvent.class, HatchPermission.READ)
        .and()
        .forRole(AuthoritiesConstants.USER)
        .allow(User.class, HatchPermission.READ)
        .and()
        .build();

    List<HatchEntitlement> getEntitlements() {
        return entitlements;
    }
}
