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
import com.entity.MethodGroup;
import com.exception.TaskExecuteException;
import com.exception.TaskInitException;

import com.result.TaskResult;
import com.util.DBconn;
import com.util.MapTools;

import java.util.List;
import java.util.Map;

import static com.task.TaskFactory.TASK_TYPE_FIND_NON_ALPHA_PNG;
import static com.task.TaskFactory.TaskDescription;

/**
 * Created by jinqiuchen on 17/6/12.
 */

public class FindNonAlphaPngTask extends ApkTask {

    private static final String TAG = "Syswin.FindNonAlphaPngTask";

    public FindNonAlphaPngTask(String params,int buildNumber) {
        super(params,buildNumber);
        type = TASK_TYPE_FIND_NON_ALPHA_PNG;
    }

    @Override
    public void init() throws TaskInitException {
        super.init();

    }

    @Override
    public TaskResult call() throws TaskExecuteException {
        int recordNum = 0;
        JSONObject jb = JSON.parseObject(params);
        String str = jb.getString("files");
        List<PNGFile> list= JSON.parseArray(str,PNGFile.class);
        for(;recordNum<list.size();recordNum++){
            PNGFile item = list.get(recordNum);
            item.buildNumber = buildNumber;
            item.entrySize = item.entrySize/(float)1024;
            try {
                DBconn.getInstance().addUpdDel("PNGFile", MapTools.objectToMap(item));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        taskResult.setResult(TaskDescription.get(taskResult.taskType)+":"+recordNum+"");
        return taskResult;
    }

    @Override
    public int getType() {
        return super.getType();
    }
}
