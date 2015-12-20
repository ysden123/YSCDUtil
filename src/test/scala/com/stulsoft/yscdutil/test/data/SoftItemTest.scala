/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
 
package com.stulsoft.yscdutil.test.data

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import com.fasterxml.jackson.databind.ObjectMapper
import com.stulsoft.yscdutil.data.SoftItem

/**
 * @author Yuriy Stul
 *
 */
class SoftItemTest extends FlatSpec with Matchers  {
	"A SoftItem" should "be serializable with JSon" in {
		val mapper: ObjectMapper = new ObjectMapper()

		val source = new SoftItem("the name", "the type")
		val json = mapper.writeValueAsString(source)
		
		val restored = mapper.readValue(json, classOf[SoftItem])

		restored shouldEqual source 
	}
	it should "implement ==" in {
		val s1 = new SoftItem("the name", "the type")
		val s2 = new SoftItem("the name", "the type")
		s1 shouldEqual s2
	}
	it should "implement hashCode" in {
		val s1 = new SoftItem("the name", "the type")
		val s2 = new SoftItem("the name", "the type")
		s1.hashCode shouldEqual s2.hashCode
	}
}

/*****************************************
object SoftItemTest{
	
	def createSoftItem {
		new SoftItem("the name", "the type")
	}
}
*******************************************/