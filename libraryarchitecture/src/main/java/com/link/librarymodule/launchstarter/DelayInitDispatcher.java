package com.link.librarymodule.launchstarter;

import android.os.Looper;
import android.os.MessageQueue;
import com.link.librarymodule.launchstarter.task.DispatchRunnable;
import com.link.librarymodule.launchstarter.task.Task;
import java.util.LinkedList;
import java.util.Queue;
/**
 * @author WJ
 * @date 2019-08-16
 *
 * 描述：延迟初始化调度器
 */
public class DelayInitDispatcher {

    private Queue<Task> mDelayTasks = new LinkedList<>();

    private MessageQueue.IdleHandler mIdleHandler = () -> {
        if(mDelayTasks.size()>0){
            Task task = mDelayTasks.poll();
            new DispatchRunnable(task).run();
        }
        return !mDelayTasks.isEmpty();
    };

    public DelayInitDispatcher addTask(Task task){
        mDelayTasks.add(task);
        return this;
    }

    public void start(){
        Looper.myQueue().addIdleHandler(mIdleHandler);
    }

}
