/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iluwatar.pipeline;

/**
 * This stage will correct cases of the letters and print the output.
 */

import static com.iluwatar.pipeline.App.buffer4;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayush
 */
public class Stage4 implements Print, Runnable {
  
  private Thread t;
  private final String threadName;
  
  Stage4(String name) {
    threadName = name;
    //System.out.println("Creating " +  threadName );
  }
    /**
     * @return String
     */
  @Override
  public String printData() {
    try {
      if (!buffer4.isEmpty()) {
        Thread.sleep(0);
        String data = (String) buffer4.remove();
        data = data.toLowerCase();
        data = (char)(data.charAt(0) - 32) + data.substring(1);
        System.out.println("Output through 4th stage : \n" + data + ".\n");
        return data;
      }
    } catch (InterruptedException | NoSuchElementException ex) {
      Logger.getLogger(Stage4.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public void run() {
    printData();
  }
  
  @Override
  public void start() {
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }  
    
}
