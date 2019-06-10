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
import com.entity.Component;
import com.exception.TaskExecuteException;
import com.exception.TaskInitException;
import com.result.TaskResult;
import com.util.DBconn;
import com.util.MapTools;

import java.util.List;

import static com.task.TaskFactory.TASK_TYPE_COMPONENT;
import static com.task.TaskFactory.TaskDescription;


public class ComponentTask extends ApkTask {

    private static final String TAG = "Syswin.TASK_TYPE_COMPONENT";
    private float MB = 1024*1024;

    public ComponentTask(String params, int buildNumber) {
        super(params,buildNumber);
        type = TASK_TYPE_COMPONENT;
    }

    @Override
    public void init() throws TaskInitException {
        super.init();
    }

    @Override
    public TaskResult call() throws TaskExecuteException {

        int recordNum = 0;
        JSONObject jb = JSON.parseObject(params);
        String str = jb.getString("entries");
        float totalSize = jb.getInteger("total-size")/MB;
        float mainSize = 0F;

        List<Component> list= JSON.parseArray(str,Component.class);
        for(;recordNum<list.size();recordNum++){
            Component item = list.get(recordNum);
            item.buildNumber = buildNumber;
            item.totalSize = item.totalSize/MB;
            if(item.totalSize>1F){
                mainSize+=item.totalSize;
                try {
                    DBconn.getInstance().addUpdDel("Component", MapTools.objectToMap(item));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Component item = new Component();
        item.buildNumber = buildNumber;
        item.suffix = "other";
        item.totalSize = totalSize - mainSize;
        try {
            DBconn.getInstance().addUpdDel("Component", MapTools.objectToMap(item));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        recordNum++;

        taskResult.setResult(TaskDescription.get(taskResult.taskType)+":"+recordNum+"");
        return taskResult;
    }

    @Override
    public int getType() {
        return super.getType();
    }
}
