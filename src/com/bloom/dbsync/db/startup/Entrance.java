package com.bloom.db.startup;

import com.bloom.db.config.MSSQLConfig;
import com.bloom.db.config.PGConfig;
import com.bloom.db.config.SnappyConfig;
import com.bloom.db.migration.Migration;
import com.bloom.db.migration.MigrationManager;

public class Entrance {

	public static void main(String[] args) {
		MigrationManager manager = new MigrationManager();
		Migration m = new Migration();
		m.setSourceConfig(getPGConfig());
		m.setDestConfig(getSnappyConfig());
		manager.addMigration(m);
		manager.shutdown();
	}
	
	public static PGConfig getPGConfig() {
		PGConfig pgConfig = new PGConfig();
		pgConfig.setHost("192.168.15.122");
		pgConfig.setPort(5432);
		pgConfig.setDatabaseName("postgres");
		pgConfig.setUser("postgres");
		pgConfig.setPassword("postgres");
		return pgConfig;
	}
	
	public static SnappyConfig getSnappyConfig() {
		SnappyConfig snappyConfig = new SnappyConfig();
		snappyConfig.setHost("192.168.15.121");
		snappyConfig.setPort(1527);
		return snappyConfig;
	}
	
	
	public static MSSQLConfig getMSSQLConfig() {
		MSSQLConfig mssqlConfig = new MSSQLConfig();
		mssqlConfig.setHost("192.168.16.103");
		mssqlConfig.setPort(1433);
		mssqlConfig.setDatabaseName("DBSyncer");
		mssqlConfig.setUser("sa");
		mssqlConfig.setPassword("123456");
		return mssqlConfig;
	}
}
