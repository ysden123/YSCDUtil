/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.yscdcatalogue.persistence;

import java.io.File;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stulsoft.yscdcatalogue.data.DiskItemTree;
import com.stulsoft.yscdcatalogue.data.SoftItemTree;

/**
 * @author Yuriy Stul
 *
 */
public class DBManagerDst {
	private static Logger logger = LogManager.getLogger(DBManagerDst.class);

	private final DB db;
	
	private ConcurrentNavigableMap<String, String> diskItemTreeMap;
	
	/**
	 * @param directory folder with destination DB.
	 */
	public DBManagerDst(final String directory) {
		db = DBMaker.newFileDB(new File(directory, "YSCDCatalogueDB.db")).compressionEnable().closeOnJvmShutdown().make();
		diskItemTreeMap = db.getTreeMap("diskItemTree");
	}
	
	/**
	 * Stores a Soft Item Tree into database
	 * 
	 * @param tree
	 *            the Soft Item Tree
	 * @throws Exception
	 *             I/O exception
	 */
	public void saveSoftItemTree(final SoftItemTree tree) throws Exception {
		if (tree == null)
			throw new IllegalArgumentException("tree is null.");
		NavigableSet<String> softItemTreeSet = db.getTreeSet("softItemTree");
		softItemTreeSet.clear();
		
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(tree);
		}
		catch (Exception e) {
			String message = String.format("Error during creating Json for SoftItemTree. Error: %s.", e.getMessage());
			logger.error(message, e);
			throw new Exception(message, e);
		}

		softItemTreeSet.add(json);
		db.commit();
	}

	/**
	 * Stores a Disk Item Tree into database
	 * 
	 * @param tree
	 *            the Disk Item Tree
	 * @throws Exception
	 *             I/O exception
	 */
	public void saveDiskItemTree(final DiskItemTree tree) throws Exception {
		if (tree == null)
			throw new IllegalArgumentException("tree is null.");
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(tree);
		}
		catch (Exception e) {
			String message = String.format("Error during creating Json for DiskItemTree. Error: %s.", e.getMessage());
			logger.error(message, e);
			throw new Exception(message, e);
		}
		diskItemTreeMap.put(tree.getId(), json);
		db.commit();
	}

}
