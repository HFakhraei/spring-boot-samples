package com.hfakhraei.samples.springboot.milton.configurations;

import io.milton.http.fs.NullSecurityManager;
import io.milton.servlet.DefaultMiltonConfigurator;

/**
 * This class configure milton and disable milton authentication.
 *
 * @author Hossein Fakhraei (HFakhraei@outlook.com)
 * @version 1 14 October 2018
 */
public class MiltonConfig extends DefaultMiltonConfigurator {
    private NullSecurityManager securityManager;

    public MiltonConfig() {
        this.securityManager = new NullSecurityManager();
    }

    @Override
    protected void build() {
        builder.setSecurityManager(securityManager);
        super.build();
    }
}