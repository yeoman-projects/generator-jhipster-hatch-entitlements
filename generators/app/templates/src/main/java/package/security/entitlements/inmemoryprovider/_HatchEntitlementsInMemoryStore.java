package <%=packageName%>.entitlements.inmemoryprovider;


import com.mycompany.myapp.domain.PersistentAuditEvent;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.security.AuthoritiesConstants;
import <%=packageName%>.entitlements.HatchEntitlement;
import <%=packageName%>.entitlements.HatchPermission;
import <%=packageName%>.entitlements.inmemoryprovider.builder.EntitlementsBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mycompany.myapp.security.AuthoritiesConstants.ADMIN;

@Service
class HatchEntitlementsInMemoryStore {

    private List<HatchEntitlement> entitlements = new EntitlementsBuilder()
        //define your entitlements here
        .build();

    List<HatchEntitlement> getEntitlements() {
        return entitlements;
    }
}
