/*
 * Copyright (C) 2017 The JackKnife Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lwh.jackknife.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwh.jackknife.ioc.ViewInjector;

import java.lang.reflect.InvocationTargetException;

/**
 * Automatically inject a layout, bind views, and register events for fragments.
 */
public abstract class Fragment extends android.app.Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		try {
			ViewInjector injector = new ViewInjector();
			return injector.injectLayout(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		try {
			ViewInjector injector = new ViewInjector();
			injector.inject(this);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
