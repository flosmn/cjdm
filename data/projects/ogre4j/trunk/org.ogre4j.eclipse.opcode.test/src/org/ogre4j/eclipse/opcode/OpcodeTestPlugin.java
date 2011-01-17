package org.ogre4j.eclipse.opcode;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class OpcodeTestPlugin extends Plugin {

    static OpcodeTestPlugin instance;

    public OpcodeTestPlugin() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        instance = null;
        super.stop(context);
    }
    public static OpcodeTestPlugin getInstance() {
        return instance;
    }

}
