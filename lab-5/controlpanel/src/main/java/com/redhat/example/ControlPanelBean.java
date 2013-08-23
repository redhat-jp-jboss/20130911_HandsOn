package com.redhat.example;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

@Named
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ControlPanelBean {

	DefaultCacheManager manager;
	Cache<String, Boolean> statusCache;

	String myName = System.getProperty("jboss.server.name");
	String cacheName = "statusCache";

	@PostConstruct
	void init() {
		try {
			// JDGキャッシュをライブラリモードで開始します。
			manager = new DefaultCacheManager("my-infinispan.xml");
			manager.start();
			statusCache = manager.getCache(cacheName);
			System.out.println("### myName="+myName+", status="+true);
			statusCache.put(myName, true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<Map.Entry<String, Boolean>> getAllStatus() {
		System.out.println("### getAllStatus(): "+statusCache.entrySet());
		return new ArrayList<Map.Entry<String, Boolean>>(statusCache.entrySet());
	}

	public void toggleStatus(String name) {
		boolean status = statusCache.get(name);
		statusCache.put(name, !status);
	}

	@PreDestroy
	void destroy() {
        // JDGキャッシュの停止します。
        statusCache.remove(myName);
        statusCache.stop();
        manager.stop();
	}
}
