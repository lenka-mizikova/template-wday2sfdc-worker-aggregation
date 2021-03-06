/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.integration;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.rule.DynamicPort;

/**
 * The objective of this class is to validate the correct behavior of the flows for this Mule Template that
 * make calls to external systems.
 *
 * @author aurel.medvegy
 */
public class BusinessLogicIT extends AbstractTemplateTestCase {

    @Rule
	public DynamicPort port = new DynamicPort("http.port");

	@Test
	public void testMainFlow() throws Exception {
		MuleEvent event = runFlow("mainFlow");
		Assert.assertTrue("The payload should not be null.", "Please find attached your Users Report".equals(event.getMessage().getPayload()));
	}
	
	@Test
	public void testGatherDataFlow() throws Exception {
		MuleEvent event = runFlow("gatherDataFlow");
		List<Map<String, String>> mergedUserList = (List<Map<String, String>>)event.getMessage().getPayload();
		
		Assert.assertTrue("There should be users from source A or source B.", !mergedUserList.isEmpty());
	} 
}
