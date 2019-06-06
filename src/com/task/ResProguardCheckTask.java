/*
 * Tencent is pleased to support the open source community by making wechat-matrix available.
 * Copyright (C) 2018 THL A29 Limited, a Tencent company. All rights reserved.
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.task;

import com.exception.TaskExecuteException;
import com.exception.TaskInitException;

import com.result.TaskResult;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

import static com.task.TaskFactory.TASK_TYPE_CHECK_RESGUARD;

/**
 * Created by jinqiuchen on 17/6/6.
 */

public class ResProguardCheckTask extends ApkTask {

    private static final String TAG = "Syswin.ResProguardCheckTask";

    private File inputFile;
    private Pattern fileNamePattern;

    public ResProguardCheckTask(String params,int buildNumber) {
        super(params,buildNumber);
        type = TASK_TYPE_CHECK_RESGUARD;
    }

    @Override
    public void init() throws TaskInitException {
        super.init();

    }

    @Override
    public TaskResult call() throws TaskExecuteException {
        return null;
    }

    @Override
    public int getType() {
        return super.getType();
    }
}
