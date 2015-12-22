/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */

package com.stulsoft.yscdutil.data

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * Holds a tree node data about SoftItem.
 *
 * @author Yuriy Stul
 *
 */
class SoftItemNode {
	var d: SoftItem = null
	var p: SoftItemNode = null
	var c: List[SoftItemNode] = List[SoftItemNode]()

	def this(data: SoftItem, parent: SoftItemNode) {
		this()
		setD(data)
		setP(parent)
	}

	def this(data: SoftItem, parent: SoftItemNode, children: List[SoftItemNode]) {
		this(data, parent)
		setC(children)
	}

	def getD: SoftItem = {
		d
	}
	def setD(data: SoftItem): Unit = {
		require(data != null)
		this.d = data
	}

	@JsonIgnore
	def getP: SoftItemNode = {
		p
	}
	def setP(parent: SoftItemNode): Unit = {
		this.p = parent
	}

	def getC: List[SoftItemNode] = {
		c
	}
	def setC(children: List[SoftItemNode]): Unit = {
		require(children != null)
		this.c = children
	}

	override def hashCode: Int = {
		var result: Int = 31
		if (d != null) result += d.hashCode()
		result
	}

	override def equals(other: Any) = other match {
		case that: SoftItemNode => this.d == that.d
		case _ => false
	}
}
