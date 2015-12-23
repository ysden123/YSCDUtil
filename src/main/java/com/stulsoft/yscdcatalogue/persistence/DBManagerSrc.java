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
import com.stulsoft.yscdcatalogue.data.SoftItemTree;

/**
 * @author Yuriy Stul
 *
 */
public class DBManagerSrc {
	private static Logger logger = LogManager.getLogger(DBManagerSrc.class);

	private final DB db;

	/**
	 * @param directory folder with source DB.
	 */
	public DBManagerSrc(final String directory) {
		db = DBMaker.newFileDB(new File(directory, "YSCDCatalogueDB.db")).closeOnJvmShutdown().make();
	}

	/**
	 * Loads and returns a Soft Item Tree
	 * 
	 * @return the Soft Item Tree
	 * @throws Exception
	 *             I/O exception
	 */
	public SoftItemTree getSoftItemTree() throws Exception {
		NavigableSet<String> softItemTreeSet = db.getTreeSet("softItemTree");
		if (!softItemTreeSet.isEmpty()) {
			String json = softItemTreeSet.first();
			ObjectMapper mapper = new ObjectMapper();
			SoftItemTree tree;
			try {
				tree = mapper.readValue(json, SoftItemTree.class);
			}
			catch (Exception e) {
				String message = String.format("Error during reading Json for SoftItemTree. Error: %s.", e.getMessage());
				logger.error(message, e);
				throw new Exception(message, e);
			}
			return tree;
		} else {
			return null;
		}
	}
	
	public ConcurrentNavigableMap<String, String> getDiskItemTreeMap(){
		ConcurrentNavigableMap<String, String> diskItemTreeMap = db.getTreeMap("diskItemTree");
		return diskItemTreeMap;
	}

}
