package <%=packageName%>.config;

import <%=packageName%>.security.entitlements.HatchEntitlementProvider;
import <%=packageName%>.security.entitlements.custommethodsecurityexpression.HatchEntitlementMethodSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;

@Configuration
public class HatchAuthorizationConfiguration {

    @Autowired
    private HatchEntitlementProvider hatchEntitlementProvider;

    @Bean
    public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler(){
        return new HatchEntitlementMethodSecurityExpressionHandler(hatchEntitlementProvider);
    }
}
