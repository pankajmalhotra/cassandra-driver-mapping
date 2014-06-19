/*
 *      Copyright (C) 2014 Pankaj Malhotra (UL).
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.datastax.driver.mapping.schemasync;

import java.nio.ByteBuffer;

import com.datastax.driver.core.RegularStatement;

public class CreateIndex extends RegularStatement {
	
	private static final String CREATE_INDEX_TEMPLATE_CQL = "CREATE INDEX IF NOT EXISTS %s ON %s(%s);";
	
	final String keyspace;
	final String tableName;
	final String columnName;
	final String indexName;

	CreateIndex(String keyspace, String tableName, String columnName, String indexName) {
		this.keyspace = keyspace;
		this.tableName = tableName;
		this.columnName = columnName;
		if (indexName != null) {
			this.indexName = indexName;
		} else {
			this.indexName = tableName + "_" + columnName + "_idx";
		}
	}
	

	@Override
	public String getQueryString() {
		String table = tableName;
		if (keyspace != null) {
			table = keyspace + "." + table;
		}
		return String.format(CREATE_INDEX_TEMPLATE_CQL, indexName, table, columnName);

	}


	@Override
	public ByteBuffer[] getValues() {
		return null;
	}


	@Override
	public ByteBuffer getRoutingKey() {
		return null;
	}


	@Override
	public String getKeyspace() {
		return keyspace;
	}
}