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
import com.entity.PNGFile;
import com.entity.UnUsedRes;
import com.exception.TaskExecuteException;
import com.exception.TaskInitException;

import com.result.TaskResult;
import com.util.DBconn;
import com.util.MapTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by jinqiuchen on 17/7/11.
 */

public class UnusedResourcesTask extends ApkTask {

    private static final String TAG = "Syswin.UnusedResourcesTask";

    public UnusedResourcesTask(String params,int buildNumber) {
        super(params,buildNumber);
        type = TaskFactory.TASK_TYPE_UNUSED_RESOURCES;
    }
    @Override
    public void init() throws TaskInitException {
        super.init();

    }

    @Override
    public TaskResult call() throws TaskExecuteException {
        int recordNum = 0;
        JSONObject jb = JSON.parseObject(params);
        String str = jb.getString("unused-resources");
        List<String> list= JSON.parseArray(str,String.class);
        for(;recordNum<list.size();recordNum++){
            UnUsedRes item = new UnUsedRes();
            item.name = list.get(recordNum);
            item.buildNumber = buildNumber;
            try {
                DBconn.getInstance().addUpdDel("UnUsedRes", MapTools.objectToMap(item));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        taskResult.setResult(recordNum+"");
        return taskResult;
    }

    @Override
    public int getType() {
        return super.getType();
    }
}
