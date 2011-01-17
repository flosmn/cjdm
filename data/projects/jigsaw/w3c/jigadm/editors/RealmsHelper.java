// RealmsHelper.java
// $Id: RealmsHelper.java,v 1.8 2000/08/16 21:37:27 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadm.editors ;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextComponent;
import java.awt.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Properties;

import org.w3c.jigadm.RemoteResourceWrapper;

import org.w3c.jigsaw.admin.RemoteAccessException;
import org.w3c.jigsaw.admin.RemoteResource;

import org.w3c.tools.resources.Attribute;

public class RealmsHelper extends ResourceHelper {

    class AddRealmListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
	    addRealm();
	}
    }

    RemoteResourceWrapper   rrw = null;
    RemoteResource rr = null;
    private boolean initialized = false;
    TextField tf;
    Panel widget;

    protected void addRealm() {
	if(tf.getText().length() > 0) {
	    RemoteResource nrr;
	    try {
		nrr = rrw.getResource().
		    registerResource(tf.getText(),
				     "org.w3c.jigsaw.auth.AuthRealm");
	    } catch (RemoteAccessException ex) {
	        errorPopup("RemoteAccessException",ex);
		return;
	    }
	    RemoteResourceWrapper nrrw;
	    nrrw = new RemoteResourceWrapper(rrw, nrr, rrw.getBrowser());
	    rrw.getBrowser().insertNode(rrw, nrrw, tf.getText());
	}
    }

    protected RemoteResourceWrapper getWrapper() {
	return rrw;
    }

    public Component getComponent() {
	return widget;
    }

    public void commitChanges() {
    }

    public boolean hasChanged() {    
	return false;
    }

    public void resetChanges() {
    }

    public void clearChanged() {
    }

    public final String getTitle () {
	return "Realms";
    }

    public RealmsHelper() {
	widget = new Panel();
    }

    protected void initAddPanel() {
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	AddRealmListener arl = new AddRealmListener();
	Panel p = new Panel(gbl);
	Label l;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.weightx = 0;
	gbc.weighty = 0;	
	tf = new TextField(15);
	tf.addActionListener(arl);
	l = new Label("Realm name", Label.RIGHT);
	gbc.gridwidth = 1;
	gbl.setConstraints(l, gbc);
	p.add(l);
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbl.setConstraints(tf, gbc);
	p.add(tf);
	widget.add("Center", p);
	Button newb     = new Button("Add Realm");
	newb.addActionListener(arl);
	widget.add("South", newb);
    }

    /**
     * initialize this helper
     * @param rrw The RemoteResourceWrapper
     * @param pr The properties
     * @exception RemoteAccessException if a remote access error occurs.
     */
    public void initialize(RemoteResourceWrapper rrw, Properties pr)
	throws RemoteAccessException
    {
	if(!initialized)
	    initialized = true;
	else
	    return;	
	
	this.rrw = rrw;
	rr = rrw.getResource();

	if(rr.isContainer()) {
	    widget.setLayout(new BorderLayout());
	    initAddPanel();
	}
    }
}
