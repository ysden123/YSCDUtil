/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */

package com.stulsoft.yscdutil.test.data

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import com.fasterxml.jackson.databind.ObjectMapper
import com.stulsoft.yscdutil.data.SoftItem
import com.stulsoft.yscdutil.data.SoftItemType._

/**
 * @author Yuriy Stul
 *
 */
class SoftItemTest extends FlatSpec with Matchers {
	def createSoftItem: SoftItem = {
		val si = new SoftItem("the name", CATEGORY)
		si.i = "123"
		si
	}

	"A SoftItem" should "be serializable with JSon" in {
		val mapper: ObjectMapper = new ObjectMapper()

		val source = createSoftItem
		val json = mapper.writeValueAsString(source)
		//		println(json)

		val restored = mapper.readValue(json, classOf[SoftItem])

		restored shouldEqual source
	}
	it should "implement ==" in {
		val s1 = createSoftItem
		val s2 = createSoftItem
		s1 shouldEqual s2
	}
	it should "implement hashCode" in {
		val s1 = createSoftItem
		val s2 = createSoftItem
		s1.hashCode shouldEqual s2.hashCode
	}
	it should "implement toString with i = null" in {
		val s = new SoftItem("the name", DISK)
		"SoftItem: n=the name, i=null, t=DISK" shouldEqual s.toString
	}
	it should "implement toString with i = \"123\"" in {
		val s = new SoftItem("the name", DISK)
		s.i = "123"
		"SoftItem: n=the name, i=123, t=DISK" shouldEqual s.toString
	}
}
