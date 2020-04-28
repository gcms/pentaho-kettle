/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2017 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.trans.steps.xbaseinput;

import org.junit.ClassRule;
import org.junit.Test;
import org.pentaho.di.junit.rules.RestorePDIEngineEnvironment;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class XBaseInputContentParsingTest extends BaseXBaseParsingTest {
  @ClassRule public static RestorePDIEngineEnvironment env = new RestorePDIEngineEnvironment();
  @Test
  public void testDefaultOptions() throws Exception {
    init( "test.dbf" );

    process();

    check( new Object[][] { { "value11", "value12", 1.0 }, { "value21", "value22", 2.0 } } );
  }

  @Test
  public void testDbc() throws Exception {
    init( "test.dbc" );

    process();

    assertEquals("test.dbc", new File(meta.getDbfFileName()).getName());
    assertEquals("UF_ZI", data.outputRowMeta.getFieldNames()[0]);
    assertEquals("ANO_CMPT", data.outputRowMeta.getFieldNames()[1]);

//    check( new Object[][] { { "value11", "value12", 1.0 }, { "value21", "value22", 2.0 } } );
  }
}
