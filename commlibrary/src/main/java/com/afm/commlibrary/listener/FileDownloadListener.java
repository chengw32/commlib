/*
 * Copyright 2016 jeasonlzy(廖子尧)
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
package com.afm.commlibrary.listener;


import com.afm.commlibrary.Utils.XLogUtil;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;

import java.io.File;

public class FileDownloadListener extends DownloadListener {

    public FileDownloadListener() {
        super("FileDownloadListener");
    }

    @Override
    public void onStart(Progress progress) {
    }

    @Override
    public void onProgress(Progress progress) {
        XLogUtil.e("onProgress :"+progress.fraction*100);
    }

    @Override
    public void onError(Progress progress) {
        progress.exception.printStackTrace();
    }

    @Override
    public void onFinish(File file, Progress progress) {
        XLogUtil.e("DownLoad onFinish");
    }

    @Override
    public void onRemove(Progress progress) {
    }
}
