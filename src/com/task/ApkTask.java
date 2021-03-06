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

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by jinqiuchen on 17/5/23.
 */

public abstract class ApkTask implements Callable<TaskResult> {

    private static final String TAG = "Syswin.ApkTask";
    TaskResult taskResult;
    protected int type;
    protected String params;
    protected int buildNumber;
    protected List<ApkTaskProgressListener> progressListeners;

    public interface ApkTaskProgressListener {
        void getProgress(int progress, String message);
    }


    public ApkTask(String params,int buildNumber) {
        this.params = params;
        this.buildNumber = buildNumber;
        progressListeners = new LinkedList<>();
    }

    public int getType() {
        return type;
    }

    public void init() throws TaskInitException {
        taskResult = new TaskResult(type);
        if (params == null ) {
            throw new TaskInitException(TAG + "---params can not be null!");
        }
        if (buildNumber == 0 ) {
            throw new TaskInitException(TAG + "---buildNumber can not be 0!");
        }
    }

    public void addProgressListener(ApkTaskProgressListener listener) {
        if (listener != null) {
            progressListeners.add(listener);
        }
    }

    public void removeProgressListener(ApkTaskProgressListener listener) {
        if (listener != null) {
            progressListeners.remove(listener);
        }
    }

    protected void notifyProgress(int progress, String message) {
        for (ApkTaskProgressListener listener : progressListeners) {
            listener.getProgress(progress, message);
        }
    }

    @Override
    public abstract TaskResult call() throws TaskExecuteException;
}
