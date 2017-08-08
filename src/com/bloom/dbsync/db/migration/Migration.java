package com.bloom.db.migration;

import com.bloom.db.config.DBConfig;
import com.bloom.db.migration.scheduler.MigrationScheduler;
import com.bloom.db.migration.scheduler.MigrationSchedulerFactory;

/**
 * 一次迁移任务。
 * （1）包含数据源配置
 * （2）包含目的地配置
 * @author houyunfei
 *
 */
public class Migration implements Runnable {

	DBConfig sourceConfig;
	
	DBConfig destConfig;

	public DBConfig getSourceConfig() {
		return sourceConfig;
	}

	public void setSourceConfig(DBConfig sourceConfig) {
		this.sourceConfig = sourceConfig;
	}

	public DBConfig getDestConfig() {
		return destConfig;
	}

	public void setDestConfig(DBConfig destConfig) {
		this.destConfig = destConfig;
	}

	@Override
	public void run() {
		MigrationScheduler scheduler = MigrationSchedulerFactory.createMigrationScheduler(this);
		try {
			scheduler.doSchedule();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
