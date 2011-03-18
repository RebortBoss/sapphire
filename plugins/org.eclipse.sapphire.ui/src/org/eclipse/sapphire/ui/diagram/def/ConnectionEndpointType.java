/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Shenxue Zhou - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.diagram.def;

import org.eclipse.sapphire.modeling.annotations.EnumSerialization;
import org.eclipse.sapphire.modeling.annotations.Label;

/**
 * @author <a href="mailto:shenxue.zhou@oracle.com">Shenxue Zhou</a>
 */

public enum ConnectionEndpointType 
{
    @Label( standard = "none" )
    @EnumSerialization( primary = "none" )
    
	NONE,
	
    @Label( standard = "arrow" )
    @EnumSerialization( primary = "arrow" )
    
	ARROW,
	
    @Label( standard = "circle" )
    @EnumSerialization( primary = "circle" )
    
	CIRCLE,
	
    @Label( standard = "ellipse" )
    @EnumSerialization( primary = "ellipse" )
    
	ELLIPSE
    
}
