/*
 * Copyright Txus Ballesteros 2015 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.bubbles.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

import com.txusballesteros.bubbles.BubbleLayout
import com.txusballesteros.bubbles.BubblesManager
import com.txusballesteros.bubbles.OnInitializedCallback

class MainActivity : AppCompatActivity() {

    private var bubblesManager: BubblesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeBubblesManager()

        findViewById<View>(R.id.add).setOnClickListener { addNewBubble() }
    }

    private fun addNewBubble() {
        val bubbleView = LayoutInflater.from(this@MainActivity).inflate(R.layout.bubble_layout, null) as BubbleLayout
        bubbleView.setOnBubbleRemoveListener { }
        bubbleView.setOnBubbleClickListener {
            Toast.makeText(applicationContext, "Clicked !",
                    Toast.LENGTH_SHORT).show()
        }
        bubbleView.setShouldStickToWall(true)
        bubblesManager!!.addBubble(bubbleView, 60, 20)
    }

    private fun initializeBubblesManager() {
        bubblesManager = BubblesManager.Builder(this)
                .setTrashLayout(R.layout.bubble_trash_layout)
                .setInitializationCallback { addNewBubble() }
                .build()
        bubblesManager!!.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        bubblesManager!!.recycle()
    }
}
