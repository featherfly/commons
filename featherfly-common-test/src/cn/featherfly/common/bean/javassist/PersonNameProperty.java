package cn.featherfly.common.bean.javassist;
//
//package com.thgk.common.bean.javassist;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//import cn.featherfly.common.bean.BeanProperty;
//
//
//public class PersonNameProperty extends BeanProperty implements BeanPropertyAccess<Person, String>{
//
//	public PersonNameProperty(Field field, Method setter, Method getter) {
//		super(Person.class, field, setter, getter);
//	}
//
//	@Override
//	public String getValue(Person obj) {
//		return obj.getName();
//	}
//	@Override
//	public void setValue(Person obj, String value) {
//		obj.setName(value);
//	}
//}
