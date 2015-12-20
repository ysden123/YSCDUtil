/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */

package com.stulsoft.yscdutil.data

import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Holds information about soft item.
 *
 * @author Yuriy Stul
 *
 */
class SoftItem(var n: String, var t: String) {
	def this() {
		this(null, null)
	}

	def getN: String = n
	def setN(thatN: String): Unit = {
		n = thatN
	}
	def getT: String = t
	def setT(thatT: String): Unit = {
		t = thatT
	}

	/**
	 * Returns a string representation of the object.
	 * @return the string representation of the object.
	 */
	override def toString = s"SoftItem: n=$n, t=$t"

	/**
	 * Compares the receiver object (this) with the argument object (that) for equivalence.
	 *
	 * @return true, if objects have same data.
	 */
	override def equals(other: Any) = other match {
		case that: SoftItem => this.n == that.n && this.t == that.t
		case _ => false
	}
	
	/**
	 * Calculate a hash code value for the object.
	 * 
	 * @return the hash code value for the object.
	 */
	override def hashCode = {
		var h = 0
		if (n != null) h +=	31 * (31 + n.hashCode())
		if (t != null) h += 37 * (37 + t.hashCode())
		h
	}
}

/**
 * For testing SoftItem.
 *
 * @author Yuriy Stul
 *
 */
object Main4SoftItemTest {
	/**
	 * For testing SoftItem.
	 *
	 * @param args
	 */
	def main(args: Array[String]): Unit = {
		val mapper: ObjectMapper = new ObjectMapper()
		val si: SoftItem = new SoftItem("name1", "type 111")
		val json: String = mapper.writeValueAsString(si)
		println(s"json: $json")
		val siRestored = mapper.readValue(json, classOf[SoftItem])
		println(s"siRestored.n= ${siRestored.n}, siRestored.t=${siRestored.t}")
		println(siRestored)
		println(s"si == siRestored: ${si == siRestored}")
		println(s"si.equals(siRestored): ${si.equals(siRestored)}")
		println(s"si.hashCode == siRestored.hashCode: ${si.hashCode == siRestored.hashCode}")
	}
}