/*
 * Copyright 2016, The Android Open Source Project
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

package com.wmm.antman.followers;

import com.wmm.antman.data.Followers;


/**
 *
 */
public class FollowersItemActionHandler {

    private FollowersContract.Presenter mListener;

    public FollowersItemActionHandler(FollowersContract.Presenter listener) {
        mListener = listener;
    }

    /**
     * Called by the Data Binding library when the checkbox is toggled.
     */
    public void completeChanged(Followers followers, boolean isChecked) {

    }

    /**
     * Called by the Data Binding library when the row is clicked.
     */
    public void taskClicked(Followers followers) {
        mListener.openFollowersDetails(followers);
    }
}
