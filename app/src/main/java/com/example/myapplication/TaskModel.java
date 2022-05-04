package com.example.myapplication;

import java.util.Comparator;

public class TaskModel {
    String taskName;
    String TaskDetails;
    int image;
    int taskId;
    boolean taskDone;
    //add due date field
    //add category

    public TaskModel(String taskName, String taskDetails, int image,int taskId) {
        this.taskName = taskName;
        TaskDetails = taskDetails;
        this.image = image;
        //by default all task are set to false
        this.taskDone = false;
        this.taskId = taskId;
    }
    //to sort today's tasks based on weather the task is done or not
    //it will sort them by not done first then done(false,false,true,true,..)
    public static Comparator<TaskModel> taskModelComparatorDone = new Comparator<TaskModel>() {
        @Override
        public int compare(TaskModel t1, TaskModel t2) {
            return Boolean.compare(t1.isTaskDone(),t2.isTaskDone());
        }
    };
    //sort task names ascending
    public static Comparator<TaskModel> taskModelComparatorName = new Comparator<TaskModel>() {
        @Override
        public int compare(TaskModel t1, TaskModel t2) {
            return t1.getTaskName().compareToIgnoreCase(t2.getTaskName());
        }
    };

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public void setTaskDetails(String taskDetails) {
        TaskDetails = taskDetails;
    }


    public String getTaskName() {
        return taskName;
    }

    public String getTaskDetails() {
        return TaskDetails;
    }

    public int getImage() {
        return image;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "taskName='" + taskName + '\'' +
                ", TaskDetails='" + TaskDetails + '\'' +
                ", image=" + image +
                ", taskId=" + taskId +
                ", taskDone=" + taskDone +
                '}';
    }
}
