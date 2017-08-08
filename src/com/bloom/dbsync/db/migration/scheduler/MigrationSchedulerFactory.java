package com.bloom.db.migration.scheduler;

import com.bloom.db.config.MSSQLConfig;
import com.bloom.db.config.OracleConfig;
import com.bloom.db.config.PGConfig;
import com.bloom.db.config.SnappyConfig;
import com.bloom.db.migration.Migration;
import com.bloom.db.migration.scheduler.impl.MSSQLSnappyMigrationScheduler;
import com.bloom.db.migration.scheduler.impl.Oracle2SnappyMigrationScheduler;
import com.bloom.db.migration.scheduler.impl.PGSql2SnappyMigrationScheduler;

/**
 * 迁移任务调度器工厂类。
 * @author houyunfei
 *
 */
public class MigrationSchedulerFactory {

	public static MigrationScheduler createMigrationScheduler(Migration migration) {
		AbstractMigrationScheduler migrationScheduler = null;
		if(migration.getSourceConfig() instanceof PGConfig && migration.getDestConfig() instanceof SnappyConfig) {
			migrationScheduler = new PGSql2SnappyMigrationScheduler();
		} else if(migration.getSourceConfig() instanceof MSSQLConfig && migration.getDestConfig() instanceof SnappyConfig) {
		    migrationScheduler = new MSSQLSnappyMigrationScheduler();
		} else if(migration.getSourceConfig() instanceof OracleConfig && migration.getDestConfig() instanceof SnappyConfig) {
		    migrationScheduler = new Oracle2SnappyMigrationScheduler();
		} 
		else {
			throw new RuntimeException("未定义类型" + migration.getSourceConfig().getClass() + "到类型" + migration.getDestConfig().getClass() + "的调度器！");
		}
		migrationScheduler.setMigration(migration);
		return migrationScheduler;
	}
}
