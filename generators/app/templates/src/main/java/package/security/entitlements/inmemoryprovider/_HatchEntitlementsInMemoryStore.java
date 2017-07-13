package <%=packageName%>.security.entitlements.inmemoryprovider;

import <%=packageName%>.security.entitlements.HatchEntitlement;
import <%=packageName%>.security.entitlements.inmemoryprovider.builder.EntitlementsBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class HatchEntitlementsInMemoryStore {

    private List<HatchEntitlement> entitlements = new EntitlementsBuilder()
        //define your entitlements here
        .build();

    List<HatchEntitlement> getEntitlements() {
        return entitlements;
    }
}
