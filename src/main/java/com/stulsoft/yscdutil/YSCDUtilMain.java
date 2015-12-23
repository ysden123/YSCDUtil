/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.yscdutil;

import java.util.concurrent.ConcurrentNavigableMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stulsoft.yscdcatalogue.data.DiskItemTree;
import com.stulsoft.yscdcatalogue.persistence.DBManagerDst;
import com.stulsoft.yscdcatalogue.persistence.DBManagerSrc;

/**
 * @author Yuriy Stul
 *
 */
public class YSCDUtilMain {
	private static Logger logger = LogManager.getLogger(YSCDUtilMain.class);

	/**
	 * @param args
	 *            input parameters
	 */
	public static void main(String[] args) {
		final String srcFolder = "d:\\My Documents D\\My Data\\YS Disks";
		final String dstFolder = "d:\\work\\YS Disks";
		logger.info("Copy DB from {} to {}", srcFolder, dstFolder);
		DBManagerSrc src = new DBManagerSrc(srcFolder);
		DBManagerDst dst = new DBManagerDst(dstFolder);

		try {
			dst.saveSoftItemTree(src.getSoftItemTree());
			final ConcurrentNavigableMap<String, String> diskItemTreeMap = src.getDiskItemTreeMap();
			logger.info("Source DB has {} disk item trees.", diskItemTreeMap.size());
			int count = 0;
			for(String json : diskItemTreeMap.values()){
				ObjectMapper mapper = new ObjectMapper();
				DiskItemTree tree = mapper.readValue(json, DiskItemTree.class);
				dst.saveDiskItemTree(tree);
				++count;
			}
			logger.info("{} disk item trees were copied.", count);
		}
		catch (Exception e) {
			final String message = "Failure: " + e.getMessage();
			logger.error(message);
			System.out.println(message);
		}
		
		logger.info("Completed.");
	}
}
