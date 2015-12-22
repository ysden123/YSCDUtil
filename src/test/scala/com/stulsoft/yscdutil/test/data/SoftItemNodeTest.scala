/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */

package com.stulsoft.yscdutil.test.data

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import com.stulsoft.yscdutil.data.SoftItemNode
import com.stulsoft.yscdutil.data.SoftItem
import com.stulsoft.yscdutil.data.SoftItemType._
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * @author Yuriy Stul
 *
 */
class SoftItemNodeTest extends FlatSpec with Matchers {
	def createSoftItemNode: SoftItemNode = {
		val d: SoftItem = new SoftItem("test item", DISK)
		d.setI("123")
		val sin: SoftItemNode = new SoftItemNode(d, null)
		sin
	}
	
	"A SoftItemNode" should "be serializabl with JSon" in {
		val mapper: ObjectMapper = new ObjectMapper()
		val source = createSoftItemNode
		val json = mapper.writeValueAsString(source)
				println(json)

		val restored = mapper.readValue(json, classOf[SoftItemNode])

		restored shouldEqual source
	}
}