/******************************************************************************
 * Copyright (c) 2010 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.editor.views.masterdetails.actions;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sapphire.ui.SapphireImageCache;
import org.eclipse.sapphire.ui.actions.Action;
import org.eclipse.sapphire.ui.editor.views.masterdetails.MasterDetailsPage;
import org.eclipse.swt.widgets.Shell;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class ExpandAllAction

    extends Action
    
{
    public static final String ACTION_ID = "page:expand-all"; //$NON-NLS-1$

    public ExpandAllAction()
    {
        setId( ACTION_ID );
        setLabel( Resources.expandAllActionLabel );
        setImageDescriptor( SapphireImageCache.ACTION_EXPAND_ALL );
    }
    
    @Override
    protected final Object run( final Shell shell )
    {
        final MasterDetailsPage page = getPart().getNearestPart( MasterDetailsPage.class );        
        page.expandAllNodes();
        
        return null;
    }
    
    private static final class Resources
    
        extends NLS
    
    {
        public static String expandAllActionLabel;
        
        static
        {
            initializeMessages( ExpandAllAction.class.getName(), Resources.class );
        }
    }
    
}
