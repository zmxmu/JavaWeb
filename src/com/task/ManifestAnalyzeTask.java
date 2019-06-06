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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.Manifest;
import com.exception.TaskExecuteException;
import com.exception.TaskInitException;
import com.result.TaskResult;
import com.util.DBconn;
import com.util.MapTools;

import java.util.Map;

import static com.task.TaskFactory.TASK_TYPE_MANIFEST;


public class ManifestAnalyzeTask extends ApkTask {

    private static final String TAG = "Syswin.ManifestAnalyzeTask";

    public ManifestAnalyzeTask(String params) {
        super(params);
        type = TASK_TYPE_MANIFEST;
    }

    @Override
    public void init() throws TaskInitException {
        super.init();
    }

    @Override
    public TaskResult call() throws TaskExecuteException {
        long startTime = System.currentTimeMillis();
        int recordNum = 0;
        JSONObject jb = JSON.parseObject(params);
        String mfStr = jb.getString("manifest");

        Manifest manifest = JSON.parseObject(mfStr,Manifest.class);

        try {
            recordNum = DBconn.getInstance().addUpdDel("Manifest", MapTools.objectToMap(manifest));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        taskResult.setResult(TaskFactory.TaskDescription.get(type)+":"+recordNum);
        taskResult.setStartTime(startTime);
        taskResult.setEndTime(System.currentTimeMillis());
        return taskResult;
    }

    @Override
    public int getType() {
        return super.getType();
    }
}
