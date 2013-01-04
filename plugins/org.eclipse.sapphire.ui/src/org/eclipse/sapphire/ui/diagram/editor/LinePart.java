/******************************************************************************
 * Copyright (c) 2013 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Shenxue Zhou - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.diagram.editor;

import org.eclipse.sapphire.ui.Color;
import org.eclipse.sapphire.ui.LineStyle;
import org.eclipse.sapphire.ui.def.Orientation;
import org.eclipse.sapphire.ui.diagram.shape.def.LineShapeDef;

/**
 * @author <a href="mailto:shenxue.zhou@oracle.com">Shenxue Zhou</a>
 */

public class LinePart extends ShapePart
{
	private LineShapeDef lineDef;
	
	@Override
    protected void init()
    {
        super.init();
        this.lineDef = (LineShapeDef)super.definition;
    }
	
	public Orientation getOrientation()
	{
		return this.lineDef.getOrientation().getContent();
	}
	
	public int getWeight()
	{
		return this.lineDef.getPresentation().getWeight().getContent();
	}
	
	public Color getColor()
	{
		return this.lineDef.getPresentation().getColor().getContent();
	}
	
	public LineStyle getStyle()
	{
		return this.lineDef.getPresentation().getStyle().getContent();
	}
}
