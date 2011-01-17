/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibernate.jpamodelgen.test.rawtypes;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @author Emmanuel Bernard
 */
@Entity
public class DeskWithRawType implements java.io.Serializable {

	@Id
	protected String id;

	@Basic
	protected String name;


	public DeskWithRawType() {
	}

	@ManyToMany(targetEntity = EmployeeWithRawType.class, cascade = CascadeType.ALL)
	@JoinTable(name = "DESK_EMPL",
			joinColumns =
			@JoinColumn(
					name = "DESK_FK", referencedColumnName = "ID"),
			inverseJoinColumns =
			@JoinColumn(
					name = "EMPLO_FK", referencedColumnName = "ID")
	)
	protected Collection employees = new java.util.ArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection getEmployees() {
		return employees;
	}

	public void setEmployees(Collection employees) {
		this.employees = employees;
	}
}