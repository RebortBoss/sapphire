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

package org.eclipse.sapphire.ui.def.internal;

import org.eclipse.sapphire.modeling.annotations.DefaultValueProviderImpl;
import org.eclipse.sapphire.ui.def.IMasterDetailsTreeNodeDef;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class MasterDetailsPageSectionDefLabelDefaultValueProvider

    extends DefaultValueProviderImpl
    
{
    @Override
    public String getDefaultValue()
    {
        final IMasterDetailsTreeNodeDef node = (IMasterDetailsTreeNodeDef) getModelElement().getParent().getParent();
        return node.getLabel().getText();
    }
    
}
