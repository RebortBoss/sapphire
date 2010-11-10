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

package org.eclipse.sapphire.ui.assist.internal;

import java.util.Collection;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sapphire.modeling.CapitalizationType;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.PossibleValuesProviderImpl;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.sapphire.ui.assist.BrowseHandler;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class PossibleValuesProviderBrowseHandler 

    extends BrowseHandler
    
{
    @Override
    public String browse( final SapphireRenderingContext context )
    {
        final ValueProperty property = getProperty();
        final PossibleValuesProviderImpl valuesProvider = getModelElement().service().getPossibleValuesProvider( property );

        if( valuesProvider != null )
        {
            final Collection<String> valuesList = valuesProvider.getPossibleValues();
            final String[] valuesArray = valuesList.toArray( new String[ valuesList.size() ] );
            
            final ElementListSelectionDialog dialog 
                = new ElementListSelectionDialog( context.getShell(), new LabelProvider() );
            
            dialog.setElements( valuesArray );
            dialog.setMultipleSelection( false );
            dialog.setHelpAvailable( false );
            dialog.setTitle( property.getLabel( false, CapitalizationType.TITLE_STYLE, false ) );
            dialog.setMessage( createBrowseDialogMessage( property.getLabel( true, CapitalizationType.NO_CAPS, false ) ) );
            
            dialog.open();
            
            final Object[] result = dialog.getResult();
            
            if( result != null && result.length == 1 )
            {
                return (String) result[ 0 ];
            }
        }
        
        return null;
    }
}