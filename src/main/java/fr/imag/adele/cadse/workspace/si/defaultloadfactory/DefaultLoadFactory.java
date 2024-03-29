/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.workspace.si.defaultloadfactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.imag.adele.cadse.core.CadseDomain;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.BooleanAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.DateAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.DoubleAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.EnumAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.IntegerAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.TimeAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.UUIDAttributeType;
import fr.imag.adele.cadse.workspace.as.classreferencer.IClassReferencer;
import fr.imag.adele.cadse.workspace.as.loadservice.ILoadFactory;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CAttType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItem;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;

/**
 * @generated
 */
public class DefaultLoadFactory implements ILoadFactory {

	/**
	 * @generated
	 */
	CadseDomain workspaceCU;

	/**
	 * @generated
	 */
	IClassReferencer classReferencer;

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.workspace.as.loadandpersistencefactory.ILoadFactory#convertCadsegToCAttType(fr.imag.adele
	 * .fede.workspace.as.initmodel.IInitModel, fr.imag.adele.cadse.core.Item)
	 */
	public CAttType convertCadsegToCAttType(IInitModel initEngine, Item attributeType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.workspace.as.loadandpersistencefactory.ILoadFactory#convertCadsegToCItemType(fr.imag.adele
	 * .fede.workspace.as.initmodel.IInitModel, fr.imag.adele.cadse.core.Item)
	 */
	public CItemType convertCadsegToCItemType(IInitModel initEngine, Item itemType) {
		// TODO Auto-generated method stub
		return null;
	}

	private int getFlag(CAttType type) {
		if (type.getFlag() != null) {
			return type.getFlag().intValue() | (type.getMin() == 1 ? Item.SHOW_IN_DEFAULT_CP : 0);
		}

		return type.getMin() == 1 ? Item.SHOW_IN_DEFAULT_CP : 0 + Item.DEFAULT_FLAG;
	}

	private int getMin(CAttType type) {
		if (type.getMin() != null) {
			return type.getMin();
		}
		return 0;
	}

	private int getMax(CAttType type) {
		if (type.getMax() != null) {
			return type.getMax();
		}
		return Integer.MAX_VALUE;
	}

	public IAttributeType<?> convertToAttributeType(IInitModel initEngine, Item parent, String cadseName,
			CAttType attDefinition, ItemType attDefinitionType) throws CadseException {
		if (attDefinitionType == CadseGCST.LIST) {
			List<CAttType> elements = attDefinition.getSubAttType();
			if (elements == null || elements.size() != 1) {
				throw new CadseException("cannot create value from {0} : bad definition of list", attDefinition
						.getKey());
			}
			ListAttributeType ret = new ListAttributeType(initEngine.getUUID(attDefinition.getId()),
					getFlag(attDefinition), attDefinition.getKey(), getMin(attDefinition), getMax(attDefinition), null);
			IAttributeType<?> subType = initEngine.convertToAttributeType(elements.get(0), ret, cadseName);
			ret.commitLoadCreateLink(CadseGCST.LIST_lt_SUB_TYPE, subType);
			return ret;
		}
		if (attDefinitionType == CadseGCST.BOOLEAN) {
			BooleanAttributeType ret = new BooleanAttributeType(initEngine.getUUID(attDefinition.getId()),
					getFlag(attDefinition), attDefinition.getKey(), attDefinition.getValue());
			return ret;
		}
		if (attDefinitionType == CadseGCST.STRING) {
			StringAttributeType ret = new StringAttributeType(initEngine.getUUID(attDefinition.getId()),
					getFlag(attDefinition), attDefinition.getKey(), attDefinition.getValue());
			return ret;
		}
		if (attDefinitionType == CadseGCST.INTEGER) {
			Integer min = null; /*
								 * createValue(initEngine, attDefinition, CadseGCST.INTEGER_ATTRIBUTE_TYPE_at_MIN,
								 * CadseGCST.INTEGER_ATTRIBUTE_TYPE_at_MIN_);
								 */
			Integer max = null; /*
								 * createValue(initEngine, attDefinition, CadseGCST.INTEGER_ATTRIBUTE_TYPE_at_MAX,
								 * CadseGCST.INTEGER_ATTRIBUTE_TYPE_at_MAX_);
								 */
			;
			IntegerAttributeType ret = new IntegerAttributeType(initEngine.getUUID(attDefinition.getId()),
					getFlag(attDefinition), attDefinition.getKey(), min, max, attDefinition.getValue());
			return ret;
		}

		if (attDefinitionType == CadseGCST.DOUBLE) {
			Double min = null; /*
								 * createValue(initEngine, attDefinition, CadseGCST.DOUBLE_at_MIN,
								 * CadseGCST.DOUBLE_at_MIN_);
								 */
			Double max = null; /*
								 * createValue(initEngine, attDefinition, CadseGCST.DOUBLE_at_MAX,
								 * CadseGCST.DOUBLE_at_MAX_);
								 */
			DoubleAttributeType ret = new DoubleAttributeType(initEngine.getUUID(attDefinition.getId()),
					getFlag(attDefinition), attDefinition.getKey(), min, max, attDefinition.getValue());
			return ret;
		}
		if (attDefinitionType == CadseGCST.UUID) {
			UUIDAttributeType ret = new UUIDAttributeType(initEngine.getUUID(attDefinition.getId()), attDefinition
					.getKey(), getFlag(attDefinition));
			return ret;
		}
		if (attDefinitionType == CadseGCST.TIME) {
			TimeAttributeType ret = new TimeAttributeType(initEngine.getUUID(attDefinition.getId()), attDefinition
					.getKey(), getFlag(attDefinition));
			return ret;
		}
		/*
		 * if (attDefinitionType == CadseGCST.SYMBOLIC_BIT_MAP_ATTRIBUTE_TYPE) { SymbolicBitMapAttributeType ret = new
		 * SymbolicBitMapAttributeType( initEngine.getUUID(attDefinition.getId()), attDefinition.getKey(),
		 * getFlag(attDefinition), "0"); return ret; }
		 */
		if (attDefinitionType == CadseGCST.DATE) {
			DateAttributeType ret = new DateAttributeType(initEngine.getUUID(attDefinition.getId()), attDefinition
					.getKey(), getFlag(attDefinition));
			return ret;
		}
		if (attDefinitionType == CadseGCST.ENUM) {
			String clazzname = createValue(initEngine, attDefinition, CadseGCST.ENUM_at_ENUM_CLAZZ,
					CadseGCST.ENUM_at_ENUM_CLAZZ_);

			Class<? extends Enum> clazz = classReferencer.loadClass(cadseName, clazzname);
			if (clazz == null) {
				throw new CadseException("cannot create type from {0}", attDefinition.getKey());
			}
			return new EnumAttributeType(initEngine.getUUID(attDefinition.getId()), getFlag(attDefinition),
					attDefinition.getKey(), clazz, attDefinition.getValue());
		}

		return null;

	}

	private <T> T createValue(IInitModel initEngine, CItem item, String attName, IAttributeType<T> type) {
		for (CValuesType vt : item.getAttributesValue()) {
			if (vt.getKey().equals(attName)) {
				return (T) initEngine.convertToCValue(vt, type);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.workspace.as.loadandpersistencefactory.ILoadFactory#convertToAttributeType(fr.imag.adele.
	 * fede.workspace.as.initmodel.IInitModel, fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType)
	 */
	public IAttributeType<?> convertToAttributeType(IInitModel initEngine, CValuesType attType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.workspace.as.loadandpersistencefactory.ILoadFactory#convertToCAttType(fr.imag.adele.fede.
	 * workspace.as.initmodel.IInitModel, fr.imag.adele.cadse.core.attribute.IAttributeType)
	 */
	public CAttType convertToCAttType(IInitModel initEngine, IAttributeType<?> attributeType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.workspace.as.loadandpersistencefactory.ILoadFactory#convertToCItemType(fr.imag.adele.fede
	 * .workspace.as.initmodel.IInitModel, fr.imag.adele.cadse.core.ItemType)
	 */
	public CItemType convertToCItemType(IInitModel initEngine, ItemType itemType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.workspace.as.loadandpersistencefactory.ILoadFactory#convertToItemType(fr.imag.adele.fede.
	 * workspace.as.initmodel.IInitModel, fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType)
	 */
	public ItemType convertToItemType(IInitModel initEngine, CItemType itemType) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object convertToCValue(IInitModel initEngine, CValuesType value, IAttributeType<?> type) {
		if (value.getType() != null) {
			switch (value.getType()) {
			case LIST: {
				List<Object> ret = new ArrayList<Object>();
				List<CValuesType> elements = value.getElement();
				for (CValuesType ct : elements) {
					ret.add(initEngine.convertToCValue(ct, null));
				}
				return ret;
			}
			case STRUCT:
			case MAP: {
				HashMap<String, Object> ret = new HashMap<String, Object>();
				List<CValuesType> elements = value.getElement();
				for (CValuesType ct : elements) {
					ret.put(ct.getKey(), initEngine.convertToCValue(ct, null));
				}
				return ret;
			}
			case BOOLEAN: {
				return new Boolean(value.getValue());
			}
			case STRING: {
				return value.getValue();
			}
			case INTEGER: {
				return new Integer(value.getValue());
			}
			}
		}
		return null;
	}

}
