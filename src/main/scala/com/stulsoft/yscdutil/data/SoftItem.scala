/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */

package com.stulsoft.yscdutil.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.stulsoft.yscdutil.data.SoftItemType._

/**
 * Holds information about soft item.
 *
 * @author Yuriy Stul
 *
 */
class SoftItem(var n: String, var t: SoftItemType) {
	private def this() {
		this(null, null)
	}

	var i: String = null

	def getN: String = n
	def setN(thatN: String): Unit = {
		n = thatN
	}
	
	def getI: String = i
	
	def setI(i: String): Unit = {
		this.i = i
	}
	
	def getT: Int = {
		t.id
	}
	
	def setT(t: Int): Unit={
		this.t = SoftItemType(t)
	}

	/**
	 * Returns a string representation of the object.
	 * @return the string representation of the object.
	 */
	override def toString = s"SoftItem: n=$n, i=$i, t=$t"

	/**
	 * Compares the receiver object (this) with the argument object (that) for equivalence.
	 *
	 * @return true, if objects have same data.
	 */
	override def equals(other: Any) = other match {
		case that: SoftItem => this.n == that.n && this.i == that.i && this.t == that.t
		case _ => false
	}
	
	/**
	 * Calculate a hash code value for the object.
	 * 
	 * @return the hash code value for the object.
	 */
	override def hashCode = {
		var h = 0
		if (n != null) h +=	7 * (7 + n.hashCode())
		if (i != null) h += 11 * (11 + i.hashCode())
		if (t != null) h += 13 * (13 + t.hashCode())
		h
	}
}